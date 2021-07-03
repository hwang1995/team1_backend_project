package com.team1.healthcare.vo.diagnostic;

import com.team1.healthcare.dto.DiagnosticTestRecordsDTO;
import com.team1.healthcare.dto.MembersDTO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DiagnosticTestRecordsVO {
  private int diagTestRecordId;
  private int diagTestId;
  private int diagInspectionId;
  private int inspectorMemberId;
  private double diagTestValue;
  private String diagTestStatus;
  private String doctorName;
  private String inspectorName;

  public DiagnosticTestRecordsVO(DiagnosticTestRecordsDTO diagnosticTestInfo, MembersDTO doctorInfo,
      MembersDTO inspectorInfo) {
    this.diagTestRecordId = diagnosticTestInfo.getDiagTestRecordId();
    this.diagTestId = diagnosticTestInfo.getDiagTestId();
    this.diagInspectionId = diagnosticTestInfo.getDiagInspectionId();
    this.inspectorMemberId = diagnosticTestInfo.getInspectorMemberId();
    this.diagTestValue = diagnosticTestInfo.getDiagTestValue();
    this.diagTestStatus = diagnosticTestInfo.getDiagTestStatus();
    this.doctorName = doctorInfo.getMemberName();
    this.inspectorName = doctorInfo.getMemberName();


  }


}
