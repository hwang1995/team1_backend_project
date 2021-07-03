package com.team1.healthcare.vo.diagnosis;

import java.time.LocalDateTime;
import com.team1.healthcare.dto.DiagnosisDTO;
import com.team1.healthcare.dto.PatientsDTO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DiagnosisVO {
  // Patients 엔티티
  private int patientId;
  private String patientName;
  private String patientGender;
  private LocalDateTime patientBirth;

  // Diagnosis 엔티티
  private int diagId;
  private LocalDateTime startDate;
  private String visitPurpose;

  public DiagnosisVO(PatientsDTO patientInfo, DiagnosisDTO diagnosisInfo) {
    // Patients 엔티티
    this.patientId = patientInfo.getPatientId();
    this.patientName = patientInfo.getPatientName();
    this.patientGender = patientInfo.getPatientGender();
    this.patientBirth = patientInfo.getPatientBirth();

    // Diagnosis 엔티티
    this.diagId = diagnosisInfo.getDiagId();
    this.startDate = diagnosisInfo.getStartDate();
    this.visitPurpose = diagnosisInfo.getVisitPurpose();
  }

}
