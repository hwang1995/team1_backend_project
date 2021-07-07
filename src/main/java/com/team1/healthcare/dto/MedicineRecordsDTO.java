package com.team1.healthcare.dto;

import com.team1.healthcare.vo.diagnosis.MedicineVO;
import com.team1.healthcare.vo.diagnosis.RegistDiagnosisVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * MedicineRecords 엔티티는 의사가 진료 시에 약 혹은 주사 처방 기록을 저장합니다.
 * 
 * @author sungwookhwang
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class MedicineRecordsDTO {
  // medicine_records 엔티티의 PK
  private int medicineRecordId;

  // diagnosis 엔티티의 FK (어떤 진료에서 약 혹은 주사를 처방했는지 식별하기 위해)
  private int diagId;

  // medicine 엔티티의 FK (어떤 약 혹은 주사를 처방 받았는지 식별하기 위해)
  private int medicineId;

  // 약 혹은 주사를 얼마나 투여할 지의 수량
  private int medicineDose;

  // members 엔티티의 FK (어떤 임직원이 약 혹은 주사를 처방했는지 식별하기 위해)
  private int memberId;

  private String medicineType;

  public MedicineRecordsDTO(MedicineVO medicineInfo, RegistDiagnosisVO diagnosisInfo) {
    this.medicineId = medicineInfo.getMedicineId();
    this.medicineDose = medicineInfo.getMedicineDose();
    this.diagId = diagnosisInfo.getDiagId();
    this.memberId = diagnosisInfo.getMemberId();
  }
}
