package com.team1.healthcare.vo.auth;

import com.team1.healthcare.dto.MembersDTO;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
public class UserInfoVO {
  @NonNull
  private String authToken;

  private int memberId;

  @NonNull
  private String memberEmail;

  @NonNull
  private String memberAuthority;

  @NonNull
  private String hospitalCode;


  public UserInfoVO(MembersDTO memberInfo, String authToken) {
    this.authToken = authToken;
    this.memberId = memberInfo.getMemberId();
    this.memberEmail = memberInfo.getMemberEmail();
    this.memberAuthority = memberInfo.getMemberAuthority();
    this.hospitalCode = memberInfo.getHospitalCode();
  }
}
