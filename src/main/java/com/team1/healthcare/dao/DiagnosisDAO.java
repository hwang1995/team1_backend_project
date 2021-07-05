package com.team1.healthcare.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.team1.healthcare.dto.DiagnosisDTO;
import com.team1.healthcare.vo.common.DateWithHospitalAndIdVO;
import com.team1.healthcare.vo.common.WeekNoWithMemberVO;
import com.team1.healthcare.vo.diagnosis.RegistDiagnosisResultVO;

@Mapper
public interface DiagnosisDAO {

  // 의사가 오늘 날짜를 기준으로 진료받을 환자의 리스트를 얻는 법
  public List<DiagnosisDTO> selectDiagnosisListByMemberId(int memberId);

  // 등록된 진료를 수정한다.
  public int addDiagnosisInfo(RegistDiagnosisResultVO diagnosisResult);

  public List<DiagnosisDTO> getCompletedDiagnosisListByPatientId(int patientId);

  public List<DiagnosisDTO> selectDiagnosisListByMemberIdAndWeekNo(WeekNoWithMemberVO weekInfo);

  public List<DiagnosisDTO> getReservationDiagnosisListByHospitalCode(String hospitalCode);

  public int addDiagnosisReservation(DiagnosisDTO diagnosisInfo);

  public int updateDiagnosisReservation(@Param("diagId") int diagId,
      @Param("visitPurpose") String visitPurpose);

  public int deleteDiagnosisReservation(int diagId);

  public DiagnosisDTO getDuplicatedDiagnosisTime(DateWithHospitalAndIdVO dateInfo);

}
