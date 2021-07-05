package com.team1.healthcare.test.jonghyun.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.team1.healthcare.dto.MembersDTO;

@Service
public class MemberServiceImpl implements IMemberService {

  @Override
  public List<MembersDTO> getMembersList() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<MembersDTO> getMembersList(String keyword) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean checkEmail(String memberEmail) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int countMember() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int countMember(String keyword) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void addMember(MembersDTO member) {
    // TODO Auto-generated method stub

  }

  @Override
  public void updateMember(MembersDTO member) {
    // TODO Auto-generated method stub

  }

  @Override
  public void initialPWMember(int memberId) {
    // TODO Auto-generated method stub

  }

  @Override
  public void deleteMember(int memberId) {
    // TODO Auto-generated method stub

  }

}
