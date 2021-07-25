package com.team1.healthcare.vo.diagnosis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VitalVO {
  private int bloodPressure;
  private int pulse;
  private int respirationRate;
  private int temperature;
}
