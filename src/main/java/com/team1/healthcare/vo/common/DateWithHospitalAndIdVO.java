package com.team1.healthcare.vo.common;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DateWithHospitalAndIdVO {
  private LocalDateTime startDate;
  private int peopleId;
  private String hospitalCode;

  @JsonIgnore
  public boolean isNull() {
    Integer id = new Integer(peopleId);
    if (startDate == null || id == null || hospitalCode == null) {
      return true;
    }
    return false;
  }
}
