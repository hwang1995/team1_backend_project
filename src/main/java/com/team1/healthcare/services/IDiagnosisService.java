package com.team1.healthcare.services;

import java.util.List;
import com.team1.healthcare.dto.DiagnosticInspectionsDTO;
import com.team1.healthcare.dto.DiagnosticTestsDTO;
import com.team1.healthcare.vo.auth.UserInfoVO;
import com.team1.healthcare.vo.common.DateWithHospitalCode;
import com.team1.healthcare.vo.common.DateWithMemberVO;
import com.team1.healthcare.vo.common.PatientSearchVO;
import com.team1.healthcare.vo.diagnosis.DiagnosisHistoryVO;
import com.team1.healthcare.vo.diagnosis.DiagnosisListVO;
import com.team1.healthcare.vo.diagnosis.MedicineVO;
import com.team1.healthcare.vo.diagnosis.RegistDiagnosisVO;
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
   * @return List<MedicineVO>
   */
  public List<MedicineVO> searchMedicineList(String medicineName);

  /**
   * 주사의 이름을 검색하면 주사의 대한 리스트를 출력하기 위한 인터페이스
   * 
   * @param medicineName
   * @return List<MedicineVO>
   */
  public List<MedicineVO> searchInjectorList(String medicineName);

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
   * @return List<DiagnosticVO>
   */
  public List<DiagnosticVO> showDiagnosticTestListByDiagId(int diagId);

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


}
