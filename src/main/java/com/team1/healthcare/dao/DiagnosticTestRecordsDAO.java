package com.team1.healthcare.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.team1.healthcare.dto.DiagnosticTestRecordsDTO;
import com.team1.healthcare.vo.diagnostic.DiagnosticTestResultVO;

@Mapper
public interface DiagnosticTestRecordsDAO {
  /**
   * 진단 검사 상세를 추가하기 위한 쿼리
   * 
   * @param DiagnosticTestRecordsDTO diagnosticInfo
   * @return number (영향받은 행 수를 알기 위해)
   */
  public int addDiagnosticTestRecord(DiagnosticTestRecordsDTO diagnosticInfo);

  /**
   * 진단 검사 상세 ID (식별자)를 통해 진단 검사 상세의 리스트를 가져오기 위한 쿼리
   * 
   * @param int diagTestId
   * @return List<DiagnosticTestRecordsDTO>
   */
  public List<DiagnosticTestRecordsDTO> getDiagnosticTestRecordByDiagTestId(int diagTestId);

  /**
   * 진단 검사 상세의 기록을 추가하기 위한 쿼리
   * 
   * @param DiagnosticTestResultVO diagnosticResultInfo
   * @return number (영향받은 행 수를 알기 위해)
   */
  public int addDiagnosticTestRecordResult(DiagnosticTestResultVO diagnosticResultInfo);


  public int changeStatusToProcessingWithMemberId(DiagnosticTestResultVO diagnosticResultInfo);

  public int changeStatusToPendingWithMemberId(DiagnosticTestResultVO diagnosticResultInfo);


  public int changeStatusToCompletedWithMemberId(DiagnosticTestResultVO diagnosticResultInfo);

  /**
   * 진단 검사 상세의 상태를 진행중으로 바꾸기 위한 쿼리
   * 
   * @param int diagTestRecordId
   * @return number (영향받은 행 수를 알기 위해)
   */
  public int changeStatusToProcessing(int diagTestRecordId);

  /**
   * 진단 검사 상세의 상태를 대기중으로 바꾸기 위한 쿼리
   * 
   * @param int diagTestRecordId
   * @return number (영향받은 행 수를 알기 위해)
   */
  public int changeStatusToPending(int diagTestRecordId);

  /**
   * 진단 검사 상세의 상태를 완료로 바꾸기 위한 쿼리
   * 
   * @param int diagTestRecordId
   * @return number (영향받은 행 수를 알기 위해)
   */
  public int changeStatusToCompleted(int diagTestRecordId);


  public int changeStatusToRegister(int diagTestRecordId);
}
