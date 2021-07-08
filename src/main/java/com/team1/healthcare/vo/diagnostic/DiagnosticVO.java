package com.team1.healthcare.vo.diagnostic;

import java.time.LocalDate;
import com.team1.healthcare.commons.CommonUtils;
import com.team1.healthcare.dto.DiagnosticTestsDTO;
import com.team1.healthcare.dto.MembersDTO;
import com.team1.healthcare.dto.PatientsDTO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DiagnosticVO {
  private int diagTestId;
  private String createdDate;
  private String inspectionStatus;
  private String patientName;

  private String patientBirth;

  private String patientGender;

  private String doctorRoom;

  public DiagnosticVO(DiagnosticTestsDTO diagnosticInfo, MembersDTO memberInfo,
      PatientsDTO patientInfo) {
    this.diagTestId = diagnosticInfo.getDiagTestId();

    this.createdDate = CommonUtils.dateTimeToString(diagnosticInfo.getCreatedDate());
    this.inspectionStatus = diagnosticInfo.getInspectionStatus();
    this.patientName = patientInfo.getPatientName();

    LocalDate originalBirth = patientInfo.getPatientBirth();
    this.patientBirth = CommonUtils.dateToString(originalBirth);
    this.patientGender = patientInfo.getPatientGender();
    this.doctorRoom = memberInfo.getDoctorRoom();

  }
}
