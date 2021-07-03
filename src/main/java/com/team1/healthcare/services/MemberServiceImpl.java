package com.team1.healthcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.team1.healthcare.dao.MembersDAO;
import com.team1.healthcare.dto.MembersDTO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class MemberServiceImpl implements IMemberService {

  @Autowired
  private MembersDAO membersDAO;

  @Override
  public boolean addMembers(MembersDTO memberInfo) {
    // 임직원의 입사일과 암호화를 실시
    memberInfo.setCurrentTime();
    memberInfo.encryptPassword();

    int result = membersDAO.insertMember(memberInfo);
    if (result == 1) {
      return true;
    }
    return false;
  }



}
