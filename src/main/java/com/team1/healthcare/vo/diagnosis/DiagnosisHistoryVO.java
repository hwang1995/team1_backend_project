package com.team1.healthcare.vo.diagnosis;

import java.time.LocalDate;
import java.util.List;
import com.team1.healthcare.commons.CommonUtils;
import com.team1.healthcare.dto.DiagnosisDTO;
import com.team1.healthcare.dto.VitalRecordsDTO;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
public class DiagnosisHistoryVO {
  @NonNull
  private String startDate;

  @NonNull
  private String visitPurpose;

  @NonNull
  private String drOpinion;
  private List<MedicineRecordVO> medicines;
  private List<MedicineRecordVO> injectors;
  private List<DiagnosticTestRecordVO> diagnostics;
  private VitalRecordsDTO vital;

  public DiagnosisHistoryVO(DiagnosisDTO diagnosisInfo, List<MedicineRecordVO> medicines,
      List<MedicineRecordVO> injectors, List<DiagnosticTestRecordVO> diagnostics,
      VitalRecordsDTO vital) {
    LocalDate originalDate = diagnosisInfo.getStartDate().toLocalDate();
    this.startDate = CommonUtils.dateToString(originalDate);
    this.visitPurpose = diagnosisInfo.getVisitPurpose();
    this.drOpinion = diagnosisInfo.getDrOpinion();
    this.medicines = medicines;
    this.injectors = injectors;
    this.diagnostics = diagnostics;
    this.vital = vital;

  }

}
