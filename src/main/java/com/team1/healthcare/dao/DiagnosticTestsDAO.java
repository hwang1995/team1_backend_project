package com.team1.healthcare.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.team1.healthcare.dto.DiagnosticTestsDTO;
import com.team1.healthcare.vo.common.DateWithHospitalCode;
import com.team1.healthcare.vo.common.DateWithMemberVO;

@Mapper
public interface DiagnosticTestsDAO {
  /**
   * 진단 검사를 추가하기 위한 쿼리
   * 
   * @param DiagnosticTestsDTO diagnosticTestInfo
   * @return number (영향받은 행 수를 알기 위해)
   */
  public int addDiagnosticTest(DiagnosticTestsDTO diagnosticTestInfo);

  /**
   * 진료의 ID (식별자)로 진단 검사의 목록을 가져오기 위한 쿼리
   * 
   * @param int diagId
   * @return List<DiagnosticTestsDTO>
   */
  public List<DiagnosticTestsDTO> getDiagnosticTestListByDiagId(int diagId);

  /**
   * 임직원의 ID (식별자)로 주별 진단 검사 리스트를 가져오기 위한 쿼리
   * 
   * @param DateWithMemberVO memberInfo
   * @return List<DiagnosticTestsDTO>
   */
  public List<DiagnosticTestsDTO> getWeeklyDiagnosticTestListByMemberId(
      DateWithMemberVO memberInfo);

  /**
   * 병원 코드 (식별자)로 주별 진단 검사 리스트를 가져오기 위한 쿼리
   * 
   * @param DateWithHospitalCode hospitalInfo
   * @return List<DiagnosticTestsDTO>
   */
  public List<DiagnosticTestsDTO> getWeeklyDiagnosticTestListByHospitalCode(
      DateWithHospitalCode hospitalInfo);

  /**
   * 진단 검사의 상태를 완료로 바꾸기 위한 쿼리
   * 
   * @param int diagTestId
   * @return number (영향 받은 행 수를 알기 위해)
   */
  public int completeDiagnosticTest(int diagTestId);

  /**
   * 진단 검사의 상태를 진행중으로 바꾸기 위한 쿼리
   * 
   * @param int diagTestId
   * @return number (영향 받은 행 수를 알기 위해)
   */
  public int processingDiagnosticTest(int diagTestId);

  /**
   * 
   * @param diagTestId
   * @return
   */
  public int pendingDiagnosticTest(int diagTestId);

  public DiagnosticTestsDTO getDiagnosticTestByDiagTestId(int diagTestId);
}
