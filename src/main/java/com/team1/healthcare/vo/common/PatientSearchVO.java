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
public class PatientSearchVO {
  // 병원코드
  private String hospitalCode;
  // 환자이름
  private String patientName;

  // 2개의 데이터가 null 일때 발생시킬 함수
  @JsonIgnore
  public boolean isNull() {
    if (hospitalCode == null || patientName == null) {
      return true;
    }
    return false;
  }
}
