package com.team1.healthcare.vo.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberSearchVO {
  private String hospitalCode;
  private String memberName;

  @JsonIgnore
  public boolean isNull() {
    if (this.hospitalCode == null || this.memberName == null) {
      return true;
    } else {
      return false;
    }
  }
}
