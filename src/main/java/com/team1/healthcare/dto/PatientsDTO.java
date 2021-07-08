package com.team1.healthcare.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Patients 엔티티는 환자의 정보를 저장합니다.
 * 
 * @author sungwookhwang
 *
 */
@Getter
@Setter
@ToString
public class PatientsDTO {
  // patients 엔티티의 PK
  private int patientId;

  // 환자의 이름
  private String patientName;

  // 환자의 주민등록번호
  private String patientSsn;

  // 환자의 성
  private String patientGender;

  // 환자의 연락처
  private String patientTel;

  // 환자의 상세 주소 1
  private String patientAddr1;

  // 환자의 상세 주소 2
  private String patientAddr2;

  // 환자의 우편번호
  private String patientPostal;

  // 환자의 키
  private int patientHeight;

  // 환자의 몸무게
  private int patientWeight;

  // 환자의 마지막 진료일
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private LocalDateTime recentDate;

  // 환자의 생일
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate patientBirth;

  private String hospitalCode;

  public boolean isNull() {
    Integer height = new Integer(patientHeight);
    Integer weight = new Integer(patientWeight);

    if (height == null || weight == null || patientName == null || patientSsn == null
        || patientGender == null || patientTel == null || patientAddr1 == null
        || patientPostal == null || recentDate == null || patientBirth == null
        || hospitalCode == null) {
      return true;
    }
    return false;
  }

  public boolean isUpdateNull() {
    Integer height = new Integer(patientHeight);
    Integer weight = new Integer(patientWeight);

    if (height == null && weight == null && patientName == null && patientSsn == null
        && patientGender == null && patientTel == null && patientAddr1 == null
        && patientAddr2 == null && patientPostal == null) {
      return true;
    }
    return false;
  }

}
