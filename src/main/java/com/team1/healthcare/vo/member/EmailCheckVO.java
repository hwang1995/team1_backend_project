package com.team1.healthcare.vo.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmailCheckVO {
  private String hospitalCode;
  private String memberEmail;

}
