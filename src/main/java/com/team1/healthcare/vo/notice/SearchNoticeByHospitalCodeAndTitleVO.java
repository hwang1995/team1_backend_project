package com.team1.healthcare.vo.notice;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchNoticeByHospitalCodeAndTitleVO {
  private String noticeTitle;
  private String hospitalCode;

  @JsonIgnore
  public boolean isNull() {
    if (hospitalCode == null || noticeTitle == null) {
      return true;
    } else {
      return false;
    }
  }
}
