package com.team1.healthcare.services;

import java.util.List;
import com.team1.healthcare.dto.MembersDTO;
import com.team1.healthcare.vo.common.MemberSearchVO;
import com.team1.healthcare.vo.member.EmailCheckVO;
import com.team1.healthcare.vo.notice.AddNoticeImageVO;

public interface IMemberService {

  /**
   * 임직원을 추가하기 위한 서비스 인터페이스
   * 
   * @param MembersDTO memberInfo
   * @return true or false (성공 여부)
   */
  public boolean addMember(MembersDTO memberInfo);

  /**
   * 임직원의 정보를 수정하기 위한 서비스 인터페이스
   * 
   * @param MembersDTO memberInfo
   * @return true or false (성공 여부)
   */
  public boolean modifyMemberInfo(MembersDTO memberInfo);

  /**
   * 임직원을 삭제하기 위한 서비스 인터페이스
   * 
   * @param int memberId
   * @return true or false (성공 여부)
   */
  public boolean deleteMember(int memberId);

  /**
   * 병원 코드 (식별자)로 임직원의 목록을 보여주기 위한 서비스 인터페이스
   * 
   * @param String hospitalCode
   * @return List<MembersDTO>
   */
  public List<MembersDTO> showMembersListByHospitalCode(String hospitalCode);

  /**
   * 병원 코드와 임직원의 이름으로 임직원의 목록을 보여주기 위한 서비스 인터페이스
   * 
   * @param MemberSearchVO memberSearchInfo
   * @return List<MembersDTO>
   */
  public List<MembersDTO> showMembersListByNameAndCode(MemberSearchVO memberSearchInfo);

  /**
   * 존재하는 이메일인지 체크하기 위한 서비스 인터페이스
   * 
   * @param EmailCheckVO emailCheckInfo
   * @return true or false (존재 여부)
   */
  public boolean isExistedEmail(EmailCheckVO emailCheckInfo);

  /**
   * 임직원의 비밀번호를 초기화하기 위한 서비스 인터페이스
   * 
   * @param int memberId
   * @return true or false (성공 여부)
   */
  public boolean intializeMemberPw(int memberId);

  /**
   * 임직원 추가시에 이미지를 업로드 하기 위한 서비스 인터페이스
   * 
   * @param AddNoticeImageVO imageInfo
   * @return imageSrc
   */
  public String memberImageUpload(AddNoticeImageVO imageInfo);

}
