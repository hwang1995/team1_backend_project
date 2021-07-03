package com.team1.healthcare.vo.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginVO {
  private String hospitalCode;
  private String memberEmail;
  private String memberPw;

}
