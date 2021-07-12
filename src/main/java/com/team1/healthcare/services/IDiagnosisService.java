package com.team1.healthcare.services;

import java.util.List;
import com.team1.healthcare.dto.DiagnosisDTO;
import com.team1.healthcare.dto.DiagnosticInspectionsDTO;
import com.team1.healthcare.dto.DiagnosticTestsDTO;
import com.team1.healthcare.dto.MedicinesDTO;
import com.team1.healthcare.dto.MembersDTO;
import com.team1.healthcare.dto.PatientsDTO;
import com.team1.healthcare.vo.auth.UserInfoVO;
import com.team1.healthcare.vo.common.DateWithHospitalCode;
import com.team1.healthcare.vo.common.DateWithMemberVO;
import com.team1.healthcare.vo.common.PatientSearchVO;
import com.team1.healthcare.vo.common.WeekNoWithMemberVO;
import com.team1.healthcare.vo.diagnosis.DiagnosisHistoryVO;
import com.team1.healthcare.vo.diagnosis.DiagnosisListVO;
import com.team1.healthcare.vo.diagnosis.DiagnosisUpdateVO;
import com.team1.healthcare.vo.diagnosis.RegistDiagnosisVO;
import com.team1.healthcare.vo.diagnosis.ReservationVO;
import com.team1.healthcare.vo.diagnostic.DiagnosticTestRecordsVO;
import com.team1.healthcare.vo.diagnostic.DiagnosticTestResultVO;
import com.team1.healthcare.vo.diagnostic.DiagnosticVO;
import com.team1.healthcare.vo.patient.PatientVO;

public interface IDiagnosisService {
  /**
   * 오늘 해당 병원의 해당 의사가 진료를 봐야하는 환자의 리스트를 출력하기 위한 인터페이스
   * 
   * @param UserInfoVO userInfo
   * @return List<DiagnosisListVO>
   */
  public List<DiagnosisListVO> showTodayDiagnosisList(UserInfoVO userInfo);

  /**
   * 해당 병원의 해당 의사가 진료를 등록하기 위한 인터페이스
   * 
   * @param RegistDiagnosisVO diagnosisInfo
   * @return boolean (성공 여부)
   */
  public boolean registDiagnosisInfo(RegistDiagnosisVO diagnosisInfo);

  /**
   * 약품의 이름을 검색하면 약품의 대한 리스트를 출력하기 위한 인터페이스
   * 
   * @param medicineName
   * @return List<MedicinesDTO>
   */
  public List<MedicinesDTO> searchMedicineList(String medicineName);

  /**
   * 주사의 이름을 검색하면 주사의 대한 리스트를 출력하기 위한 인터페이스
   * 
   * @param medicineName
   * @return List<MedicinesDTO>
   */
  public List<MedicinesDTO> searchInjectorList(String medicineName);

  /**
   * 진단 검사의 그룹 명 (bundleName)을 검색하면 진단 검사에 대한 리스트를 출력하기 위한 인터페이스
   * 
   * @param bundleName
   * @return List<DiagnosticInspectionsDTO>
   */
  public List<DiagnosticInspectionsDTO> searchDiagnosticListByBundleName(String bundleName);

  /**
   * 진단 검사의 그룹 코드 (bundleCode)를 검색하면 진단 검사에 대한 리스트를 출력하기 위한 인터페이스
   * 
   * @param bundleCode
   * @return List<DiagnosticInspectionsDTO>
   */
  public List<DiagnosticInspectionsDTO> searchDiagnosticListByBundleCode(String bundleCode);

  /**
   * 환자의 식별자 (patientId)를 통해 진료 기록 리스트를 출력하기 위한 인터페이스
   * 
   * @param patientId
   * @return List<DiagnosisHistoryVO>
   */
  public List<DiagnosisHistoryVO> showDiagnosisHistoryByPatientId(int patientId);

  /**
   * 환자의 진단 검사를 추가하기 위한 인터페이스
   * 
   * @param diagnosticTestInfo
   * @return boolean (성공 여부)
   */
  public boolean addDiagnosticTest(DiagnosticTestsDTO diagnosticTestInfo);

  /**
   * 진료의 식별자 (diagId)를 통해 진단 검사의 상세 리스트를 출력하기 위한 인터페이스
   * 
   * @param diagId
   * @return List<DiagnosticTestRecordsVO>
   */
  public List<DiagnosticTestRecordsVO> showDiagnosticTestListByDiagTestId(int diagTestId);

  /**
   * 날짜(startDate, endDate)와 임직원의 식별자(memberId)를 통해 해당 주의 진단 검사 리스트를 출력하기 위한 인터페이스
   * 
   * @param memberInfo
   * @return List<DiagnosticVO>
   */
  public List<DiagnosticVO> showWeeklyDiagnosticTestListByMemberId(DateWithMemberVO memberInfo);

  /**
   * 날짜(startDate, endDate)와 병원 코드(hosptialCode)를 통해 해당 주의 진단 검사 리스트를 출력하기 위한 인터페이스
   * 
   * @param hospitalInfo
   * @return List<DiagnosticVO>
   */
  public List<DiagnosticVO> showWeeklyDiagnosticTestListByHospitalCode(
      DateWithHospitalCode hospitalInfo);

  /**
   * 진단 검사의 상태를 완료로 바꿔주기 위한 인터페이스 (채혈 완료)
   * 
   * @param diagTestId
   * @return boolean (성공 여부)
   */
  public boolean changeStatusCompleted(int diagTestId);

  /**
   * 진단 검사의 상태를 접수로 바꿔주기 위한 인터페이스 (바코드 출력)
   * 
   * @param diagTestId
   * @return boolean (성공 여부)
   */
  public boolean changeStatusProcessing(int diagTestId);

  /**
   * 진단 검사의 상태를 대기로 바꿔주기 위한 인터페이스 (접수 취소)
   * 
   * @param diagTestId
   * @return boolean (성공 여부)
   */
  public boolean changeStatusPending(int diagTestId);

  /**
   * 병원 정보와 환자의 이름을 통해 환자의 리스트를 출력하기 위한 인터페이스
   * 
   * @param patientInfo
   * @return List<PatientVO>
   */
  public List<PatientVO> searchPatientInfoByName(PatientSearchVO patientInfo);

  /**
   * 진료를 예약하기 위한 서비스 인터페이스
   * 
   * @param DiagnosisDTO diagnosisInfo
   * @return true or false (성공 여부)
   */
  public boolean addReservationInfo(DiagnosisDTO diagnosisInfo);

  /**
   * 진료 정보를 수정하기 위한 서비스 인터페이스
   * 
   * @param int diagId
   * @param String visitPurpose
   * @return true or false (성공 여부)
   */
  public boolean modifyReservationInfo(DiagnosisUpdateVO diagnosisUpdateVO);

  /**
   * 진료 정보를 삭제(예약 상태만 수정) 하기 위한 서비스 인터페이스
   * 
   * @param int diagId
   * @return true or false (성공 여부)
   */
  public boolean removeReservationInfo(int diagId);

  /**
   * 해당 주의 진료 예약 정보를 보여주기 위한 서비스 인터페이스
   * 
   * @param WeekNoWithMemberVO dateInfo
   * @return List<ReservationVO>
   */
  public List<ReservationVO> showWeeklyReservationList(WeekNoWithMemberVO dateInfo);

  /**
   * 해당 병원의 진료 예약 환자를 보여주기 위한 서비스 인터페이스
   * 
   * @param PatientSearchVO patientSearchVO
   * @return List<ReservationVO>
   */
  public List<ReservationVO> showReservationWaitingList(PatientSearchVO patientSearchVO);

  /**
   * 해당 병원의 의사 정보를 가져오기 위한 서비스 인터페이스
   * 
   * @param String hospitalCode
   * @return List<MembersDTO>
   */
  public List<MembersDTO> getDoctorsInfo(String hospitalCode);

  /**
   * 해당 병원의 환자 정보를 가져오기 위한 서비스 인터페이스
   * 
   * @param PatientSearchVO patientInfo
   * @return List<PatientsDTO>
   */
  public List<PatientsDTO> getPatientsInfo(PatientSearchVO patientInfo);

  /**
   * 진단 검사의 결과 값을 추가하기 위한 서비스 인터페이스
   * 
   * @param List<DiagnosticTestResultVO> resultInfo
   * @return true | false
   */
  public boolean changeDiagnosticValue(List<DiagnosticTestResultVO> resultInfo);

  /**
   * 환자의 ID로 진단 검사 목록을 가져오기 위한 서비스 인터페이스
   * 
   * @param int patientId
   * @return List<DiagnosticTestsDTO>
   */
  public List<DiagnosticTestsDTO> getDiagnosticTestsByPatientId(int patientId);


}
