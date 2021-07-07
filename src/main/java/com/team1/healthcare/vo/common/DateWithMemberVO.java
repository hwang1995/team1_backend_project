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
public class DateWithMemberVO {
  private String startDate;
  private String endDate;
  private int memberId;

  @JsonIgnore
  public boolean isNull() {
    if (startDate == null || endDate == null || memberId == 0) {
      return true;
    }
    return false;
  }
}
