package com.team1.healthcare.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Hospitals 엔티티는 병원에 대한 정보를 담고 있습니다.
 * 
 * @author sungwookhwang
 *
 */
@Getter
@Setter
@ToString
public class HospitalsDTO {
  // hospital 엔티티의 PK
  private String hospitalCode;

  // 병원 이름
  private String hospitalName;

  // 병원 연락처
  private String hospitalTel;

  // 병원 주소
  private String hospitalAddress;
}
