package com.team1.healthcare.vo.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// 예약리스트를 가져오기 위한 VO
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WeekNoWithMemberVO {
  private int weekNo; // 일년중 몇번쨰
  private int memberId; // 의사 고유 번호
  private String hospitalCode; // 병원코드


  @JsonIgnore
  public boolean isNull() {
    Integer weekNo = new Integer(this.weekNo);
    Integer memberId = new Integer(this.memberId);

    if (weekNo == null || memberId == null || hospitalCode == null) {
      return true;
    }

    return false;
  }
}
