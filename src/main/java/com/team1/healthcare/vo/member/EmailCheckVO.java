package com.team1.healthcare.vo.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

  @JsonIgnore
  public boolean isNull() {
    if (this.hospitalCode == null || this.memberEmail == null) {
      return true;
    } else {
      return false;
    }
  }
}
