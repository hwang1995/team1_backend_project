package com.team1.healthcare.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
import com.team1.healthcare.dto.DiagnosticTestRecordsDTO;
import com.team1.healthcare.dto.DiagnosticTestsDTO;
import com.team1.healthcare.dto.MedicineRecordsDTO;
import com.team1.healthcare.dto.MedicinesDTO;
import com.team1.healthcare.dto.MembersDTO;
import com.team1.healthcare.dto.PatientsDTO;
import com.team1.healthcare.dto.VitalRecordsDTO;
import com.team1.healthcare.exception.BadRequestException;
import com.team1.healthcare.exception.ConflictRequestException;
import com.team1.healthcare.exception.NoContentException;
import com.team1.healthcare.exception.NotFoundException;
import com.team1.healthcare.vo.auth.UserInfoVO;
import com.team1.healthcare.vo.common.DateWithHospitalCode;
import com.team1.healthcare.vo.common.DateWithMemberVO;
import com.team1.healthcare.vo.common.PatientSearchVO;
import com.team1.healthcare.vo.common.WeekNoWithMemberVO;
import com.team1.healthcare.vo.diagnosis.DiagnosisHistoryVO;
import com.team1.healthcare.vo.diagnosis.DiagnosisListVO;
import com.team1.healthcare.vo.diagnosis.DiagnosticTestRecordVO;
import com.team1.healthcare.vo.diagnosis.MedicineRecordVO;
import com.team1.healthcare.vo.diagnosis.MedicineResultVO;
import com.team1.healthcare.vo.diagnosis.MedicineVO;
import com.team1.healthcare.vo.diagnosis.RegistDiagnosisResultVO;
import com.team1.healthcare.vo.diagnosis.RegistDiagnosisVO;
import com.team1.healthcare.vo.diagnosis.ReservationVO;
import com.team1.healthcare.vo.diagnosis.VitalResultVO;
import com.team1.healthcare.vo.diagnosis.VitalVO;
import com.team1.healthcare.vo.diagnostic.DiagnosticTestRecordsVO;
import com.team1.healthcare.vo.diagnostic.DiagnosticVO;
import com.team1.healthcare.vo.patient.PatientVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = {Exception.class, RuntimeException.class})
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

    // 사용자 정보가 없는 경우
    if (userInfo.isNull()) {
      throw new BadRequestException("UserInfoVO의 값이 정상적으로 들어오지 않았습니다.",
          new Throwable("no_user_info"));
    }

    List<DiagnosisDTO> diagnosisList =
        diagnosisDAO.selectDiagnosisListByMemberId(userInfo.getMemberId());

    // 검색 결과가 없는 경우
    if (diagnosisList.size() == 0) {
      throw new NoContentException("검색 시점에 해당 병원의 진료 목록이 존재하지 않습니다.",
          new Throwable("no_diagnosis_list"));
    }

    List<DiagnosisListVO> result = new ArrayList<DiagnosisListVO>();

    // 순회를 통해 List를 만들어 준다.
    diagnosisList.forEach(data -> {
      int patientId = data.getPatientId();
      PatientsDTO patientInfo = patientsDAO.selectPatientByPatientId(patientId);

      if (patientInfo == null) {
        throw new ConflictRequestException("환자의 정보가 존재하지 않습니다", new Throwable("no_patient_info"));
      }
      DiagnosisListVO diagnosisInfo = new DiagnosisListVO(patientInfo, data);
      result.add(diagnosisInfo);

    });

    return result;
  }

  @Override

  public boolean registDiagnosisInfo(RegistDiagnosisVO diagnosisInfo) {
    // 진료 정보가 존재하지 않는 경우
    if (diagnosisInfo.isNull()) {
      log.error("Exception throwed");
      throw new BadRequestException("진료 등록 정보가 올바르지 않습니다. 다시 시도해주세요.",
          new Throwable("no_diagnosis_info"));
    }

    RegistDiagnosisResultVO registDiagnosisInfo = new RegistDiagnosisResultVO(diagnosisInfo);
    int registResult = diagnosisDAO.addDiagnosisInfo(registDiagnosisInfo);

    // 진료가 정상적으로 업데이트 되지 않는 경우
    if (registResult != 1) {
      throw new ConflictRequestException("알 수 없는 이유로 진료가 등록되지 않았습니다. 다시 시도해주세요. ",
          new Throwable("not_updated_diagnosis_info"));
    }

    List<MedicineVO> medicineInfo = diagnosisInfo.getMedicines();
    List<MedicineVO> injectorInfo = diagnosisInfo.getInjectors();
    List<Integer> diagnosticsInfo = diagnosisInfo.getDiagnostics();
    VitalVO vitalInfo = diagnosisInfo.getVital();

    // 약품의 대한 정보가 존재할 경우 Insert
    if (medicineInfo.size() > 0) {
      log.info("medicineInfoIn");
      medicineInfo.forEach(medicine -> {
        MedicineResultVO sendMedicineInfo = new MedicineResultVO(medicine, diagnosisInfo);
        int count = medicinesRecordsDAO.addMedicineRecord(sendMedicineInfo);
        if (count != 1) {
          throw new ConflictRequestException("알 수 없는 이유료 진료가 등록되지 않았습니다.",
              new Throwable("not_updated_medicines_info"));
        }
      });
    }

    if (injectorInfo.size() > 0) {
      log.info("InjectorInf");
      injectorInfo.forEach(injector -> {
        MedicineResultVO sendInjectorInfo = new MedicineResultVO(injector, diagnosisInfo);
        int count = medicinesRecordsDAO.addMedicineRecord(sendInjectorInfo);
        if (count != 1) {
          throw new ConflictRequestException("알 수 없는 이유료 진료가 등록되지 않았습니다.",
              new Throwable("not_updated_injectors_info"));
        }
      });
    }

    if (diagnosticsInfo.size() > 0) {
      // 우선 진료를 만듭시다.
      DiagnosticTestsDTO diagnosticTestInfo = new DiagnosticTestsDTO(diagnosisInfo);
      diagnosticTestsDAO.addDiagnosticTest(diagnosticTestInfo);
      diagnosticsInfo.forEach(diagnostics -> {
        int diagTestId = diagnosticTestInfo.getDiagTestId();
        log.debug("Registering diagnosticTest Id is " + diagTestId);
        int diagInspectionId = diagnostics;
        DiagnosticTestRecordsDTO diagnosticTestRecordInfo =
            new DiagnosticTestRecordsDTO(diagTestId, diagInspectionId);
        int count = diagnosticTestRecordsDAO.addDiagnosticTestRecord(diagnosticTestRecordInfo);

        if (count != 1) {
          throw new ConflictRequestException("알 수 없는 이유로 진료가 등록되지 않았습니다.",
              new Throwable("not_updated_diagnostics_info"));
        }
      });
    }

    if (vitalInfo != null) {
      VitalResultVO vitalResultInfo = new VitalResultVO(vitalInfo, diagnosisInfo);
      int vitalCount = vitalRecordsDAO.addVitalRecord(vitalResultInfo);

      if (vitalCount != 1) {
        throw new ConflictRequestException("알 수 없는 이유로 진료가 등록되지 않았습니다.",
            new Throwable("not_updated_vital_info"));
      }
    }


    return true;
  }


  @Override
  public List<MedicinesDTO> searchMedicineList(String medicineName) {

    if (medicineName == null || medicineName.trim().isEmpty()) {
      throw new BadRequestException("약품 이름이 입력되지 않았거나, 공백입니다.", new Throwable("no_medicine_name"));
    }

    List<MedicinesDTO> medicinesInfo = medicinesDAO.getMedicineInfoByMedicineName(medicineName);

    if (medicinesInfo.size() == 0 || medicinesInfo == null) {
      throw new NoContentException("약품이 존재하지 않습니다.", new Throwable("no_medicines_content"));
    }

    return medicinesInfo;
  }

  @Override
  public List<MedicinesDTO> searchInjectorList(String medicineName) {

    if (medicineName == null || medicineName.trim().isEmpty()) {
      throw new BadRequestException("약품 이름이 입력되지 않았거나, 공백입니다.", new Throwable("no_medicine_name"));
    }

    List<MedicinesDTO> injectorsInfo = medicinesDAO.getInjectorInfoByMedicineName(medicineName);

    if (injectorsInfo.size() == 0 || injectorsInfo == null) {
      throw new NoContentException("약품이 존재하지 않습니다.", new Throwable("no_medicines_content"));
    }

    return injectorsInfo;
  }

  @Override
  @Cacheable(value = "bundleName", cacheManager = "userCacheManager")
  public List<DiagnosticInspectionsDTO> searchDiagnosticListByBundleName(String bundleName) {

    log.info(bundleName);
    if (bundleName == null || bundleName.trim().isEmpty()) {
      throw new BadRequestException("그룹 명이 입력되지 않았거나, 공백입니다.", new Throwable("no_bundle_name"));
    }

    List<DiagnosticInspectionsDTO> diagnosticInspectionsInfo =
        diagnosticInspectionsDAO.selectInspectionListByBundleName(bundleName);

    if (diagnosticInspectionsInfo.size() == 0 || diagnosticInspectionsInfo == null) {
      throw new NoContentException("진단 검사가 존재하지 않습니다.",
          new Throwable("no_diagnostic_inspection_content"));
    }

    return diagnosticInspectionsInfo;
  }

  @Override
  public List<DiagnosticInspectionsDTO> searchDiagnosticListByBundleCode(String bundleCode) {

    if (bundleCode == null) {
      throw new BadRequestException("그룹 코드가 입력되지 않았습니다.", new Throwable("no_bundle_code"));
    }

    List<DiagnosticInspectionsDTO> diagnosticInspectionsInfo =
        diagnosticInspectionsDAO.selectInspectionListByBundleCode(bundleCode);

    if (diagnosticInspectionsInfo.size() == 0 || diagnosticInspectionsInfo == null) {
      throw new NoContentException("진단 검사가 존재하지 않습니다.",
          new Throwable("no_diagnostic_inspection_content"));
    }

    return diagnosticInspectionsInfo;
  }

  @Override
  public List<DiagnosisHistoryVO> showDiagnosisHistoryByPatientId(int patientId) {
    // TODO 환자의 식별자(patientId)를 통해 진료 기록 리스트를 리턴하는 것이 목표
    // 협력 객체 : DiagnosisDAO, MedicinesDAO, MedicinesRecordsDAO, DiagnosticTestRecordsDAO,
    // VitalRecordsDAO

    if (patientId == 0 || Integer.toString(patientId) == null) {
      throw new BadRequestException("회원의 식별자가 입력되지 않았습니다.", new Throwable("no_patient_id"));
    }

    List<DiagnosisDTO> completedInfo = diagnosisDAO.getCompletedDiagnosisListByPatientId(patientId);

    log.info(completedInfo.size() + "");
    if (completedInfo.size() == 0 || completedInfo == null) {
      throw new NoContentException("회원이 진료를 받은 기록이 존재하지 않습니다.",
          new Throwable("no_patient_history"));
    }

    List<DiagnosisHistoryVO> historyInfo = new ArrayList<DiagnosisHistoryVO>();

    completedInfo.forEach(info -> {
      // 진료의 식별자
      int diagId = info.getDiagId();
      List<MedicineRecordsDTO> medicineRecordInfo =
          medicinesRecordsDAO.selectPharmaciesByDiagId(diagId);

      // 보내 줄 정보 내복 약 혹은 외용약의 정보
      List<MedicineRecordVO> medicineRecordResult = new ArrayList<>();
      if (medicineRecordInfo.size() > 0) {

        medicineRecordInfo.forEach(medicineRecord -> {
          int medicineId = medicineRecord.getMedicineId();
          MedicinesDTO medicineInfo = medicinesDAO.getMedicineInfoByMedicineId(medicineId);
          MedicineRecordVO result = new MedicineRecordVO(medicineInfo, medicineRecord);
          medicineRecordResult.add(result);
        });
      }

      List<MedicineRecordsDTO> injectorRecordInfo =
          medicinesRecordsDAO.selectInjectorsByDiagId(diagId);

      // 보내 줄 정보 주사약의 정보
      List<MedicineRecordVO> injectorRecordResult = new ArrayList<>();

      if (injectorRecordInfo.size() > 0) {
        injectorRecordInfo.forEach(injectorRecord -> {
          int medicineId = injectorRecord.getMedicineId();
          MedicinesDTO injectorInfo = medicinesDAO.getMedicineInfoByMedicineId(medicineId);
          MedicineRecordVO result = new MedicineRecordVO(injectorInfo, injectorRecord);
          injectorRecordResult.add(result);
        });
      }

      DiagnosticTestsDTO diagnosticInfo = diagnosticTestsDAO.getDiagnosticTestListByDiagId(diagId);

      // 보내 줄 진단 검사 정보
      List<DiagnosticTestRecordVO> diagnosticTestResult = new ArrayList<>();

      if (diagnosticInfo != null) {
        int diagTestId = diagnosticInfo.getDiagTestId();
        List<DiagnosticTestRecordsDTO> diagnosticTestRecordInfo =
            diagnosticTestRecordsDAO.getDiagnosticTestRecordByDiagTestId(diagTestId);

        diagnosticTestRecordInfo.forEach(diagnosticTest -> {
          int diagInspectionId = diagnosticTest.getDiagInspectionId();
          DiagnosticInspectionsDTO diagInspectionInfo =
              diagnosticInspectionsDAO.selectInspectionByDiagInspectionId(diagInspectionId);
          DiagnosticTestRecordVO result =
              new DiagnosticTestRecordVO(diagnosticTest, diagInspectionInfo);
          diagnosticTestResult.add(result);
        });

      }

      VitalRecordsDTO vitalInfo = vitalRecordsDAO.selectVitalRecordByDiagId(diagId);

      DiagnosisHistoryVO sendResult = new DiagnosisHistoryVO(info, medicineRecordResult,
          injectorRecordResult, diagnosticTestResult, vitalInfo);
      historyInfo.add(sendResult);

    });


    return historyInfo;
  }

  @Override
  public boolean addDiagnosticTest(DiagnosticTestsDTO diagnosticTestInfo) {
    // TODO 환자가 진료시에 진단 검사를 요청한다면 진단 검사를 추가하고 성공 여부를 리턴하는 것이 목표
    // 협력 객체 : DiagnosticTestsDAO

    if (diagnosticTestInfo == null) {
      throw new BadRequestException("진단 검사의 정보가 존재하지 않습니다.",
          new Throwable("no_diagnostic_test_info"));
    }
    int affectedRow = diagnosticTestsDAO.addDiagnosticTest(diagnosticTestInfo);

    if (affectedRow != 1) {
      throw new ConflictRequestException("알 수 없는 이유로 진단 검사 추가에 실패하였습니다.",
          new Throwable("no_insert_diagnostic_test"));
    }

    return true;

  }

  @Override
  public List<DiagnosticTestRecordsVO> showDiagnosticTestListByDiagTestId(int diagTestId) {
    // TODO 진료의 식별자 (diagId)로 해당 환자의 진단 검사 상세 리스트를 리턴하는 것이 목표
    // 협력 객체 : DiagnosticInspectionsDAO, DiagnosticTestRecordsDAO, MembersDAO

    if (diagTestId == 0) {
      throw new BadRequestException("올바른 진단 검사 ID를 입력해주세요.", new Throwable("no_diag_test_id"));
    }

    DiagnosticTestsDTO memberInfo = diagnosticTestsDAO.getDiagnosticTestByDiagTestId(diagTestId);

    int doctorId = memberInfo.getMemberId();
    List<DiagnosticTestRecordsDTO> diagTestList =
        diagnosticTestRecordsDAO.getDiagnosticTestRecordByDiagTestId(diagTestId);

    if (diagTestList.size() == 0 || diagTestList == null) {
      throw new NoContentException("진단 검사가 존재하지 않습니다.", new Throwable("no_result"));
    }

    MembersDTO doctorInfo = membersDAO.selectMemberInfoByMemberId(doctorId);

    List<DiagnosticTestRecordsVO> sendResult = new ArrayList<>();
    diagTestList.forEach(diagTest -> {
      log.info(diagTest.toString());
      int inspectionId = diagTest.getDiagInspectionId();
      int inspectorMemberId = diagTest.getInspectorMemberId();

      DiagnosticInspectionsDTO diagnosticInfo =
          diagnosticInspectionsDAO.selectInspectionByDiagInspectionId(inspectionId);
      if (inspectorMemberId == 0) {
        DiagnosticTestRecordsVO result =
            new DiagnosticTestRecordsVO(diagnosticInfo, diagTest, doctorInfo);
        sendResult.add(result);
      } else {
        MembersDTO inspectorInfo = membersDAO.selectMemberInfoByMemberId(inspectorMemberId);

        // log.info(inspectorInfo.toString());
        DiagnosticTestRecordsVO result =
            new DiagnosticTestRecordsVO(diagnosticInfo, diagTest, doctorInfo, inspectorInfo);
        sendResult.add(result);
      }



    });


    return sendResult;
  }

  @Override
  public List<DiagnosticVO> showWeeklyDiagnosticTestListByMemberId(DateWithMemberVO memberInfo) {

    if (memberInfo.isNull()) {
      throw new BadRequestException("올바른 정보를 입력해주세요", new Throwable("no_date_with_member_info"));
    }

    List<DiagnosticTestsDTO> weeklyList =
        diagnosticTestsDAO.getWeeklyDiagnosticTestListByMemberId(memberInfo);

    if (weeklyList.size() == 0 || weeklyList == null) {
      throw new NoContentException("이번주에는 진단 검사가 존재하지 않습니다.", new Throwable("no_contents"));
    }

    List<DiagnosticVO> weeklyDiagnosticList = new ArrayList<>();

    weeklyList.forEach(info -> {
      int memberId = info.getMemberId();
      int patientId = info.getPatientId();
      MembersDTO diagMemberInfo = membersDAO.selectMemberInfoByMemberId(memberId);
      PatientsDTO diagPatientInfo = patientsDAO.selectPatientByPatientId(patientId);

      if (diagMemberInfo == null || diagPatientInfo == null) {
        throw new NotFoundException("임직원과 환자 정보가 존재하지 않습니다.",
            new Throwable("no_member_and_patient"));
      }

      DiagnosticVO result = new DiagnosticVO(info, diagMemberInfo, diagPatientInfo);
      weeklyDiagnosticList.add(result);

    });



    return weeklyDiagnosticList;
  }

  @Override
  public List<DiagnosticVO> showWeeklyDiagnosticTestListByHospitalCode(
      DateWithHospitalCode hospitalInfo) {
    // TODO 병원의 식별자 (hospitalCode)와 날짜(startDate, endDate)로 해당 주의 진단 검사 리스트를 리턴하는 것이 목표
    // 협력 객체 : DiagnosticTestDAO, MembersDAO, PatientsDAO

    if (hospitalInfo.isNull()) {
      throw new BadRequestException("올바른 정보를 입력해주세요.", new Throwable("no_date_with_hospital_info"));
    }

    List<DiagnosticTestsDTO> weeklyList =
        diagnosticTestsDAO.getWeeklyDiagnosticTestListByHospitalCode(hospitalInfo);

    if (weeklyList.size() == 0 || weeklyList == null) {
      throw new NoContentException("이번 주에는 진단 검사가 존재하지 않습니다.", new Throwable("no_contents"));
    }

    List<DiagnosticVO> weeklyDiagnosticList = new ArrayList<>();

    weeklyList.forEach(info -> {
      int memberId = info.getMemberId();
      int patientId = info.getPatientId();

      MembersDTO diagMemberInfo = membersDAO.selectMemberInfoByMemberId(memberId);
      PatientsDTO diagPatientInfo = patientsDAO.selectPatientByPatientId(patientId);

      log.info(diagMemberInfo.toString());
      if (diagMemberInfo == null || diagPatientInfo == null) {
        throw new NotFoundException("임직원과 환자 정보가 존재하지 않습니다.",
            new Throwable("no_member_and_patient"));
      }

      DiagnosticVO result = new DiagnosticVO(info, diagMemberInfo, diagPatientInfo);
      weeklyDiagnosticList.add(result);

    });
    return weeklyDiagnosticList;
  }

  @Override
  public boolean changeStatusCompleted(int diagTestId) {
    // TODO 진단 검사의 상태를 완료(DIAGNOSTIC_COMPLETED)로 변경하고 성공 여부를 리턴하는 것이 목표
    // 협력 객체 : DiagnosticTestsDAO

    if (diagTestId == 0) {
      throw new BadRequestException("올바르지 않은 진단 검사의 식별자입니다.", new Throwable("not_diag_test_id"));
    }

    int result = diagnosticTestsDAO.completeDiagnosticTest(diagTestId);

    if (result != 1) {
      throw new ConflictRequestException("진단 검사의 상태가 변경되지 않았습니다.",
          new Throwable("not_updated_diag_test_status"));
    }

    return true;
  }

  @Override
  public boolean changeStatusProcessing(int diagTestId) {
    // TODO 진단 검사의 상태를 진행중 | 접수 (DIAGNOSTIC_PROCESSING)로 변경하고 성공 여부를 리턴하는 것이 목표
    // 협력 객체 : DiagnosticTestDAO

    if (diagTestId == 0) {
      throw new BadRequestException("올바르지 않은 진단 검사의 식별자입니다.", new Throwable("not_diag_test_id"));
    }

    int result = diagnosticTestsDAO.processingDiagnosticTest(diagTestId);

    if (result != 1) {
      throw new ConflictRequestException("진단 검사의 상태가 변경되지 않았습니다.",
          new Throwable("not_updated_diag_test_status"));
    }

    return true;

  }

  @Override
  public boolean changeStatusPending(int diagTestId) {
    // TODO 진단 검사의 상태를 대기중 (DIAGNOSTIC_PENDING)으로 변경하고 성공 여부를 리턴하는 것이 목표
    // 협력 객체 : DiagnosticTestDAO

    if (diagTestId == 0) {
      throw new BadRequestException("올바르지 않은 진단 검사의 식별자입니다.", new Throwable("not_diag_test_id"));
    }

    int result = diagnosticTestsDAO.pendingDiagnosticTest(diagTestId);

    if (result != 1) {
      throw new ConflictRequestException("진단 검사의 상태가 변경되지 않았습니다.",
          new Throwable("not_updated_diag_test_status"));
    }

    return true;
  }

  @Override
  public List<PatientVO> searchPatientInfoByName(PatientSearchVO patientInfo) {
    // TODO 해당 병원의 해당 환자의 이름으로 검색하여 환자의 리스트를 리턴하는 것이 목표
    // 협력 객체 : PatientsDAO
    if (patientInfo.isNull()) {
      throw new BadRequestException("병원 코드 혹은 환자의 이름이 없습니다.",
          new Throwable("no_patient_search_info"));
    }

    List<PatientsDTO> patientInfos = patientsDAO.getPatientInfoByName(patientInfo);

    if (patientInfos.size() == 0 || patientInfos == null) {
      throw new NoContentException("검색 결과 존재하지 않습니다.", new Throwable("no_result"));
    }

    List<PatientVO> returnResults = new ArrayList<PatientVO>();

    patientInfos.forEach(info -> {
      PatientVO newData = new PatientVO(info);
      returnResults.add(newData);
    });

    return returnResults;
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
