package com.team1.healthcare.vo.diagnosis;

import com.team1.healthcare.dto.MedicineRecordsDTO;
import com.team1.healthcare.dto.MedicinesDTO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MedicineRecordVO {
  private String medicineCode;
  private String medicineType;
  private String medicineUnit;
  private String medicineExplain;

  private int medicineDose; // MedicineRecordsDTO

  public MedicineRecordVO(MedicinesDTO medicineInfo, MedicineRecordsDTO medicineRecordInfo) {
    this.medicineCode = medicineInfo.getMedicineCode();
    this.medicineType = medicineInfo.getMedicineType();
    this.medicineUnit = medicineInfo.getMedicineUnit();
    this.medicineExplain = medicineInfo.getMedicineExplain();

    this.medicineDose = medicineRecordInfo.getMedicineDose();
  }
}
