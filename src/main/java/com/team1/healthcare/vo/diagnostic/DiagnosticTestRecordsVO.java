package com.team1.healthcare.vo.diagnostic;

import com.team1.healthcare.dto.DiagnosticInspectionsDTO;
import com.team1.healthcare.dto.DiagnosticTestRecordsDTO;
import com.team1.healthcare.dto.MembersDTO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DiagnosticTestRecordsVO {
  // DiagnosticInspections
  private String bundleCode;
  private String bundleName;
  private String presCode;
  private String presName;
  private String presVessel;
  private String presSpecimenName;

  // DiagnosticTestRecordsVO
  private String diagTestAvgValue;

  // DiagnosticTestRecords
  private int diagTestRecordId;
  private double diagTestValue;
  private String diagTestStatus;

  // Members - 의사
  private String doctorName;

  // Members - 검사자
  private String inspectorName;

  public DiagnosticTestRecordsVO(DiagnosticInspectionsDTO diagnosticInfo,
      DiagnosticTestRecordsDTO diagnosticRecordInfo, MembersDTO doctorInfo,
      MembersDTO inspectorInfo) {
    // DiagnosticInspectionsDTO
    this.bundleCode = diagnosticInfo.getBundleCode();
    this.bundleName = diagnosticInfo.getBundleName();
    this.presCode = diagnosticInfo.getPresCode();
    this.presName = diagnosticInfo.getPresName();
    this.presVessel = diagnosticInfo.getPresVessel();
    this.presSpecimenName = diagnosticInfo.getPresSpecimenName();

    // DiagnosticTestRecordsDTO
    this.diagTestRecordId = diagnosticRecordInfo.getDiagTestRecordId();
    this.diagTestValue = diagnosticRecordInfo.getDiagTestValue();
    this.diagTestStatus = diagnosticRecordInfo.getDiagTestStatus();

    this.doctorName = doctorInfo.getMemberName();
    this.inspectorName = inspectorInfo.getMemberName();

    double presLowerLimit = diagnosticInfo.getPresLowerLimit();
    double presUpperLimit = diagnosticInfo.getPresUpperLimit();
    String presUnit = diagnosticInfo.getPresUnit();

    double presAvgValue = (presLowerLimit + presUpperLimit) / 2;
    this.diagTestAvgValue = String.format("%.2f", presAvgValue) + " " + presUnit;

  }


}
