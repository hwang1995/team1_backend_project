package com.team1.healthcare.vo.diagnosis;

import java.time.LocalDateTime;
import java.util.List;
import com.team1.healthcare.dto.DiagnosisDTO;
import com.team1.healthcare.dto.DiagnosticTestRecordsDTO;
import com.team1.healthcare.dto.MedicinesDTO;
import com.team1.healthcare.dto.VitalRecordsDTO;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
public class DiagnosisHistoryVO {
  @NonNull
  private LocalDateTime startDate;

  @NonNull
  private String visitPurpose;

  @NonNull
  private String drOpinion;
  private List<MedicinesDTO> medicines;
  private List<MedicinesDTO> injectors;
  private List<DiagnosticTestRecordsDTO> diagnostics;
  private VitalRecordsDTO vital;

  public DiagnosisHistoryVO(DiagnosisDTO diagnosisInfo, List<MedicinesDTO> medicines,
      List<MedicinesDTO> injectors, List<DiagnosticTestRecordsDTO> diagnostics,
      VitalRecordsDTO vital) {
    this.startDate = diagnosisInfo.getStartDate();
    this.visitPurpose = diagnosisInfo.getVisitPurpose();
    this.drOpinion = diagnosisInfo.getDrOpinion();
    this.medicines = medicines;
    this.injectors = injectors;
    this.diagnostics = diagnostics;
    this.vital = vital;

  }

}
