package com.team1.healthcare.services;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.io.Files;
import com.team1.healthcare.commons.CommonUtils;
import com.team1.healthcare.dao.HospitalsDAO;
import com.team1.healthcare.dao.MembersDAO;
import com.team1.healthcare.dto.HospitalsDTO;
import com.team1.healthcare.dto.MembersDTO;
import com.team1.healthcare.exception.BadRequestException;
import com.team1.healthcare.exception.ConflictRequestException;
import com.team1.healthcare.exception.NoContentException;
import com.team1.healthcare.vo.common.MemberSearchVO;
import com.team1.healthcare.vo.notice.AddNoticeImageVO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class MemberServiceImpl implements IMemberService {

  // 이메일 정규식
  private boolean isEmail(String memberEmail) {
    return Pattern.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",
        memberEmail);
  }

  private boolean isPhone(String memberTel) {
    return Pattern.matches("^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$",
        memberTel);
  }

  private boolean isPassword(String memberPw) {
    return Pattern.matches(
        "(?=.*\\d{1,50})(?=.*[~`!@#$%\\^&*()-+=]{1,50})(?=.*[a-zA-Z]{2,50}).{8,50}$", memberPw);
  }


  @Autowired
  private MembersDAO membersDAO;

  @Autowired
  private HospitalsDAO hospitalsDAO;


  @Override
  public boolean addMember(MembersDTO memberInfo) {

    if (memberInfo.isNull()) {
      throw new BadRequestException("필수 값이 누락되어있습니다.", new Throwable("null-info"));
    }

    if (!isEmail(memberInfo.getMemberEmail())) {
      throw new BadRequestException("이메일 값이 잘못되었습니다.", new Throwable("invalid-email"));
    }

    if (!isPhone(memberInfo.getMemberTel())) {
      throw new BadRequestException("전화번호 값이 잘못되었습니다.", new Throwable("invalid-tel"));
    }

    if (!isPassword(memberInfo.getMemberPw())) {
      throw new BadRequestException("비밀번호 값이 잘못되었습니다.", new Throwable("invalid-pw"));
    }

    // 해당 이메일이 존재하는지 여부 검사
    int memberEmailCount = membersDAO.countExistedEmail(memberInfo.getMemberEmail());
    if (memberEmailCount >= 1) { // 존재(중복)
      throw new BadRequestException("이메일 값이 중복됩니다.", new Throwable("overlapped-memberEmail"));
    } else if (memberEmailCount < 0) {
      throw new RuntimeException("알 수 없는 이유로 임직원 추가도중 이메일 중복체크가 실패하였습니다.");
    }

    HospitalsDTO existedHospitalInfo = hospitalsDAO.getHospitalInfo(memberInfo.getHospitalCode());
    if (existedHospitalInfo == null) {
      throw new BadRequestException("해당 병원이 존재하지 않습니다.", new Throwable("invalid-hospitalCode"));
    }

    // 의사의 진료실(doctorRoom)을 세팅
    if (memberInfo.getMemberAuthority().equals("ROLE_DOCTOR")) {
      int doctorRoomNum = membersDAO.countDoctorByHospitalCode(memberInfo.getHospitalCode()) + 1;
      memberInfo.setDoctorRoom("진료실 " + doctorRoomNum);
    }

    // 임직원의 입사일과 암호화를 실시
    memberInfo.setCurrentTime();
    memberInfo.encryptPassword();

    // 추가할때 영향받은 행 수
    int result = membersDAO.insertMember(memberInfo);
    if (result == 1) {
      return true;
    }

    throw new RuntimeException("알 수 없는 이유로 임직원 추가가 실패하였습니다.");
  }


  // 임직원 정보 수정
  @Override
  public boolean modifyMemberInfo(MembersDTO memberInfo) {

    log.info(memberInfo.toString());

    if (memberInfo.isModifyDataNull()) {
      throw new BadRequestException("필수 값이 누락되어 있습니다.", new Throwable("null-info"));
    }

    int modifyRows = membersDAO.updateMemberInfo(memberInfo);

    // 변경이 안되면
    if (modifyRows == 0) {
      throw new BadRequestException("임직원 정보가 변경되지 않았습니다.", new Throwable("failed-info"));
    }
    // 하나의 직원정보 변경 성공
    else if (modifyRows == 1) {
      return true;
    }

    throw new RuntimeException("알 수 없는 이유로 임직원 정보수정이 실패하였습니다.");
  }


  // 임직원 삭제
  @Override
  public boolean deleteMember(int memberId) {
    if (memberId <= 0) {
      throw new BadRequestException("memberId값이 제대로 입력되지 않았습니다.", new Throwable("wrong-info"));
    }

    int deleteRows = membersDAO.deleteMember(memberId);
    if (deleteRows == 1) {
      return true;
    }

    throw new RuntimeException("알 수 없는 이유로 임직원 삭제가 실패하였습니다.");
  }


  // 해당 병원의 임직원 목록
  @Override
  public List<MembersDTO> showMembersListByHospitalCode(String hospitalCode) {

    if (hospitalCode == null) {
      throw new BadRequestException("hospitalCode값이 존재하지 않습니다.", new Throwable("null-info"));
    }

    List<MembersDTO> memberInfo = membersDAO.selectMembersListByHospitalCode(hospitalCode);
    if (memberInfo == null || memberInfo.size() == 0) {
      log.info("@@@@@@@@@@@@@@@@@");
      throw new NoContentException("결과값이 없습니다.", new Throwable("no-content"));
    }

    return memberInfo;
  }


  // 해당 병원의 임직원들에 대한 이름검색
  @Override
  public List<MembersDTO> showMembersListByNameAndCode(MemberSearchVO memberSearchInfo) {

    if (memberSearchInfo.isNull() || memberSearchInfo.getMemberName().trim().isEmpty()) {
      throw new BadRequestException("MemberSearchVO값이 존재하지 않습니다.", new Throwable("null-info"));
    }

    List<MembersDTO> memberInfo = membersDAO.selectMembersListByMemberName(memberSearchInfo);
    if (memberInfo == null || memberInfo.size() == 0) {
      throw new NoContentException("결과값이 없습니다.", new Throwable("no-content"));
    }

    return memberInfo;
  }

  // 임직원 이메일이 중복되었는지 체크
  @Override
  public boolean isExistedEmail(String memberEmail) {

    if (memberEmail.trim().isEmpty() || memberEmail == null) {
      throw new BadRequestException("이메일값이 입력되지 않았습니다.", new Throwable("null-memberEmail"));
    }
    if (!isEmail(memberEmail)) {
      throw new BadRequestException("이메일 값이 잘못되었습니다.", new Throwable("invalid-email"));
    }

    int memberEmailCount = membersDAO.countExistedEmail(memberEmail);
    if (memberEmailCount == 0) { // 존재하지 않음
      return true;
    } else if (memberEmailCount >= 1) { // 존재
      throw new BadRequestException("이메일 값이 중복됩니다.", new Throwable("overlapped-memberEmail"));
    }

    throw new RuntimeException("알 수 없는 이유로 이메일 중복체크에 실패하였습니다.");
  }


  // 비밀번호 초기화
  @Override
  public boolean intializeMemberPw(int memberId) {

    if (memberId == 0) {
      throw new BadRequestException("memberId값이 존재하지 않습니다.", new Throwable("null-info"));
    }

    String saltedPassword = CommonUtils.encryptPassword("!@#douzone1234");
    int updateRows = membersDAO.updateMemberPw(memberId, saltedPassword);
    if (updateRows != 1) {
      throw new ConflictRequestException("초기화 중 오류가 생겼습니다.", new Throwable("initialPW-error"));
    }

    return true;
  }


  // 이미지 업로드
  @Override
  public String memberImageUpload(AddNoticeImageVO imageInfo) {

    if (imageInfo.isNull()) {
      throw new BadRequestException("AddNoticeImageVO값이 존재하지 않습니다.", new Throwable("null-info"));
    }

    String extension = Files.getFileExtension(imageInfo.getImageName());
    if (extension.trim().isEmpty()) {
      throw new BadRequestException("파일 이름이 올바르지 않습니다.", new Throwable("bad-filename"));
    }

    if (!(extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png"))) {
      throw new BadRequestException("허용되지 않는 확장자입니다.", new Throwable("bad-file-extension"));
    }


    // base64의 순수 binary 데이터를 가져오기 위해서 접두에 붙어있는 내용을 제거한다.
    String[] base64Str = imageInfo.getBase64Content().split(",");

    // base64로 encoding된 데이터를 decoding 해서 byte[]로 임시 저장
    byte[] decodedBytes = Base64.getDecoder().decode(base64Str[1]);

    // defaultPath, filePath를 지정
    String defaultPath = System.getProperty("user.home") + "/images";
    String filePath = "/" + imageInfo.getHospitalCode() + "/" + imageInfo.getImageName();
    log.info("default: " + defaultPath);
    log.info("filePath: " + filePath);

    try {
      FileUtils.writeByteArrayToFile(new File(defaultPath + filePath), decodedBytes);
      // 상품 이미지의 정보를 보내기 위해 DTO와 정보를 넣어줌
    } catch (IOException e) {
      e.printStackTrace();
    }

    return filePath;
  }

}
