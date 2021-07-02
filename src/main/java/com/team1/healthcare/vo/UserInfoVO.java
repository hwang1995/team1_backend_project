package com.team1.healthcare.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class UserInfoVO {
  private int memberId;
  private String memberEmail;
  private String memberAuthority;
  private String hospitalCode;
}
