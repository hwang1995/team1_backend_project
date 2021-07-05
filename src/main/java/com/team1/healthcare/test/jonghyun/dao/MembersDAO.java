package com.team1.healthcare.test.jonghyun.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.team1.healthcare.dto.MembersDTO;

@Mapper
public interface MembersDAO {
  // 임직원의 전체 리스트 받아오기
  // 프론트에서 검색초기화 버튼 맟 새로고침을 할 경우 ('') keyword 전달
  public List<MembersDTO> getMembersList();

  // 검색 키워드가 포함된 임직원의 전체 리스트 받아오기
  public List<MembersDTO> getMembersKeywordList(String keyword);

  // 해당임직원 상세정보보기 (화면 미구현)
  // public List<MembersDTO> getMemberDetail(String member_id);

  // 이메일 중복 체크
  // 해당 데이터값이 임직원 컬럼에 있을 경우 true, 데이터가 없을경우 false 리턴
  public int checkEmail(String memberEmail);

  // 임직원의 개수를 셀때
  public int countMember();

  // 키워드가 포함된 임직원의 개수를 셀때
  public int countMember(String keyword);

  // 임직원 추가
  public void createMember(MembersDTO member);

  // 해당 임직원에 대한 정보를 수정할때
  public void updateMember(MembersDTO member);

  // 해당 임직원에 대한 비밀번호를 초기화할때
  public void initialPWMember(int memberId);

  // 해당 임직원에 대한 정보를 삭제할때
  public void deleteMember(int memberId);

  // 임직원에 대한 page에 해당되는 정보들 가져오기(material-ui 테스트필요)
  // publc List<MembersDTO> getMemberByPage(int pageNo);

}
