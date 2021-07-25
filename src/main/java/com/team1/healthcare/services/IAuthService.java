package com.team1.healthcare.services;

import com.team1.healthcare.dto.HospitalsDTO;
import com.team1.healthcare.dto.MembersDTO;
import com.team1.healthcare.vo.auth.LoginVO;

public interface IAuthService {
  public HospitalsDTO isExistedHospital(LoginVO loginInfo);

  public MembersDTO getLoginMemberInfo(LoginVO loginInfo);
  // public UserInfoVO processAuthentication(LoginVO loginInfo);
}
