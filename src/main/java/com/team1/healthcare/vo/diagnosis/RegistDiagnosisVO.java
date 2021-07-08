package com.team1.healthcare.vo.diagnosis;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class RegistDiagnosisVO {
  // 환자의 PK
  private int patientId;

  // 임직원 (의사)의 PK
  private int memberId;

  // 진료의 PK
  private int diagId;

  // 병원 코드
  private String hospitalCode;

  // 의사 의견
  private String drOpinion;

  // 약품 정보들
  private List<MedicineVO> medicines;

  // 주사 정보들
  private List<MedicineVO> injectors;

  // 진단 검사 정보들
  private List<Integer> diagnostics;

  // 바이탈 검사 정보
  private VitalVO vital;

  @JsonIgnore
  public boolean isNull() {
    if (patientId == 0 || memberId == 0 || diagId == 0 || hospitalCode == null
        || drOpinion == null) {
      log.info("errorRegistVO");
      return true;
    }
    return false;
  }
}
