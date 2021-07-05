package com.team1.healthcare.vo.diagnosis;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class VitalResultVO {
  private int diagId;
  private int memberId;
  private int bloodPressure;
  private int pulse;
  private int respirationRate;
  private int temperature;

  public VitalResultVO(VitalVO vitalInfo, RegistDiagnosisVO diagnosisInfo) {
    this.diagId = diagnosisInfo.getDiagId();
    this.memberId = diagnosisInfo.getMemberId();
    this.bloodPressure = vitalInfo.getBloodPressure();
    this.pulse = vitalInfo.getPulse();
    this.respirationRate = vitalInfo.getRespirationRate();
    this.temperature = vitalInfo.getTemperature();

  }

}
