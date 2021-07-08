package com.team1.healthcare.vo.diagnosis;

import java.time.LocalDate;
import com.team1.healthcare.commons.CommonUtils;
import com.team1.healthcare.dto.DiagnosisDTO;
import com.team1.healthcare.dto.PatientsDTO;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
@NonNull
public class DiagnosisListVO {
  // Patients 엔티티
  private int patientId;
  private String patientName;
  private String patientGender;

  private String patientBirth;

  // Diagnosis 엔티티
  private int diagId;
  private String startDate;
  private String visitPurpose;

  public DiagnosisListVO(PatientsDTO patientInfo, DiagnosisDTO diagnosisInfo) {
    // Patients 엔티티
    this.patientId = patientInfo.getPatientId();
    this.patientName = patientInfo.getPatientName();
    this.patientGender = patientInfo.getPatientGender();
    LocalDate originalBirth = patientInfo.getPatientBirth();
    this.patientBirth = CommonUtils.dateToString(originalBirth);

    // Diagnosis 엔티티
    this.diagId = diagnosisInfo.getDiagId();
    this.startDate = CommonUtils.dateTimeToString(diagnosisInfo.getStartDate());
    this.visitPurpose = diagnosisInfo.getVisitPurpose();
  }

}
