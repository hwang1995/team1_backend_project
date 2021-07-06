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

  /**
   * 환자의 완료된 진료 목록을 가지고 오기 위한 쿼리
   * 
   * @param int patientId
   * @return List<DiagnosisDTO>
   */
  public List<DiagnosisDTO> getCompletedDiagnosisListByPatientId(int patientId);

  /**
   * 임직원의 ID (식별자)와 weekNo을 통해 진료 리스트를 가지고 오기 위한 쿼리
   * 
   * @param WeekNoWithMemberVO weekInfo
   * @return List<DiagnosisDTO>
   */
  public List<DiagnosisDTO> selectDiagnosisListByMemberIdAndWeekNo(WeekNoWithMemberVO weekInfo);

  /**
   * 병원 코드 (식별자)를 통해 예약 진료 리스트를 보여주기 위한 쿼리
   * 
   * @param String hospitalCode
   * @return List<DiagnosisDTO>
   */
  public List<DiagnosisDTO> getReservationDiagnosisListByHospitalCode(String hospitalCode);

  /**
   * 진료를 예약하기 위한 쿼리
   * 
   * @param DiagnosisDTO diagnosisInfo
   * @return number (영향받은 행 수를 알기 위해)
   */
  public int addDiagnosisReservation(DiagnosisDTO diagnosisInfo);

  /**
   * 예약 상태(방문 목적) 를 바꾸기 위한 쿼리
   * 
   * @param int diagId
   * @param String visitPurpose
   * @return number (영향받은 행 수를 알기 위해)
   */
  public int updateDiagnosisReservation(@Param("diagId") int diagId,
      @Param("visitPurpose") String visitPurpose);

  /**
   * 진료 예약을 지우기 위한 쿼리
   * 
   * @param int diagId
   * @return number (영향받은 행 수를 알기 위해)
   */
  public int deleteDiagnosisReservation(int diagId);

  /**
   * 예약 시에 겹치는 시간이 있는치 체크하기 위한 쿼리
   * 
   * @param DateWithHospitalAndIdVO dateInfo
   * @return DiagnosisDTO
   */
  public DiagnosisDTO getDuplicatedDiagnosisTime(DateWithHospitalAndIdVO dateInfo);



}
