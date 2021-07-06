package com.team1.healthcare.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.team1.healthcare.dao.DiagnosisDAO;
import com.team1.healthcare.dao.DiagnosticInspectionsDAO;
import com.team1.healthcare.dao.DiagnosticTestRecordsDAO;
import com.team1.healthcare.dao.DiagnosticTestsDAO;
import com.team1.healthcare.dao.MedicineRecordsDAO;
import com.team1.healthcare.dao.MedicinesDAO;
import com.team1.healthcare.dao.MembersDAO;
import com.team1.healthcare.dao.PatientsDAO;
import com.team1.healthcare.dao.VitalRecordsDAO;
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
import com.team1.healthcare.vo.diagnosis.RegistDiagnosisVO;
import com.team1.healthcare.vo.diagnosis.ReservationVO;
import com.team1.healthcare.vo.diagnostic.DiagnosticVO;
import com.team1.healthcare.vo.patient.PatientVO;

@Service
public class DiagnosisServiceImpl implements IDiagnosisService {

  @Autowired
  private DiagnosisDAO diagnosisDAO;

  @Autowired
  private PatientsDAO patientsDAO;

  @Autowired
  private MembersDAO membersDAO;

  @Autowired
  private DiagnosticTestsDAO diagnosticTestsDAO;

  @Autowired
  private DiagnosticTestRecordsDAO diagnosticTestRecordsDAO;

  @Autowired
  private MedicinesDAO medicinesDAO;

  @Autowired
  private MedicineRecordsDAO medicinesRecordsDAO;

  @Autowired
  private VitalRecordsDAO vitalRecordsDAO;

  @Autowired
  private DiagnosticInspectionsDAO diagnosticInspectionsDAO;



  // ======================== SUNG WOOK HWANG
  @Override
  public List<DiagnosisListVO> showTodayDiagnosisList(UserInfoVO userInfo) {
    // TODO 검색 시점 (오늘) 해당 병원의 해당 의사가 진료를 봐야하는 환자의 리스트를 리턴하는 것이 목표
    // 협력 객체 : DiagnosisDAO, PatientsDAO

    // 1. UserInfoVO가 올바르게 들어왔는지 검증한다. (객체가 null인지 아닌지)
    // 1.1 만약 UserInfoVO 객체 중 null인 필드가 존재한다면 BadRequestException을 터트린다.
    // 2. 우선 DiagnosisDAO의 selectDiagnosisListByMemberId(int memberId)를 통해 진료 리스트를 받아온다.
    // 2.1 만약 List<DiagnosisDTO>의 값이 존재하지 않는다면? NoContentExcption을 터트린다.
    // 3. List<DiagnosisDTO>의 값을 loop 하면서 DiagnosisDTO에 있는 patientId로 PatientsDTO를 받아온다.
    // 3.1 만약 PatientsDTO의 값이 존재하지 않는다면? ConflictRequestException을 터트린다.
    // 3.2 존재한다면? DiagnosisListVO 객체를 생성하여 DiagnosisDTO와 PatientsDTO를 생성자의 인자로 넣어준다.
    // 3.3 정상적으로 객체가 생성되었다면 List에 add 해준다.
    // 4. 모든 작업이 끝났다면? List<DiagnosisListVO>를 리턴한다.

    return null;
  }

  @Override
  public boolean registDiagnosisInfo(RegistDiagnosisVO diagnosisInfo) {
    // TODO 해당 병원의 해당 의사가 진료를 등록 (수정) 하여 성공 여부를 알려주는 것이 목표
    // 협력 객체 : PatientsDAO, MembersDAO, DiagnosisDAO, DiagnosticsDAO, MedicinesDAO, VitalRecordsDAO

    // 1. RegistDiagnosisVO의 값이 올바르게 들어왔는지 검증한다. (객체가 null이 아닌지)
    // 1.1 만약 RegistDiagnosisVO 객체 중 null인 필드가 존재한다면 BadRequestException을 터트린다.
    // 2. RegistDiagnosisVO에 들어 있는 diagId를 통하여 RegistDiagnosisResultVO
    // 2. 객체의 생성자에 RegistDiagnosisVO를 인자로 받아 객체를 생성한다.
    // 3. DiagnosisDAO의 addDiagnosisInfo(RegistDiagnosisResultVO diagnosisResult)로 update를 한다.
    // 3.1 만약 생성에 실패하였다면 ConflictRequestException을 터트린다.
    // 4. RegistDiagnosisVO에 medicines의 값이 0 이상이라면, List<MedicineVO>의 값을 loop하면서
    // 4. MedicineRecordsDAO의 addMedicineRecord(MedicineResultVO medicineInfo)의 값을 insert 한다.
    // 4.1 만약 생성에 실패하였다면 ConflictRequestException을 터트린다.
    // 5. RegistDiagnosisVO의 injectors의 값이 0 이상이라면, List<MedicineVO>의 값을 loop하면서
    // 5. MedicineRecordsDAO의 addMedicineRecord(MedicineResultVO medicineInfo)의 값을 insert 한다.
    // 5.1 만약 생성에 실패하였다면 ConflictRequestException을 터트린다.
    // 6. RegistDiagnosisVO의 diagnostics의 값이 0 이상이라면, 우선 DiagnosticTestDAO의
    // addDiagnosticTest(DiagnosticTestsDTO diagnosticTestInfo)를 생성해준다.
    // 7. 만약 생성에 실패하였다면 ConflictRequestException을 터트린다.
    // 8. RegistDiagnosisVO의 diagnostics의 값을 loop하면서, DiagnosticTestDTO에 있는 diagTestId를 통하여
    // DiagnosticTestRecordsDTO에 생성 인자로 (int diagTestId,
    // int diagInspectionId)를 넣어서 객체를 생성한다.
    // 9. DiagnosticTestRecordsDAO의 addDiagnosticTestRecord(DiagnosticTestRecordsDTO
    // diagnosticInfo)의 값을 insert 한다.
    // 9.1 만약 생성에 실패하였다면 ConflictRequestException을 터트린다.
    // 10. RegistDiagnosisVO의 vital이 null이 아니라면, VitalResultVO 객체를 생성하기 위해 VitalRecordsDAO의
    // addVitalRecord(VitalResultVO vitalInfo)의 값을 insert 한다.
    // 10.1 만약 생성에 실패하였다면 ConflictRequestException을 터트린다.
    // 11. true를 반환한다.

    return false;
  }


  @Override
  public List<MedicinesDTO> searchMedicineList(String medicineName) {
    // TODO 약품의 이름(medicineName)을 검색하면 약품에 대한 리스트를 리턴하는 것이 목표
    // 협력 객체 : MedicinesDAO

    // 1. medicineName이 null이 아닌지 검증한다.
    // 1.1 만약 medicineName의 값이 null이라면, BadRequestException을 터트린다.
    // 2. MedicinesDAO의 getMedicineInfoByMedicineName(String medicineName)으로 값을 가져온다.
    // 2.1 만약 List<MedicinesDTO>의 값이 0이라면, NoContentException을 터트린다.
    // 3. List<MedicinesDTO>를 반환한다.

    return null;
  }

  @Override
  public List<MedicinesDTO> searchInjectorList(String medicineName) {
    // TODO 주사의 이름(medicineName)을 검색하면 주사에 대한 리스트를 리턴하는 것이 목표
    // 협력 객체 : MedicinesDAO

    // 1. medicineName이 null이 아닌지 검증한다.
    // 1.1 만약 medicineName의 값이 null이라면, BadRequestException을 터트린다.
    // 2. MedicineDAO의 getInjectorInfoByMedicineName(String medicineName)으로 값을 가져온다.
    // 2.1 만약 List<MedicinesDTO>의 값이 0이라면, NoContentException을 터트린다.
    // 3. List<MedicinesDTO>를 반환한다.

    return null;
  }

  @Override
  public List<DiagnosticInspectionsDTO> searchDiagnosticListByBundleName(String bundleName) {
    // TODO 그룹 명(bundleName)으로 검색하면 진단 검사에 대한 리스트를 리턴하는 것이 목표
    // 협력 객체 : DiagnosticInspectionsDAO

    // 1. bundleName이 null이 아닌지 검증한다.
    // 1.1 만약 bundleName의 값이 null이라면, BadRequestException을 터트린다.
    // 2. DiagnosticInspectionsDAO의 selectInspectionListByBundleName(String bundleName)으로 값을 가져온다.
    // 2.1 만약 List<DiagosticInspectionsDTO>의 값이 0이라면, NoContentException을 터트린다.
    // 3. List<DiagnoticInspectionsDTO>를 반환한다.


    return null;
  }

  @Override
  public List<DiagnosticInspectionsDTO> searchDiagnosticListByBundleCode(String bundleCode) {
    // TODO 그룹 코드(bundleCode)로 검색하면 진단 검사에 대한 리스트를 리턴하는 것이 목표
    // 협력 객체 : DiagnosticInspectionsDAO

    // 1. bundleCode가 null이 아닌지 검증한다.
    // 1.1 만약 bundleCode의 값이 null이라면, BadRequestException을 터트린다.
    // 2. DiagnosticInspectionsDAO의 selectInspectionListByBundleCode(String bundleCode)으로 값을 가져온다.
    // 2.1 만약 List<DiagnosticInspectionsDTO>의 값이 0이라면, NoContentException을 터트린다.
    // 3. List<DiagnosticInspectionsDTO>를 반환한다.

    return null;
  }

  @Override
  public List<DiagnosisHistoryVO> showDiagnosisHistoryByPatientId(int patientId) {
    // TODO 환자의 식별자(patientId)를 통해 진료 기록 리스트를 리턴하는 것이 목표
    // 협력 객체 : DiagnosisDAO, MedicinesDAO, MedicinesRecordsDAO, DiagnosticTestRecordsDAO,
    // VitalRecordsDAO

    // 1. patientId의 값이 0 혹은 null 아닌지 검증한다.
    // 1.1 만약 patientId의 값이 0 혹은 null이라면, BadRequestException을 터트린다.
    // 2. DiagnosisDAO의 getCompletedDiagnosisListByPatientId(int patientId)로 값을 가져온다.
    // 2.1 만약 List<DiagnosisDTO>의 크기가 0이라면, NoContentException을 터트린다.

    // 3. List<DiagnosisDTO>를 loop 하면서, diagId를 통해
    // 3. medicineType이 '내복약' 혹은 '외용약'인 경우를 조회해야 하기 때문에 MedicineRecordsDAO의 select
    // PharmaciesByDiagId(int diagId)로 조회한다.
    // 3.1 없으면 스킵한다.

    // 4. medicineType이 '주사약'인 경우를 조회해야 하기 때문에 MedicineRecordsDAO의 selectInjectorsByDiagId(int
    // diagId)로 조회한다.
    // 4.1 없으면 스킵한다.

    // 5. '내복약' 혹은 '외용약'인 경우에 List<MedicineRecordsVO> 를 만들어야 하기 떄문에 받아온 List를 looping 한다.
    // 5.1 MedicinesRecordsDTO가 가지고 있는 데이터인 medicineId로 MedicinesDAO의
    // getMedicineInfoByMedicineId(int medicineId)로 조회한다.
    // 5.2 만약 MedicinesDTO를 가지고 있지 않다면, NotFoundException을 터트린다.
    // 5.3 정상적으로 가지고 있다면, MedicineRecordsVO(MedicinesDTO, MedicinesRecordsDTO)로 객체를 생성한다.

    // 6. '주사약'인 경우에 List<MedicineRecordsVO>를 만들어야 하기 때문에 받아온 List를 looping한다.
    // 6.1 MedicinesRecordsDTO가 가지고 있는 데이터인 medicineId로 MedicinesDAO의
    // getMedicineInfoByMedicineId(int medicineId)로 조회한다.
    // 6.2 만약 MedicinesDTO를 가지고 있지 않다면, NotFoundException을 터트린다.

    // 7. 진단 검사 기록을 가져와야 하기 때문에 diagId를 통해 DiagnosticTestsDAO의 getDiagnosticTestByDiagId로
    // DiagnosticTestDTO를 가져온다.
    // 7.1 만약 값이 존재하지 않는다면, 스킵한다.
    // 7.2 값이 존재한다면, DiagnosticTestRecordsDAO의 getDiagnosticTestRecordByDiagTestId(int diagTestId)로
    // 조회한다.
    // 7.2 List<DiagnosticTestRecordsDTO>의 값이 존재하지 않는다면, NotFoundException을 터트린다.
    // 7.3 존재한다면 DiagnosticTestRecords를 Looping 하는데 이 때에 가지고 있는 diagInspectionId로 Diagnosis 정보를
    // 가져온다.
    // 7.3 값이 존재하지 않는다면, NotFoundException을 터트린다.

    // 8. VitalRecords가 존재하는지 확인한다.
    // 9. 결과값을 가지고 DiagnosisHistoryVO를 만들고 List에 추가한다.
    // 10. 반복 후 결과를 반환한다.

    return null;
  }

  @Override
  public boolean addDiagnosticTest(DiagnosticTestsDTO diagnosticTestInfo) {
    // TODO 환자가 진료시에 진단 검사를 요청한다면 진단 검사를 추가하고 성공 여부를 리턴하는 것이 목표
    // 협력 객체 : DiagnosticTestsDAO

    // 1. diagnosticTestInfo의 값이 null인지 체크한다.
    // 1.1 만약 diagnosticTestInfo의 값이 null이면, BadRequestException을 터트린다.
    // 2. DiagnosticTestsDAO의 addDiagnosticTest(DiagnosticTestsDTO diagnosticTestInfo)로 insert를 한다.
    // 2.1 만약 정상적으로 삽입되지 않았다면, ConflictRequestException을 터트린다.
    // 3. true를 반환한다.

    return false;
  }

  @Override
  public List<DiagnosticVO> showDiagnosticTestListByDiagId(int diagId) {
    // TODO 진료의 식별자 (diagId)로 해당 환자의 진단 검사 상세 리스트를 리턴하는 것이 목표
    // 협력 객체 : DiagnosticTestDAO, MembersDAO, PatientsDAO

    // 1. diagId가 0 혹은 null이라면, BadRequestException을 터트린다.
    // 2. diagId를 통해 DiagnosticTestsDAO의 getDiagnosticTestListByDiagId(int diagId)를 조회한다.
    // 3. 없으면 NoContentException을 터트린다.
    // 4. 존재한다면, DiagnosticTestDTO의 memberId, patientId를 가져와서
    // 4.1 MembersDAO의 selectMemberInfoByMemberId(int memberId)로 값을 가져온다.
    // 4.2 PatientsDAO의 selectPatientsByPatientId(int patientId)로 값을 가져온다.
    // 4.3 MembersDTO와 PatientsDTO의 값이 존재하지 않는 경우 NotFoundException을 터트린다.
    // 5. DiagnosticVO 객체를 생성하여, DiagnosticVO(DiagnosticTestsDTO, MembersDTO, PatientsDTO) 객체를 생성한다.
    // 6. 만들어진 객체를 List에 추가해준다.
    // 7. Looping을 돌면서, 끝이 났다면 List를 반환한다.

    return null;
  }

  @Override
  public List<DiagnosticVO> showWeeklyDiagnosticTestListByMemberId(DateWithMemberVO memberInfo) {
    // TODO 임직원의 식별자 (memberId)와 날짜(startDate, endDate)로 해당 주의 진단 검사 리스트를 리턴하는 것이 목표
    // 협력 객체 : DiagnosticTestDAO, MembersDAO, PatientsDAO

    // 1. DateWithMemberVO의 값이 null이라면, BadRequestException을 터트린다.
    // 2. DiagnosticTestsDAO의 getWeeklyDiagnosticTestListByMemberId(DateWithMemberVO memberInfo)를
    // 조회한다.
    // 2.1 값이 존재하지 않으면 NoContentException을 터트린다.
    // 4. 존재한다면, DiagnosticTestDTO의 memberId, patientId를 가져와서
    // 4.1 MembersDAO의 selectMemberInfoByMemberId(int memberId)로 값을 가져온다.
    // 4.2 PatientsDAO의 selectPatientsByPatientId(int patientId)로 값을 가져온다.
    // 4.3 MembersDTO와 PatientsDTO의 값이 존재하지 않는 경우 NotFoundException을 터트린다.
    // 5. DiagnosticVO 객체를 생성하여, DiagnosticVO(DiagnosticTestsDTO, MembersDTO, PatientsDTO) 객체를 생성한다.
    // 6. 만들어진 객체를 List에 추가해준다.
    // 7. Looping을 돌면서, 끝이 났다면 List를 반환한다.

    return null;
  }

  @Override
  public List<DiagnosticVO> showWeeklyDiagnosticTestListByHospitalCode(
      DateWithHospitalCode hospitalInfo) {
    // TODO 병원의 식별자 (hospitalCode)와 날짜(startDate, endDate)로 해당 주의 진단 검사 리스트를 리턴하는 것이 목표
    // 협력 객체 : DiagnosticTestDAO, MembersDAO, PatientsDAO

    // 1. DateWithHospitalCode의 값이 null이라면, BadRequestException을 터트린다.
    // 2. DiagnosticTestsDAO의 getWeeklyDiagnosticTestListByHospitalCode(DateWithHospitalCode
    // hospitalInfo)를 조회한다.
    // 2.1 값이 존재하지 않으면 NoContentException을 터트린다.
    // 4. 존재한다면, DiagnosticTestDTO의 memberId, patientId를 가져와서
    // 4.1 MembersDAO의 selectMemberInfoByMemberId(int memberId)로 값을 가져온다.
    // 4.2 PatientsDAO의 selectPatientsByPatientId(int patientId)로 값을 가져온다.
    // 4.3 MembersDTO와 PatientsDTO의 값이 존재하지 않는 경우 NotFoundException을 터트린다.
    // 5. DiagnosticVO 객체를 생성하여, DiagnosticVO(DiagnosticTestsDTO, MembersDTO, PatientsDTO) 객체를 생성한다.
    // 6. 만들어진 객체를 List에 추가해준다.
    // 7. Looping을 돌면서, 끝이 났다면 List를 반환한다.
    return null;
  }

  @Override
  public boolean changeStatusCompleted(int diagTestId) {
    // TODO 진단 검사의 상태를 완료(DIAGNOSTIC_COMPLETED)로 변경하고 성공 여부를 리턴하는 것이 목표
    // 협력 객체 : DiagnosticTestsDAO

    // 1. diagTestId가 0이라면, BadRequestException을 터트린다.
    // 2. 아니라면, DiagnosticTestsDAO의 completeDiagnosticTest(int diagTestId)를 실행한다.
    // 3. 만약 실패했다면 ConflictRequestException을 터트린다.
    // 4. true를 반환한다.

    return false;
  }

  @Override
  public boolean changeStatusProcessing(int diagTestId) {
    // TODO 진단 검사의 상태를 진행중 | 접수 (DIAGNOSTIC_PROCESSING)로 변경하고 성공 여부를 리턴하는 것이 목표
    // 협력 객체 : DiagnosticTestDAO

    // 1. diagTestId가 0이라면, BadRequestException을 터트린다.
    // 2. 아니라면, DiagnosticTestsDAO의 processingDiagnosticTest(int diagTestId)를 실행한다.
    // 3. 만약 실패했다면 ConflictRequestException을 터트린다.
    // 4. true를 반환한다.

    return false;
  }

  @Override
  public boolean changeStatusPending(int diagTestId) {
    // TODO 진단 검사의 상태를 대기중 (DIAGNOSTIC_PENDING)으로 변경하고 성공 여부를 리턴하는 것이 목표
    // 협력 객체 : DiagnosticTestDAO

    // 1. diagTestId가 0이라면, BadRequestException을 터트린다.
    // 2. 아니라면 DiagnosticTestsDAO의 pendingDiagnosticTest(int diagTestId)를 실행한다.
    // 3. 만약 실패했다면, ConflictRequestException을 터트린다.
    // 4. true를 반환한다.
    return false;
  }

  @Override
  public List<PatientVO> searchPatientInfoByName(PatientSearchVO patientInfo) {
    // TODO 해당 병원의 해당 환자의 이름으로 검색하여 환자의 리스트를 리턴하는 것이 목표
    // 협력 객체 : PatientsDAO

    // 1. patientInfo가 null이라면, BadRequestException을 터트린다.
    // 2. 아니라면 PatientsDAO의 getPatientInfoByName(PatientSearchVO patientSearchInfo)를 실행한다.
    // 3. 만약 값이 존재하지 않는다면, NoContentException을 터트린다.
    // 4. List<PatientVO>를 반환한다.

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
