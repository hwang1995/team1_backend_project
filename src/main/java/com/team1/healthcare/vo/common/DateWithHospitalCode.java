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
public class DateWithHospitalCode {
  private String startDate;
  private String endDate;
  private String hospitalCode;

  @JsonIgnore
  public boolean isNull() {
    if (startDate == null || endDate == null || hospitalCode == null) {
      return true;
    }
    return false;
  }
}
