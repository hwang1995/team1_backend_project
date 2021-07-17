package com.team1.healthcare.vo.diagnosis;

import java.time.LocalDate;
import com.team1.healthcare.commons.CommonUtils;
import com.team1.healthcare.dto.DiagnosisDTO;
import com.team1.healthcare.dto.PatientsDTO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DiagnosisInfoVO {
  private int diagId;
  private String visitPurpose;
  private String startDate;

  private String patientName;
  private String patientGender;
  private String patientBirth;

  public DiagnosisInfoVO(DiagnosisDTO diagnosisInfo, PatientsDTO patientInfo) {
    this.diagId = diagnosisInfo.getDiagId();
    this.visitPurpose = diagnosisInfo.getVisitPurpose();

    LocalDate originalDate = diagnosisInfo.getStartDate().toLocalDate();
    this.startDate = CommonUtils.dateToString(originalDate);

    this.patientName = patientInfo.getPatientName();
    if (patientInfo.getPatientGender().equals("male")) {
      this.patientGender = "남자";
    }

    if (patientInfo.getPatientGender().equals("female")) {
      this.patientGender = "여자";
    }

    this.patientBirth = CommonUtils.dateToString(patientInfo.getPatientBirth());
  }
}
