package com.team1.healthcare.dto;

import com.team1.healthcare.vo.diagnosis.RegistDiagnosisVO;
import com.team1.healthcare.vo.diagnosis.VitalVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * VitalRecords 엔티티는 의사가 진료시에 바이탈 체크의 정보를 저장할 떄에 사용됩니다.
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

  // diagnosis 엔티티의 FK (어떤 진료에서 바이탈 체크를 받았는지 식별하기 위해)
  private int diagId;

  // 혈압
  private int bloodPressure;

  // 맥박
  private int pulse;

  // 호흡 수
  private int respirationRate;

  // 온도
  private int temperature;

  // member 엔티티의 FK (어떤 임직원이 바이탈 체크를 했는지 식별하기 위해)
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
