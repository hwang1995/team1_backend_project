package com.team1.healthcare.api.v1;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.team1.healthcare.dto.MembersDTO;
import com.team1.healthcare.services.MemberServiceImpl;
import com.team1.healthcare.vo.common.MemberSearchVO;
import com.team1.healthcare.vo.member.EmailCheckVO;
import com.team1.healthcare.vo.notice.AddNoticeImageVO;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/member")
public class MemberController {

  @Autowired
  private MemberServiceImpl memberService;


  // 1. GET 해당 병원의 모든 임직원 목록 가져오기
  // 새로고침(이미지) 버튼 클릭시 클라이언트 요청
  @GetMapping("")
  public List<MembersDTO> getMemberList(String hospitalCode) {
    return memberService.showMembersListByHospitalCode(hospitalCode);
  }


  // 2. GET 검색된 키워드(이름)이 포함된 모든 임직원 목록 가져오기
  // 임직원 관리 메인페이지 or 임직원 관리 검색후 submit했을때
  @GetMapping("/search")
  public List<MembersDTO> getMemberSearchList(@RequestBody MemberSearchVO memberSearchInfo) {
    log.info(memberSearchInfo.toString());
    return memberService.showMembersListByNameAndCode(memberSearchInfo);
  }


  // 3. POST 해당 병원의 해당 임직원을 추가하기
  // '추가'버튼 클릭후 나오는 drawer창에서 '임직원 추가'버튼 클릭시 클라이언트 요청
  @PostMapping("")
  public boolean addMember(@RequestBody MembersDTO memberInfo) {
    log.info(memberInfo.toString());
    memberService.addMember(memberInfo);
    return true;
  }


  // 4. PUT 해당 병원의 해당 임직원을 수정하기
  // '변경'버튼 클릭후 열리는 drawer창에서 '정보변경' 버튼 클릭시 클라이언트 요청
  @PutMapping("")
  public boolean updateMember(@RequestBody MembersDTO memberInfo) {
    memberService.modifyMemberInfo(memberInfo);
    return true;
  }


  // 4. DELETE 해당 병원의 해당 임직원을 삭제하기
  // '삭제'버튼 클릭후 나오는 모달창에서 '확인'버튼 클릭시 클라이언트 요청
  @DeleteMapping("")
  public boolean deleteMember(int memberId) {
    memberService.deleteMember(memberId);
    return true;
  }


  // 5. PUT 해당 병원의 해당 임직원에 대한 비밀번호 초기화하기
  // '비밀번호 초기화'버튼 클릭시 클라이언트 요청
  @PutMapping("/initial-pw")
  public boolean memberInitialPw(int memberId) {
    memberService.intializeMemberPw(memberId);
    return true;
  }

  // 6. POST 해당 환자의 이미지 업로드 버튼을 눌렀을때 이미지를 base64로 전송해서 저장
  // '이미지를 선택해주세요' 버튼을 클릭 후 이미지를 선택할때 클라이언트 요청
  @PostMapping("/image")
  public String memberUploadImage(@RequestBody AddNoticeImageVO imageInfo) {
    String filePath = memberService.memberImageUpload(imageInfo);
    if (filePath == null) {
      return null;
    }
    log.info(filePath);
    return filePath;
  }

  // 7. GET 중복된 이메일이 있는지에 대해 검사
  // '중복체크' 버튼을 클릭 시 클라이언트 요청
  @GetMapping("/email-check")
  public boolean memberEmailCheck(@RequestBody EmailCheckVO emailCheckInfo) {
    memberService.isExistedEmail(emailCheckInfo);
    return true;
  }

}
