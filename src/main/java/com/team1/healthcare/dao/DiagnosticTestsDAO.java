package com.team1.healthcare.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.team1.healthcare.dto.DiagnosticTestsDTO;
import com.team1.healthcare.vo.common.DateWithHospitalCode;
import com.team1.healthcare.vo.common.DateWithMemberVO;

@Mapper
public interface DiagnosticTestsDAO {
  public int addDiagnosticTest(DiagnosticTestsDTO diagnosticTestInfo);

  public List<DiagnosticTestsDTO> getDiagnosticTestListByDiagId(int diagId);

  public List<DiagnosticTestsDTO> getWeeklyDiagnosticTestListByMemberId(
      DateWithMemberVO memberInfo);

  public List<DiagnosticTestsDTO> getWeeklyDiagnosticTestListByHospitalCode(
      DateWithHospitalCode hospitalInfo);
}
