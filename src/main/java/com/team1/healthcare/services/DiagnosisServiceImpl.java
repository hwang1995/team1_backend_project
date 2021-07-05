package com.team1.healthcare.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.team1.healthcare.dto.DiagnosisDTO;
import com.team1.healthcare.dto.DiagnosticInspectionsDTO;
import com.team1.healthcare.dto.DiagnosticTestsDTO;
import com.team1.healthcare.dto.MembersDTO;
import com.team1.healthcare.dto.PatientsDTO;
import com.team1.healthcare.vo.auth.UserInfoVO;
import com.team1.healthcare.vo.common.DateWithHospitalCode;
import com.team1.healthcare.vo.common.DateWithMemberVO;
import com.team1.healthcare.vo.common.PatientSearchVO;
import com.team1.healthcare.vo.common.WeekNoWithMemberVO;
import com.team1.healthcare.vo.diagnosis.DiagnosisHistoryVO;
import com.team1.healthcare.vo.diagnosis.DiagnosisListVO;
import com.team1.healthcare.vo.diagnosis.MedicineVO;
import com.team1.healthcare.vo.diagnosis.RegistDiagnosisVO;
import com.team1.healthcare.vo.diagnosis.ReservationVO;
import com.team1.healthcare.vo.diagnostic.DiagnosticVO;
import com.team1.healthcare.vo.patient.PatientVO;

@Service
public class DiagnosisServiceImpl implements IDiagnosisService {

  // ======================== SUNG WOOK HWANG
  @Override
  public List<DiagnosisListVO> showTodayDiagnosisList(UserInfoVO userInfo) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean registDiagnosisInfo(RegistDiagnosisVO diagnosisInfo) {
    // TODO Auto-generated method stub
    return false;
  }


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

  // ======================== SI HYUN PARK

  @Override
  public boolean addReservationInfo(DiagnosisDTO diagnosisInfo) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean modifyReservationInfo(int diagId, String visitPurpose) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean removeReservationInfo(int diagId) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public List<ReservationVO> showWeeklyReservationList(WeekNoWithMemberVO dateInfo) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<ReservationVO> showReservationWaitingList(String hospitalCode) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<MembersDTO> getDoctorsInfo(String hospitalCode) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<PatientsDTO> getPatientsInfo(PatientSearchVO patientInfo) {
    // TODO Auto-generated method stub
    return null;
  }


}
