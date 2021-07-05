package com.team1.healthcare.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DiagnosticTestRecords 엔티티는 특정 하나의 진단 검사 (DiagnosticTest 엔티티) 안에 존재하는 여러 가지 진단 검사 목록들을 기술하기 위해
 * 사용한다.
 * 
 * @author sungwookhwang
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class DiagnosticTestRecordsDTO {
  // diagnostic_test_records 엔티티의 PK
  private int diagTestRecordId;

  // diagnostic_tests 엔티티의 FK (진단 검사 기록을 식별하기 위해)
  private int diagTestId;

  // diagnostic_inspections 엔티티의 FK (진단 검사 상세 항목을 식별하기 위해)
  private int diagInspectionId;

  // 진단 검사 기록의 측정 값
  private double diagTestValue;

  // 진단 검사 기록의 대한 상태
  // - DIAGNOSTIC_PENDING (대기)
  // - DIAGNOSTIC_PROCESSING (진행중)
  // - DIAGNOSTIC_COMPLETED (완료)
  private String diagTestStatus;

  // members 엔티티의 FK (검사자를 식별하기 위해)
  private int inspectorMemberId;

  // 진단 검사를 추가할 떄에는 diagTestId, diagInspectionId 를 세팅한다.
  public DiagnosticTestRecordsDTO(int diagTestId, int diagInspectionId) {
    this.diagTestId = diagTestId;
    this.diagInspectionId = diagInspectionId;
    this.diagTestStatus = "DIAGNOSTIC_PENDING";
  }

}
