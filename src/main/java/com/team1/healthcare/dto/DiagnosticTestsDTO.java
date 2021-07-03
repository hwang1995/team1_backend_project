package com.team1.healthcare.dto;

import java.time.LocalDateTime;
import com.team1.healthcare.vo.diagnosis.RegistDiagnosisVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DiagnosticTest 엔티티는 진단 검사 기록의 정보를 담고 있습니다. 연관된 엔티티로는 DiagnosticTestRecord가 있는데 1 : N의 관계를 맺고 있으며
 * 조회 시에 DiagnosticTest의 PK를 통해 특정 일에 맡겨진 진단 검사의 항목들을 확인할 수 있습니다.
 * 
 * @author sungwookhwang
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class DiagnosticTestsDTO {
  // diagnostic_test 엔티티의 PK
  private int diagTestId;

  // members 엔티티의 FK (진단 검사 기록을 처방한 의사를 식별하기 위해)
  private int memberId;

  // patients 엔티티의 FK (진단 검사 기록을 받은 환자를 식별하기 위해)
  private int patientId;

  // 진단 검사 기록의 대한 상태
  // - DIAGNOSTIC_PENDING (대기)
  // - DIAGNOSTIC_PROCESSING (접수)
  // - DIAGNOSTIC_COMPLETED (완료)
  private String inspectionStatus;

  // 진단 검사 기록을 등록한 날짜
  private LocalDateTime createdDate;

  // diagnosis 엔티티의 FK (환자가 어떠한 진료를 받았는지 식별하기 위해)
  private int diagId;

  public DiagnosticTestsDTO(RegistDiagnosisVO diagnosisInfo) {
    this.memberId = diagnosisInfo.getMemberId();
    this.patientId = diagnosisInfo.getPatientId();
    this.diagId = diagnosisInfo.getDiagId();
    this.inspectionStatus = "DIAGNOSTIC_PENDING";
    this.createdDate = LocalDateTime.now();
  }

}
