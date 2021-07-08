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
public class WeekNoWithMemberVO {
  private int weekNo;
  private int memberId;

  @JsonIgnore
  public boolean isNull() {
    Integer weekNo = new Integer(this.weekNo);
    Integer memberId = new Integer(this.memberId);

    if (weekNo == null || memberId == null) {
      return true;
    }

    return false;
  }
}
