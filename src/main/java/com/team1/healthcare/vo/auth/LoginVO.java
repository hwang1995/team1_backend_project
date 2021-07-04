package com.team1.healthcare.vo.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginVO {
  @NonNull
  private String hospitalCode;

  @NonNull
  private String memberEmail;

  @NonNull
  private String memberPw;

}
