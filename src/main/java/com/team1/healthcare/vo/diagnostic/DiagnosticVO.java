package com.team1.healthcare.vo.diagnostic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.team1.healthcare.dto.DiagnosticTestsDTO;
import com.team1.healthcare.dto.MembersDTO;
import com.team1.healthcare.dto.PatientsDTO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DiagnosticVO {
  private int diagTestId;
  private LocalDateTime createdDate;
  private String inspectionStatus;
  private String patientName;
  private LocalDate patientBirth;
  private String patientGender;

  private String doctorRoom;

  public DiagnosticVO(DiagnosticTestsDTO diagnosticInfo, MembersDTO memberInfo,
      PatientsDTO patientInfo) {
    this.diagTestId = diagnosticInfo.getDiagTestId();
    this.createdDate = diagnosticInfo.getCreatedDate();
    this.inspectionStatus = diagnosticInfo.getInspectionStatus();
    this.patientName = patientInfo.getPatientName();
    this.patientBirth = patientInfo.getPatientBirth();
    this.patientGender = patientInfo.getPatientGender();
    this.doctorRoom = memberInfo.getDoctorRoom();

  }
}
