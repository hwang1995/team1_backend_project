package com.team1.healthcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.team1.healthcare.dao.HospitalsDAO;
import com.team1.healthcare.dao.MembersDAO;
import com.team1.healthcare.dto.HospitalsDTO;
import com.team1.healthcare.dto.MembersDTO;
import com.team1.healthcare.vo.auth.LoginVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthServiceImpl implements IAuthService {

  @Autowired
  private MembersDAO membersDAO;

  @Autowired
  private HospitalsDAO hospitalDAO;


  @Override
  public HospitalsDTO isExistedHospital(LoginVO loginInfo) {

    HospitalsDTO hospitalInfo = hospitalDAO.getHospitalInfo(loginInfo.getHospitalCode());

    if (hospitalInfo == null) {
      return null;
    }
    return hospitalInfo;
  }


  @Override
  public MembersDTO getLoginMemberInfo(LoginVO loginInfo) {
    MembersDTO memberInfo = membersDAO.getLoginMemberInfo(loginInfo);
    return memberInfo;
  }

}
