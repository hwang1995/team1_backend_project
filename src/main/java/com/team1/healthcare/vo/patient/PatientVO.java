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
  private String patientAddr1;
  private String patientAddr2;
  private String patientPostal;
  private String recentDate;
  private String hospitalCode;

  public PatientVO(PatientsDTO patientInfo) {
    this.patientId = patientInfo.getPatientId();
    this.patientName = patientInfo.getPatientName();
    this.patientGender = patientInfo.getPatientGender();
    LocalDate convertDate = patientInfo.getPatientBirth();

    this.patientBirth = CommonUtils.dateToString(convertDate);
    this.hospitalCode = patientInfo.getHospitalCode();

    this.patientAddr1 = patientInfo.getPatientAddr1();
    this.patientAddr2 = patientInfo.getPatientAddr2();
    this.patientPostal = patientInfo.getPatientPostal();
    LocalDate oriRecentDate = patientInfo.getRecentDate().toLocalDate();
    this.recentDate = CommonUtils.dateToString(oriRecentDate);


  }


}
