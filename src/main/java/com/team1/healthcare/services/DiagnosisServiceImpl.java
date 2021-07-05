package com.team1.healthcare.services;

import java.util.List;
import org.springframework.stereotype.Service;
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

@Service
public class DiagnosisServiceImpl implements IDiagnosisService {

  @Override
  public List<DiagnosisListVO> showTodayDiagnosisList(UserInfoVO userInfo) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
<<<<<<< HEAD
  public boolean registDiagnosisInfo(RegistDiagnosisVO diagnosisInfo) {
    // TODO Auto-generated method stub
    return false;
  }
=======
  public List<DiagnosisVO> getDiagnosisList(UserInfoVO userInfo) {
    // Diagnosis는 memberId로 식별하기도 하며, 오늘날짜를 가져와야 한다.
    //
    // List<DiagnosisDTO> diagnosisList =
    // diagnosisDAO.selectDiagnosisListByMemberId(userInfo.getMemberId());
    //
    // 보내줄 List 객체 생성
    // List<DiagnosisVO> sendDiagnosisList = new ArrayList<DiagnosisVO>();
    // for (DiagnosisDTO diagnosis : diagnosisList) {
    // // DiagnosisDTO에 있는 patientId로 patient를 조회한다.
    // int patientId = diagnosis.getPatientId();
    // PatientsDTO patientInfo = patientsDAO.selectPatientByPatientId(patientId);
    // DiagnosisVO diagnosisVO = new DiagnosisVO(patientInfo, diagnosis);
    // sendDiagnosisList.add(diagnosisVO);
    // }
    //
    //
    // // DiagnosisDTO에 있는 patientId로 patient를 조회한다.
    //
    //
    // return sendDiagnosisList;
>>>>>>> refs/remotes/origin/origin

  @Override
  public List<MedicineVO> searchMedicineList(String medicineName) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<MedicineVO> searchInjectorList(String medicineName) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<DiagnosticInspectionsDTO> searchDiagnosticListByBundleName(String bundleName) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<DiagnosticInspectionsDTO> searchDiagnosticListByBundleCode(String bundleCode) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<DiagnosisHistoryVO> showDiagnosisHistoryByPatientId(int patientId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean addDiagnosticTest(DiagnosticTestsDTO diagnosticTestInfo) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public List<DiagnosticVO> showDiagnosticTestListByDiagId(int diagId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<DiagnosticVO> showWeeklyDiagnosticTestListByMemberId(DateWithMemberVO memberInfo) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<DiagnosticVO> showWeeklyDiagnosticTestListByHospitalCode(
      DateWithHospitalCode hospitalInfo) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean changeStatusCompleted(int diagTestId) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean changeStatusProcessing(int diagTestId) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean changeStatusPending(int diagTestId) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public List<PatientVO> searchPatientInfoByName(PatientSearchVO patientInfo) {
    // TODO Auto-generated method stub
    return null;
  }


}
