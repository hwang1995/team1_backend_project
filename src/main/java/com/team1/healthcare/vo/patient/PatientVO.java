package com.team1.healthcare.vo.patient;

import java.time.LocalDate;
import com.team1.healthcare.commons.CommonUtils;
import com.team1.healthcare.dto.PatientsDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@ToString
@NoArgsConstructor
@Slf4j
public class PatientVO {
  private int patientId;
  private String patientName;
  private String patientGender;
  private String patientBirth;
  private String hospitalCode;

  public PatientVO(PatientsDTO patientInfo) {
    this.patientId = patientInfo.getPatientId();
    this.patientName = patientInfo.getPatientName();
    this.patientGender = patientInfo.getPatientGender();
    LocalDate convertDate = patientInfo.getPatientBirth().toLocalDate();

    this.patientBirth = CommonUtils.dateToString(convertDate);
    this.hospitalCode = patientInfo.getHospitalCode();
  }


}
