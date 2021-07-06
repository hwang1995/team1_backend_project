package com.team1.healthcare.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.team1.healthcare.dao.MembersDAO;
import com.team1.healthcare.dto.MembersDTO;
import com.team1.healthcare.exception.ConflictRequestException;
import com.team1.healthcare.vo.common.MemberSearchVO;
import com.team1.healthcare.vo.member.EmailCheckVO;
import com.team1.healthcare.vo.notice.AddNoticeImageVO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class MemberServiceImpl implements IMemberService {

  @Autowired
  private MembersDAO membersDAO;

  @Override
  public boolean addMembers(MembersDTO memberInfo) {

    // 1. Dao의 isExistedUser를 통해 이메일이 존재하는지 체크
    // 1-1 존재하면 throw ConflictRequestException
    // 1-2 유효성검사
    // 1-3. null : throw NoContentException
    // 1-4. 잘못된 값 : throw ConflictRequestException

    // 2. 비밀번호 유효성 검사(비밀번호를 숫자, 특수문자 각 1회 이상, 영문은 2글자 이상 입력하고 총 8자 이상)
    // 2-1. null : throw NoContentException
    // 2-2. 잘못된 값 : throw ConflictRequestException

    // 3. 성별을 선택
    // 3-1. null : throw NoContentException

    // 4. 이름을 입력
    // 4-1. null : throw NoContentException

    // 5. 전화번호 유효성 검사(000-0000-0000)
    // 5-1. null : throw NoContentException
    // 5-2. 잘못된 값: throw ConflictRequestException

    // 6. 생년월일 입력
    // 6-1. null : throw NoContentException

    // 7. 주소 입력 memberPostal, memberAddr1, memberAddr2
    // 7-1. null : throw NoContentException

    // 8. 이미지 업로드 버튼 클릭
    // 8-1. AddNoticeImageVO와 base64 저장

    // 9. 자기소개란 입력
    // 10. 임직원의 입사일과 암호화 실시
    // 11. DAO의 insertMember 호출
    // 12. 결과값이 1이면 true 리턴
    // 12-1. 그 외 throw RuntimeException

    // 우선 이미 병원에 해당 이메일이 존재 하는지 점검한다.
    MembersDTO existedMemberInfo = membersDAO.isExistedUser(memberInfo);

    if (existedMemberInfo != null) {
      throw new ConflictRequestException("이미 존재하는 이메일 주소입니다. 다른 이메일 주소를 입력해주세요",
          new Throwable("existed-email"));
    }
    // 임직원의 입사일과 암호화를 실시
    memberInfo.setCurrentTime();
    memberInfo.encryptPassword();

    int result = membersDAO.insertMember(memberInfo);

    if (result == 1) {
      return true;
    }

    throw new RuntimeException("알 수 없는 이유로 회원가입에 실패하였습니다.");
  }

  // 임직원 추가
  @Override
  public boolean addMember(MembersDTO memberInfo) {
    // TODO Auto-generated method stub
    return false;
  }

  // 임직원 정보 수정
  @Override
  public boolean modifyMemberInfo(MembersDTO memberInfo) {
    // DAO를 통한 정보 수정
    // return 수정된 행의 개수
    int modifyRows = membersDAO.updateMemberInfo(memberInfo);
    if (modifyRows == 1) { // 한행만 수정
      return true;
    } else {
      return false;
    }
  }

  // 임직원 삭제
  @Override
  public boolean deleteMember(int memberId) {
    int deleteRows = membersDAO.deleteMember(memberId);
    if (deleteRows == 1) {
      return true;
    } else {
      return false;
    }
  }

  // 해당 병원의 임직원 목록
  @Override
  public List<MembersDTO> showMembersListByHospitalCode(String hospitalCode) {

    // hospitalCode가 null 일경우 throw BadRequestException
    // DAO의 selectMembersListByHospitalCode 호출
    // 만약 List<MembersDTO>의 크기가 0이라면? NoContentException을 터트린다.
    // 값 리턴

    return membersDAO.selectMembersListByHospitalCode(hospitalCode);
  }

  // 해당 병원의 임직원들에 대한 이름검색
  @Override
  public List<MembersDTO> showMembersListByNameAndCode(MemberSearchVO memberSearchInfo) {

    // 1. MemberSearchVO가 null이면? throw BadRequestException
    // 2. List<MembersDTO> memberInfo = membersDAO.selectMembersListByMemberName()
    // 3. 만약 memberInfo의 크기가 0이라면 throw new NoContentException

    // 값 리턴

    return membersDAO.selectMembersListByMemberName(memberSearchInfo);
  }

  // 해당 병원에서 임직원 이메일이 중복되었는지 체크
  @Override
  public boolean isExistedEmail(EmailCheckVO emailCheckInfo) {

    // 1. EmailCheckVO가 null이면? throw new BadRequestException
    // 2. MembersDAO의 isExistedEmail를 호출
    // 3. 만약 MembersDTO가 null이 아니면, throw new ConflictRequestException
    // 4. return true;

    return false;
  }

  // 비밀번호 초기화
  @Override
  public boolean intializeMemberPw(int memberId) {

    // 1. memberId가 0이면? throw new BadRequestException
    // 2. 비밀번호 초기화를 위해 !@#douzone1234으로 초기화 할 예정
    // 3. String saltedPassword = CommonUtils.encryptPassword("!@#douzone1234");
    // 4. MembersDAO의 updateMemberPw(memberId, saltedPassword)
    // 5. 만약 1이 아니면, ConflictRequestException을 터트린다.


    // DAO의 updateMemberPw 호출, 전달 @params (memberId,memberPw)
    // 1이면 true
    // 그외 런타임에러
    return false;
  }

  // 이미지 업로드
  @Override
  public String memberImageUpload(AddNoticeImageVO imageInfo) {

    // 1. imageInfo가 null이면? throw new BadRequestException
    // 2. base64의 순수 binary 데이터를 가져오기 위해서 접두에 붙어있는 내용을 제거한다.
    // 3. base64로 encoding된 데이터를 decoding 해서 byte[]로 임시 저장
    // 4. defaultPath, filePath를 지정
    // 5, FileUtils.writeByteArrayToFile(new File(defaultPath + filePath), decodedBytes)
    // 6. 이미지 경로 보내줘

    return null;
  }

}
