package com.team1.healthcare.vo.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team1.healthcare.dto.MembersDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Slf4j
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

  private String memberName;


  public UserInfoVO(MembersDTO memberInfo, String authToken) {
    this.authToken = authToken;
    log.info(memberInfo.toString());
    this.memberId = memberInfo.getMemberId();

    this.memberName = memberInfo.getMemberName();
    this.memberEmail = memberInfo.getMemberEmail();
    this.memberAuthority = memberInfo.getMemberAuthority();
    this.hospitalCode = memberInfo.getHospitalCode();
  }

  @JsonIgnore
  public boolean isNull() {
    if (authToken == null || memberId == 0 || memberEmail == null || memberAuthority == null
        || hospitalCode == null) {
      return true;
    }
    return false;
  }
}
