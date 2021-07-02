package com.team1.healthcare.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Medicines 엔티티는 약 혹은 주사의 목록을 담고 있습니다.
 * 
 * @author sungwookhwang
 *
 */
@Getter
@Setter
@ToString
public class MedicinesDTO {
  // medicines 엔티티의 PK
  private int medicineId;

  // 약품 코드
  private String medicineCode;

  // 약품 영문 명
  private String medicineName;

  // 약품 구분
  private String medicineType;

  // 약 혹은 주사의 단위
  private String medicineUnit;

  // 약 혹은 주사의 상세 설명을 보기 위해 넣음.
  private String medicineExplain;

}
