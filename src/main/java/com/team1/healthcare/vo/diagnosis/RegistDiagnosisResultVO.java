package com.team1.healthcare.vo.diagnosis;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RegistDiagnosisResultVO {
  private int diagId;
  private String drOpinion;
  private boolean isPharmacy;
  private boolean isInjector;
  private boolean isDiagnosticTest;
  private boolean isVital;

  public RegistDiagnosisResultVO(RegistDiagnosisVO diagnosisInfo) {
    this.diagId = diagnosisInfo.getDiagId();
    this.drOpinion = diagnosisInfo.getDrOpinion();

    this.isPharmacy = false;
    this.isInjector = false;
    this.isDiagnosticTest = false;
    this.isVital = false;

    int medicineCount = diagnosisInfo.getMedicines().size();
    int injectorCount = diagnosisInfo.getInjectors().size();
    int diagnosticCount = diagnosisInfo.getDiagnostics().size();

    if (medicineCount > 0) {
      this.isPharmacy = true;
    }

    if (injectorCount > 0) {
      this.isInjector = true;
    }

    if (diagnosticCount > 0) {
      this.isDiagnosticTest = true;
    }

    if (diagnosisInfo.getVital() != null) {
      this.isVital = true;
    }



  }


}
