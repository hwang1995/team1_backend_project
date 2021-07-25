package com.team1.healthcare.dto;

import com.team1.healthcare.vo.diagnosis.RegistDiagnosisVO;
import com.team1.healthcare.vo.diagnosis.VitalVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * VitalRecords �뿏�떚�떚�뒗 �쓽�궗媛� 吏꾨즺�떆�뿉 諛붿씠�깉 泥댄겕�쓽 �젙蹂대�� ���옣�븷 �뻹�뿉 �궗�슜�맗�땲�떎.
 * 
 * @author sungwookhwang
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class VitalRecordsDTO {
  // vital_records 엔티티의 PK
  private int vrId;

  // diagnosis 엔티티의 FK
  private int diagId;

  // 혈압
  private int bloodPressure;

  // 맥박
  private int pulse;

  // 호흡 수
  private int respirationRate;

  // 온도
  private int temperature;

  // member
  private int memberId;

  public VitalRecordsDTO(VitalVO vitalInfo, RegistDiagnosisVO diagnosisInfo) {
    this.bloodPressure = vitalInfo.getBloodPressure();
    this.pulse = vitalInfo.getPulse();
    this.respirationRate = vitalInfo.getRespirationRate();
    this.temperature = vitalInfo.getTemperature();
    this.diagId = diagnosisInfo.getDiagId();
    this.memberId = diagnosisInfo.getMemberId();

  }
}
