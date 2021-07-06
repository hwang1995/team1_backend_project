package com.team1.healthcare.vo.patient;

import java.time.LocalDateTime;
import com.team1.healthcare.dto.PatientsDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PatientVO {
  private int patientId;
  private String patientName;
  private String patientGender;
  private LocalDateTime patientBirth;
  private String hospitalCode;

  public PatientVO(PatientsDTO patientInfo) {
    this.patientId = patientInfo.getPatientId();
    this.patientName = patientInfo.getPatientName();
    this.patientGender = patientInfo.getPatientGender();
    this.patientBirth = patientInfo.getPatientBirth();
    this.hospitalCode = patientInfo.getHospitalCode();
  }


}
