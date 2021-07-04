package com.team1.healthcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.team1.healthcare.dao.MembersDAO;
import com.team1.healthcare.dto.MembersDTO;
import com.team1.healthcare.exception.ConflictRequestException;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class MemberServiceImpl implements IMemberService {

  @Autowired
  private MembersDAO membersDAO;

  @Override
  public boolean addMembers(MembersDTO memberInfo) {
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



}
