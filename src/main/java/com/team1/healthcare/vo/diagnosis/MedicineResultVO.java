package com.team1.healthcare.vo.diagnosis;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MedicineResultVO {
  private int medicineId;
  private int medicineDose;
  private int diagId;
  private int memberId;

  public MedicineResultVO(MedicineVO medicineInfo, RegistDiagnosisVO diagnosisInfo) {
    this.medicineId = medicineInfo.getMedicineId();
    this.medicineDose = medicineInfo.getMedicineDose();
    this.diagId = diagnosisInfo.getDiagId();
    this.memberId = diagnosisInfo.getMemberId();
  }

}
