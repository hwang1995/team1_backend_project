package com.team1.healthcare.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.team1.healthcare.dto.MembersDTO;
import com.team1.healthcare.vo.auth.LoginVO;
import com.team1.healthcare.vo.common.MemberSearchVO;
import com.team1.healthcare.vo.member.EmailCheckVO;

@Mapper
public interface MembersDAO {
  /**
   * 회원 정보를 삽입 하기 위한 쿼리
   * 
   * @param MembersDTO memberInfo
   * @return numbers (영향 받은 행의 수)
   */
  public int insertMember(MembersDTO memberInfo);

  /**
   * 로그인 성공 시에 JWT 토큰에 넘겨줄 loginInfo를 조회하기 위한 쿼리
   * 
   * @param LoginVO loginInfo
   * @return MembersDTO
   */
  public MembersDTO getLoginMemberInfo(LoginVO loginInfo);

  /**
   * [임시] 회원 가입시에 이메일 중복 체크를 하기 위해 필요한 쿼리
   * 
   * @param MembersDTO memberInfo
   * @return MembersDTO
   */
  public MembersDTO isExistedUser(MembersDTO memberInfo);

  /**
   * 해당 병원의 임직원 리스트를 조회하기 위한 쿼리
   * 
   * @param String hospitalCode
   * @return List<MembersDTO>
   */
  public List<MembersDTO> selectMembersListByHospitalCode(String hospitalCode);

  /**
   * 해당 병원의 임직원 이름으로 리스트를 조회하기 위한 쿼리
   * 
   * @param MemberSearchVO memberSearchInfo
   * @return List<MembersDTO>
   */
  public List<MembersDTO> selectMembersListByMemberName(MemberSearchVO memberSearchInfo);

  /**
   * 임직원 추가시에 이메일 중복을 체크하기 위한 쿼리
   * 
   * @param EmailCheckVO emailCheckInfo
   * @return MembersDTO
   */
  public MembersDTO isExistedEmail(EmailCheckVO emailCheckInfo);

  /**
   * 병원 이름과 임직원의 이름으로 카운트를 가져오기 위한 쿼리
   * 
   * @param MemberSearchVO memberSearchInfo
   * @return number (행의 수를 반환)
   */
  public int countMembersByNameAndCode(MemberSearchVO memberSearchInfo);

  /**
   * 임직원의 정보를 수정하기 위한 쿼리
   * 
   * @param MembersDTO memberInfo
   * @return number (행의 수를 반환)
   */
  public int updateMemberInfo(MembersDTO memberInfo);

  /**
   * 임직원의 비밀번호를 초기화 하기 위한 쿼리
   * 
   * @param int memberId
   * @param String memberPw
   * @return number (행의 수를 반환)
   */
  public int updateMemberPw(@Param("memberId") int memberId, @Param("memberPw") String memberPw);

  /**
   * 임직원을 삭제하기 위한 쿼리
   * 
   * @param int memberId
   * @return number (행의 수를 반환)
   */
  public int deleteMember(int memberId);

  /**
   * 해당 병원에 임직원 (의사)의 리스트를 반환하기 위한 쿼리
   * 
   * @param String hospitalCode
   * @return List<MembersDTO>
   */
  public List<MembersDTO> selectDoctorInfoByHospitalCode(String hospitalCode);
}
