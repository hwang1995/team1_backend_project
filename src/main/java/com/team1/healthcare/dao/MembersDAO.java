package com.team1.healthcare.dao;

import org.apache.ibatis.annotations.Mapper;
import com.team1.healthcare.dto.MembersDTO;
import com.team1.healthcare.vo.auth.LoginVO;

@Mapper
public interface MembersDAO {
  public int insertMember(MembersDTO memberInfo);

  public MembersDTO getLoginMemberInfo(LoginVO loginInfo);

  public MembersDTO isExistedUser(MembersDTO memberInfo);
}
