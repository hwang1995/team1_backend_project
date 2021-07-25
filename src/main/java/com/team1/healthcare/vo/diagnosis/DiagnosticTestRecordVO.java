package com.team1.healthcare.vo.diagnosis;

import com.team1.healthcare.dto.DiagnosticInspectionsDTO;
import com.team1.healthcare.dto.DiagnosticTestRecordsDTO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DiagnosticTestRecordVO {
  // DiagnosticTestRecordsDTO
  private double diagTestValue;
  private String diagTestStatus;

  // DiagnosticInspectionsDTO
  private String bundleCode;
  private String bundleName;
  private String presCode;
  private String presName;
  private String presUnit;
  private double presLowerLimit;
  private double presUpperLimit;

  public DiagnosticTestRecordVO(DiagnosticTestRecordsDTO diagnosticTestInfo,
      DiagnosticInspectionsDTO diagnosticInfo) {
    this.diagTestValue = diagnosticTestInfo.getDiagTestValue();
    this.diagTestStatus = diagnosticTestInfo.getDiagTestStatus();

    this.bundleCode = diagnosticInfo.getBundleCode();
    this.bundleName = diagnosticInfo.getBundleName();
    this.presCode = diagnosticInfo.getPresCode();
    this.presName = diagnosticInfo.getPresName();
    this.presUnit = diagnosticInfo.getPresUnit();
    this.presLowerLimit = diagnosticInfo.getPresLowerLimit();
    this.presUpperLimit = diagnosticInfo.getPresUpperLimit();
  }
}
