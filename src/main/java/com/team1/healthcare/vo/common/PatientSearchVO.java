package com.team1.healthcare.vo.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PatientSearchVO {
  private String hospitalCode;
  private String patientName;
}
