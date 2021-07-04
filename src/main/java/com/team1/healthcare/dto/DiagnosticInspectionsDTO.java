package com.team1.healthcare.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DiagnosticInspections 엔티티는 진단 검사 검색을 위해 필요한 엔티티입니다.
 * 
 * @author sungwookhwang
 *
 */
@Getter
@Setter
@ToString
public class DiagnosticInspectionsDTO {
  // diagnostic_inspections의 PK
  private int diagInspectionId;

  // 진단 검사의 묶음 코드
  private String bundleCode;

  // 진단 검사의 그룹 명
  private String bundleName;

  // 진단 검사의 처방 코드
  private String presCode;

  // 진단 검사의 처방 명
  private String presName;

  // 진단 검사의 단위
  private String presUnit;

  // 진단 검사의 하한치
  private double presLowerLimit;

  // 진단 검사의 상한치
  private double presUpperLimit;

  // 진단 검사의 용기
  private String presVessel;

  // 진단 검사의 검체명
  private String presSpecimenName;

}
