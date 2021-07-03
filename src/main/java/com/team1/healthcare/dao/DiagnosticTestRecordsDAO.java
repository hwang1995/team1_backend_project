package com.team1.healthcare.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.team1.healthcare.dto.DiagnosticTestRecordsDTO;
import com.team1.healthcare.vo.diagnostic.DiagnosticTestResultVO;

@Mapper
public interface DiagnosticTestRecordsDAO {
  public int addDiagnosticTestRecord(DiagnosticTestRecordsDTO diagnosticInfo);

  public List<DiagnosticTestRecordsDTO> getDiagnosticTestRecordByDiagTestId(int diagTestId);

  public int addDiagnosticTestRecordResult(DiagnosticTestResultVO diagnosticResultInfo);
}
