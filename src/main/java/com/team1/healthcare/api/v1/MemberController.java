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
import com.team1.healthcare.services.IMemberService;
import com.team1.healthcare.vo.common.MemberSearchVO;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/member")
public class MemberController {

  @Autowired
  private IMemberService memberService;

  // 1. GET 해당 병원의 모든 임직원 or 검색된 키워드(이름)이 포함된 모든 임직원
  // 임직원 관리 메인페이지 or 임직원 관리 검색후 submit했을때
  @GetMapping("")
  public List<MembersDTO> getMemberList(@RequestBody MemberSearchVO memberSearchInfo) {
    log.info(memberSearchInfo.toString());

    // 1. @RequestBody를 통해 memberSearchVO를 JSON형태의 데이터를 인자로 받음
    // 2. showMembersListByNameAndCode 서비스 호출
    // 3. 결과값 리턴

    return memberService.showMembersListByNameAndCode(memberSearchInfo);
  }


  // 2. GET 임직원에 대한 검색목록을 초기화후 모든 임직원 데이터 받기
  // 새로고침(이미지) 버튼 클릭시 클라이언트 요청
  @GetMapping("/test2")
  public List<MembersDTO> initialMemberSearch(String hospitalCode) {

    // 1. String hospitalCode를 인자로 받음
    // 2. selectMembersListByHospitalCode 서비스 호출
    // 3. 결과값 리턴

    return null;
  }


  // 3. POST 해당 병원의 해당 임직원을 추가하기
  // '추가'버튼 클릭후 나오는 drawer창에서 '임직원 추가'버튼 클릭시 클라이언트 요청
  @PostMapping("/test3")
  public boolean addMember(@RequestBody MembersDTO memberInfo) {
    return true;
    // throw new ConflictRequestException("에러가 났어요", new Throwable("fatal_error"));
    //
    // 1. @RequestBody를 통해 MembersDTO를 JSON형태로 받음
    // 2. addMembers 서비스 호출(true: 성공, false: 실패)

  }


  // 4. PUT 해당 병원의 해당 임직원을 수정하기
  // '변경'버튼 클릭후 열리는 drawer창에서 '정보변경' 버튼 클릭시 클라이언트 요청
  @PutMapping("/test3")
  public boolean updateMember(@RequestBody MembersDTO memberInfo) {
    return true;
    // 1. @RequestBody를 통해 MembersDTO를 JSON형태로 받음
    // 2. modifyMemberInfo 서비스 호출(true: 성공, false: 실패)
  }


  // 4. DELETE 해당 병원의 해당 임직원을 삭제하기
  // '삭제'버튼 클릭후 나오는 모달창에서 '확인'버튼 클릭시 클라이언트 요청
  @DeleteMapping("/test3")
  public boolean deleteMember(int memberId) {
    return true;
    // 1. int값을 인자로 받음
    // 2. deleteMember 서비스 호출(true: 성공, false: 실패)
  }


  // 5. PUT 해당 병원의 해당 임직원에 대한 비밀번호 초기화하기
  // '비밀번호 초기화'버튼 클릭시 클라이언트 요청
  @PutMapping("/test4")
  public boolean initialMemberPw(int memberId) {
    return true;
    // 1. int값을 인자로 받음
    // 2. intializeMemberPw 서비스 호출(true: 성공, false: 실패)
  }
}
