package com.team1.healthcare.services;


import java.time.LocalDateTime;
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
import com.team1.healthcare.vo.common.DateWithHospitalAndIdVO;
import com.team1.healthcare.vo.common.DateWithHospitalCode;
import com.team1.healthcare.vo.common.DateWithMemberVO;
import com.team1.healthcare.vo.common.PatientSearchVO;
import com.team1.healthcare.vo.common.WeekNoWithMemberVO;
import com.team1.healthcare.vo.diagnosis.DiagnosisHistoryVO;
import com.team1.healthcare.vo.diagnosis.DiagnosisInfoVO;
import com.team1.healthcare.vo.diagnosis.DiagnosisListVO;
import com.team1.healthcare.vo.diagnosis.DiagnosisUpdateVO;
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
import com.team1.healthcare.vo.diagnostic.DiagnosticTestResultVO;
import com.team1.healthcare.vo.diagnostic.DiagnosticVO;
import com.team1.healthcare.vo.patient.PatientVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
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
      throw new NotFoundException("검색 시점에 해당 병원의 진료 목록이 존재하지 않습니다.",
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
      throw new NotFoundException("약품이 존재하지 않습니다.", new Throwable("no_medicines_content"));
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
      throw new NotFoundException("약품이 존재하지 않습니다.", new Throwable("no_medicines_content"));
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
      throw new NotFoundException("진단 검사가 존재하지 않습니다.",
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
      throw new NotFoundException("진단 검사가 존재하지 않습니다.",
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

    if (completedInfo.size() == 0 || completedInfo == null) {
      throw new NotFoundException("회원이 진료를 받은 기록이 존재하지 않습니다.", new Throwable("no_patient_history"));
    }
    completedInfo.forEach(info -> {
      log.info(info.toString());
    });
    List<DiagnosisHistoryVO> historyInfo = new ArrayList<DiagnosisHistoryVO>();

    completedInfo.forEach(info -> {
      // 진료의 식별자
      log.info(info.getDiagId() + "");
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
      log.info("실행이 될까요?");
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
      throw new NotFoundException("해당 주에는 진단 검사가 존재하지 않습니다. 기간 : "
          + hospitalInfo.getStartDate().toString() + " ~ " + hospitalInfo.getEndDate().toString(),
          new Throwable("no_contents"));
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
  public List<DiagnosticVO> showDiagnosticTestListByPatientId(int patientId) {
    // TODO 환자의 ID로 진단 검사의 리스트를 리턴하는 것이 목표
    if (patientId == 0) {
      throw new BadRequestException("올바른 정보를 입력해주세요.", new Throwable("no_patient_id_info"));
    }

    List<DiagnosticTestsDTO> patientTestInfo =
        diagnosticTestsDAO.getDiagnosticTestByPatientId(patientId);

    if (patientTestInfo.size() == 0 || patientTestInfo == null) {
      throw new NotFoundException("해당 환자의 진단 검사 기록이 존재하지 않습니다.",
          new Throwable("no_patient_test_info"));
    }

    List<DiagnosticVO> patientDiagnosticTestList = new ArrayList<>();

    patientTestInfo.forEach(info -> {
      int memberId = info.getMemberId();

      MembersDTO diagMemberInfo = membersDAO.selectMemberInfoByMemberId(memberId);
      PatientsDTO diagPatientInfo = patientsDAO.selectPatientByPatientId(patientId);

      if (diagMemberInfo == null || diagPatientInfo == null) {
        throw new NotFoundException("임직원과 환자 정보가 존재하지 않습니다.",
            new Throwable("no_member_and_patient"));
      }

      DiagnosticVO result = new DiagnosticVO(info, diagMemberInfo, diagPatientInfo);
      patientDiagnosticTestList.add(result);

    });

    return patientDiagnosticTestList;
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
  public boolean changeStatusRegister(int diagTestId) {
    if (diagTestId == 0) {
      throw new BadRequestException("올바르지 않은 진단 검사의 식별자입니다.", new Throwable("not_diag_test_id"));
    }

    int result = diagnosticTestsDAO.registerDiagnosticTest(diagTestId);

    if (result != 1) {
      throw new ConflictRequestException("진단 검사의 상태가 변경되지 않았습니다.",
          new Throwable("not_updated_diag_test_status"));
    }

    return true;
  }

  @Override
  public boolean changeStatusCompletedDiagTestRecord(int diagTestRecordId) {
    if (diagTestRecordId == 0) {
      throw new BadRequestException("올바르지 않은 진단 검사의 식별자입니다.",
          new Throwable("not_diag_test_record_id"));
    }

    int result = diagnosticTestRecordsDAO.changeStatusToCompleted(diagTestRecordId);

    if (result != 1) {
      throw new ConflictRequestException("진단 검사의 상태가 변경되지 않았습니다.",
          new Throwable("not_updated_diag_test_record_status"));
    }

    return true;
  }

  @Override
  public boolean changeStatusPendingDiagTestRecord(int diagTestRecordId) {
    if (diagTestRecordId == 0) {
      throw new BadRequestException("올바르지 않은 진단 검사의 식별자입니다.",
          new Throwable("not_diag_test_record_id"));
    }

    int result = diagnosticTestRecordsDAO.changeStatusToPending(diagTestRecordId);

    if (result != 1) {
      throw new ConflictRequestException("진단 검사의 상태가 변경되지 않았습니다.",
          new Throwable("not_updated_diag_test_record_status"));
    }

    return true;
  }

  @Override
  public boolean changeStatusProcessingDiagTestRecord(int diagTestRecordId) {
    if (diagTestRecordId == 0) {
      throw new BadRequestException("올바르지 않은 진단 검사의 식별자입니다.",
          new Throwable("not_diag_test_record_id"));
    }

    int result = diagnosticTestRecordsDAO.changeStatusToProcessing(diagTestRecordId);

    if (result != 1) {
      throw new ConflictRequestException("진단 검사의 상태가 변경되지 않았습니다.",
          new Throwable("not_updated_diag_test_record_status"));
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
      throw new NotFoundException("검색 결과 존재하지 않습니다.", new Throwable("no_result"));
    }

    List<PatientVO> returnResults = new ArrayList<PatientVO>();

    patientInfos.forEach(info -> {
      PatientVO newData = new PatientVO(info);
      returnResults.add(newData);
    });

    return returnResults;
  }

  @Override
  public boolean changeDiagnosticValue(List<DiagnosticTestResultVO> resultInfo) {
    // TODO 진단 검사 상세의 결과를 바꾸는 것이 목표
    if (resultInfo.size() == 0) {
      throw new BadRequestException("진단 검사의 기록이 들어온 것이 없습니다.",
          new Throwable("no_diagnostic_test_result"));
    }

    resultInfo.forEach(result -> {
      int affectedRow = diagnosticTestRecordsDAO.addDiagnosticTestRecordResult(result);
      if (affectedRow != 1) {
        throw new ConflictRequestException("알 수 없는 이유로 인해 진단 검사의 등록에 실패하였습니다.",
            new Throwable("not_updated_diagnostic_test_result"));
      }

    });

    return true;
  }

  @Override
  public List<DiagnosticTestsDTO> getDiagnosticTestsByPatientId(int patientId) {
    // TODO: 환자의 ID로 진단 검사의 목록을 가져오기
    if (patientId == 0) {
      throw new BadRequestException("잘못된 환자의 식별자가 입력되었습니다.", new Throwable("no_patient_id"));
    }

    List<DiagnosticTestsDTO> patientInfo =
        diagnosticTestsDAO.getDiagnosticTestByPatientId(patientId);

    if (patientInfo.size() == 0) {
      throw new NotFoundException("환자가 진단 검사를 받은 기록이 존재하지 않습니다.", new Throwable("no_result"));
    }

    return patientInfo;
  }

  @Override
  public boolean changeStatusToProcessingWithMemberId(List<DiagnosticTestResultVO> diagnosticInfo) {
    if (diagnosticInfo.size() == 0 || diagnosticInfo == null) {
      throw new BadRequestException("잘못된 진단 검사의 값이 들어왔습니다.", new Throwable("no_diagnostic_info"));
    }

    diagnosticInfo.forEach(diagnostic -> {
      int affectedRow = diagnosticTestRecordsDAO.changeStatusToProcessingWithMemberId(diagnostic);
      if (affectedRow != 1) {
        throw new ConflictRequestException("알 수 없는 이유로 변경되지 않았습니다.",
            new Throwable("conflict_diagnostic_info"));
      }
    });

    return true;
  }

  @Override
  public boolean changeStatusToPendingWithMemberId(List<DiagnosticTestResultVO> diagnosticInfo) {
    if (diagnosticInfo.size() == 0 || diagnosticInfo == null) {
      throw new BadRequestException("잘못된 진단 검사의 값이 들어왔습니다.", new Throwable("no_diagnostic_info"));
    }

    diagnosticInfo.forEach(diagnostic -> {
      int affectedRow = diagnosticTestRecordsDAO.changeStatusToPendingWithMemberId(diagnostic);
      if (affectedRow != 1) {
        throw new ConflictRequestException("알 수 없는 이유로 변경되지 않았습니다.",
            new Throwable("conflict_diagnostic_info"));
      }
    });

    return true;
  }

  @Override
  public boolean changeStatusToCompletedWithMemberId(List<DiagnosticTestResultVO> diagnosticInfo) {
    if (diagnosticInfo.size() == 0 || diagnosticInfo == null) {
      throw new BadRequestException("잘못된 진단 검사의 값이 들어왔습니다.", new Throwable("no_diagnostic_info"));
    }

    diagnosticInfo.forEach(diagnostic -> {
      int affectedRow = diagnosticTestRecordsDAO.changeStatusToCompletedWithMemberId(diagnostic);
      if (affectedRow != 1) {
        throw new ConflictRequestException("알 수 없는 이유로 변경되지 않았습니다.",
            new Throwable("conflict_diagnostic_info"));
      }
    });

    return true;
  }



  // ======================== SI HYUN PARK
  // 예약 접수를 하기 위한 메소드
  @Override
  public boolean addReservationInfo(DiagnosisDTO diagnosisInfo) {

    boolean result = true;
    LocalDateTime currentTime = LocalDateTime.now();
    // 추가하가전 몇가지 확인 작업절차
    // 1) weekNo가 0초과 52이하 범위에 있는지 확인하기 위한 작업
    if (diagnosisInfo.getWeekNo() < 0 || diagnosisInfo.getWeekNo() > 52) {
      throw new BadRequestException("잘못된 데이터 정보입니다. 올바른 데이터인지 확인해주세요",
          new Throwable("Wrong Data : WeekNo"));
    }

    // 2)-1 수정작업 들어가기전 진료할 환자가 해당병원에 있는지 확인
    PatientsDTO patient = patientsDAO.selectPatientByPatienIdAndHospitalCode(
        diagnosisInfo.getPatientId(), diagnosisInfo.getHospitalCode());
    log.info(patient.toString());
    if (patient == null) {
      throw new ConflictRequestException("해당 병원에 환자가 존재하지 않습니다. 다시 확인해주세요",
          new Throwable("Wrong Update : No Patient Data in hospital"));
    }
    // 2)-2 수정작업 들어가기전 진료할 의사가 해당병원에 있는지 확인
    MembersDTO member = membersDAO.selectMemberInfoByMemberIdAndHospitalCode(
        diagnosisInfo.getMemberId(), diagnosisInfo.getHospitalCode());

    if (member == null) {
      throw new ConflictRequestException("해당 병원에 의사가 존재하지 않습니다. 다시 확인해주세요",
          new Throwable("Wrong Update : No Doctor Data in hospital"));
    }

    // 3) 환자가 예약한 시간에 같은 환자가 예약되어 있는지 확인하는 작업
    DateWithHospitalAndIdVO dateWithHospitalAndIdVO =
        new DateWithHospitalAndIdVO(diagnosisInfo.getStartDate(), diagnosisInfo.getPatientId(),
            diagnosisInfo.getHospitalCode());

    // 4) 예약정보를 확인하기전, 예약 확인을 위한 데이터가 올바르지 않다면, 에러를 발생시킨다
    if (dateWithHospitalAndIdVO.isNull()) {
      throw new BadRequestException("잘못된 데이터 정보입니다. 올바른 데이터인지 확인해주세요",
          new Throwable("Wrong data : dateWithHospitalAndIdVO value is Null"));
    }

    // 5) 예약시간이 현재 날짜 기준으로 과거일때 에러를 발생시킨다
    if (diagnosisInfo.getStartDate().isBefore(currentTime)) {
      throw new ConflictRequestException("예약할 수 없는 시간입니다.", new Throwable("Fail ReservationTime"));
    }

    // 6) 예약이 되어 있다는 것을 확인하기 위한 작업
    DiagnosisDTO diagnosisDTO = diagnosisDAO.getDuplicatedDiagnosisTime(dateWithHospitalAndIdVO);

    // 7) 이미 예약이 되어있는 환자일 경우, 에러를 발생시킨다.
    if (diagnosisDTO != null) {
      throw new ConflictRequestException("이미 예약이되어 있는 환자입니다", new Throwable("Fail Reservation"));
    } else {

      // 8) diagnosisDTO 값이 null일 경우, 추가 작업을 진행한다.
      if (diagnosisDAO.addDiagnosisReservation(diagnosisInfo) != 1) {
        result = false;
      } ;
    }

    return result;
  }


  // 예약 수정하기
  @Override
  public boolean modifyReservationInfo(DiagnosisUpdateVO diagnosisUpdateVO) {

    // 1)diagnosisUpdateVO 요소가 null 일때나 "" 값일 때 에러 발생
    if (diagnosisUpdateVO.isNull()) {
      throw new BadRequestException("잘못된 데이터 정보입니다. 올바른 정보인지 확인해주세요",
          new Throwable("Wrong data : DiagnosisUpateVO is null"));
    }
    PatientsDTO patient = patientsDAO.selectPatientByPatienIdAndHospitalCode(
        diagnosisUpdateVO.getPatientId(), diagnosisUpdateVO.getHospitalCode());
    // 2) 수정하기전 해당 환자가 해당 병원에 등록되어 있는지 확인
    if (patient == null) {
      throw new ConflictRequestException("해당 병원에 환자가 존재하지 않습니다. 다시 확인해주세요",
          new Throwable("Wrong Update : No Patient Data in hospital"));
    }

    MembersDTO member = membersDAO.selectMemberInfoByMemberIdAndHospitalCode(
        diagnosisUpdateVO.getMemberId(), diagnosisUpdateVO.getHospitalCode());
    // 3) 수정하기전 해당 의사가 해당 병원에 등록되어 있는지 확인
    if (member == null) {
      throw new ConflictRequestException("해당 병원에 의사가 존재하지 않습니다. 다시 확인해주세요",
          new Throwable("Wrong Update : No Doctor Data in hospital"));
    }

    // 4) diagId, visitPurpose를 넘겨 데이터를 수정을 한다
    int updateResult = diagnosisDAO.updateDiagnosisReservation(diagnosisUpdateVO.getDiagId(),
        diagnosisUpdateVO.getVisitPurpose());
    // 5) 수정결과가 1이 아닐때는 에러를 발생시킨다
    if (updateResult != 1) {
      throw new ConflictRequestException("수정하는데 실패하였습니다", new Throwable("Wrong Update"));
    }

    return true;
  }

  // 예약 취소하기
  @Override
  public boolean removeReservationInfo(int diagId) {
    // 1) diagId 값이 올바르지 않을 경우
    if (diagId == 0) {
      throw new BadRequestException("잘못된 데이터 정보입니다. 올바른 정보인지 확인해주세요",
          new Throwable("Wrong data : removeData is null"));
    }

    int deleteResult = diagnosisDAO.deleteDiagnosisReservation(diagId);

    // 2) 삭제결과가 제대로 작동되지 않았을 때 발생이되는 에러
    if (deleteResult != 1) {
      throw new ConflictRequestException("삭제하는데 실패하였습니다", new Throwable("Wrong Update"));
    }
    return true;
  }

  // 예약 목록 보여주기
  @Override
  public List<ReservationVO> showWeeklyReservationList(WeekNoWithMemberVO dateInfo) {

    List<ReservationVO> reservationInfoList = new ArrayList<>();
    // 1) dateInfo의 데이터들이 null 일 때
    if (dateInfo.isNull()) {
      throw new BadRequestException("잘못된 데이터 정보입니다. 올바른 정보인지 확인해주세요",
          new Throwable("Wrong data : WeekNoWithMemberVo data is null"));
    }

    MembersDTO CheckMember = membersDAO.selectMemberInfoByMemberIdAndHospitalCode(
        dateInfo.getMemberId(), dateInfo.getHospitalCode());
    // 2) 수정하기전 해당 의사가 해당 병원에 등록되어 있는지 확인
    if (CheckMember == null) {
      throw new ConflictRequestException("해당 병원에 의사가 존재하지 않습니다. 다시 확인해주세요",
          new Throwable("Wrong Update : No Doctor Data in hospital"));
    }

    // 3) diagnosisInfo 정보에 대한 리스트 갖고오기
    List<DiagnosisDTO> diagnosisInfo =
        diagnosisDAO.selectDiagnosisListByMemberIdAndWeekNo(dateInfo);

    // 4) 리스트에 대한 정보가 없을 때, 에러를 일으킨다
    if (diagnosisInfo.size() == 0) {
      throw new NotFoundException("데이터가 검색이되지 않았습니다.", new Throwable("No Data"));
    }

    // 5) ReservationVO에 필요한 MemberDTO, PatinetDTO, DiagnosisDTO를 구해 ReservationVO에 세팅하는 과정
    for (DiagnosisDTO diagnosis : diagnosisInfo) {

      Integer memberId = new Integer(diagnosis.getMemberId());
      Integer patientId = new Integer(diagnosis.getPatientId());

      if (memberId == null || patientId == null) {
        throw new BadRequestException("잘못된 데이터 정보입니다. 올바른 정보인지 확인해주세요",
            new Throwable("Wrong data"));
      }

      MembersDTO member = membersDAO.selectMemberInfoByMemberId(diagnosis.getMemberId());
      PatientsDTO patient = patientsDAO.selectPatientByPatientId(diagnosis.getPatientId());

      ReservationVO reservationVO = new ReservationVO(diagnosis, patient, member);
      // reservationVO 안의 값들이 모두 null이라면 발생시킬 익셉션
      if (reservationVO.isNull()) {
        throw new BadRequestException("잘못된 데이터 정보입니다. 올바른 정보인지 확인해주세요",
            new Throwable("Wrong data"));
      }

      reservationInfoList.add(reservationVO);

    }
    return reservationInfoList;
  }

  // 예약 환자 목록을 검색하기
  @Override
  public List<ReservationVO> showReservationWaitingList(PatientSearchVO patientSearchVO) {

    List<ReservationVO> reservationInfoList = new ArrayList<>();
    // patientSearchVO 안의 값들이 null 이라면 발생시킬 익셉션
    if (patientSearchVO.isNull()) {
      throw new BadRequestException("잘못된 데이터 정보입니다. 올바른 정보인지 확인해주세요", new Throwable("Wrong data"));
    }

    // hospitalCode를 통해 해당 병원의 예약환자를 모두 가져온다
    List<DiagnosisDTO> diagnosisInfo =
        diagnosisDAO.getReservationDiagnosisListByHospitalCode(patientSearchVO.getHospitalCode());

    for (DiagnosisDTO diagnosis : diagnosisInfo) {
      Integer memberId = new Integer(diagnosis.getMemberId());
      Integer patientId = new Integer(diagnosis.getPatientId());
      // diagnosis안의 memberId와 patientId가 둘중 하나라도 null이라면 익셉션을 발생시킨다
      if (memberId == null || patientId == null) {
        throw new BadRequestException("잘못된 데이터 정보입니다. 올바른 정보인지 확인해주세요",
            new Throwable("Wrong data"));
      }
      // diagnosis 에서 가져온 의사데이터(memberId)를 가지고 의사가 해당 병원에 존재하는지 확인하기 위해 가져온 DTO
      MembersDTO member = membersDAO.selectMemberInfoByMemberIdAndHospitalCode(
          diagnosis.getMemberId(), patientSearchVO.getHospitalCode());

      // diagnosis 에서 가져온 환자데이터(patientId)를 가지고 환자가 해당 병원에 존재하는지 확인하기 위해 가져온 DTO
      PatientsDTO patient = patientsDAO.selectPatientByPatienIdAndHospitalCode(
          diagnosis.getPatientId(), patientSearchVO.getHospitalCode());

      // 환자, 의사 둘중하나라도 null이면 익셉션 발생
      if (member == null || patient == null) {
        throw new NotFoundException("해당 병원에 환자 또는 의사에 대한 데이터 존재하지 않습니다. 다시 확인해주세요",
            new Throwable("Wrong Data : No Doctor or Patient Data in hospital"));
      }

      // diagnosis, patient, member dto를 통해 reservationVO에 데이터를 세팅한다
      ReservationVO reservationVO = new ReservationVO(diagnosis, patient, member);

      // 세팅된 데이터 중 하나라도 null이 있다면 익셉션을 발생시킨다
      if (reservationVO.isNull()) {
        throw new BadRequestException("잘못된 데이터 정보입니다. 올바른 정보인지 확인해주세요",
            new Throwable("Wrong data"));
      }

      // 검색한 keyword: getPatientName과 patient dto 중 환자이름이 같다면 리스트에 추가시킨다
      if (patient.getPatientName().equals(patientSearchVO.getPatientName())) {
        reservationInfoList.add(reservationVO);
      }
    }
    // 예약정보 리스트에 데이터가 존재하지 않다면, 익셉션을 발생시킨다
    if (reservationInfoList.size() == 0) {
      throw new NotFoundException("검색결과가 없습니다.", new Throwable(
          "No data : reservationInfoList data is not Found in showReservationWaitingList"));
    }

    return reservationInfoList;
  }

  // 의사 정보를 가져오기
  @Override
  public List<MembersDTO> getDoctorsInfo(String hospitalCode) {
    // 1) hospitalCode가 null일때 에러를 발생시킨다
    if (hospitalCode == "" || hospitalCode == null) {
      throw new BadRequestException("잘못된 데이터 정보입니다. 올바른 정보인지 확인해주세요", new Throwable("Wrong data"));
    }
    // 2) 의사에 대한 정보 리스트를 가져온다
    List<MembersDTO> getMembers = membersDAO.selectDoctorInfoByHospitalCode(hospitalCode);

    // 3) 의사에 대한 리스트 결과가 없다면, 에러를 발생시킨다
    if (getMembers.size() == 0) {
      throw new NotFoundException("데이터가 검색되지 않았습니다.", new Throwable("No Data"));
    }

    return getMembers;
  }

  // 환자 리스트
  @Override
  public List<PatientsDTO> getPatientsInfo(PatientSearchVO patientInfo) {
    // 1) PatientSearchVO 안의 요소들 중 하나라도 null일 때 에러를 발생시킨다
    if (patientInfo.isNull()) {
      throw new BadRequestException("잘못된 데이터 정보입니다. 올바른 정보인지 확인해주세요", new Throwable("Wrong data"));
    }
    // 2) PatientSearchVO 요소(patientName, hospitalCode)를 통해 환자 정보 리스트를 가져온다
    List<PatientsDTO> getPatientList = patientsDAO.selectPatientsByPatientName(patientInfo);

    // 3) list 크기가 0일떄, 에러를 발생시킨다
    if (getPatientList.size() == 0) {
      throw new NotFoundException("데이터가 검색되지 않았습니다.", new Throwable("No Data"));
    }
    return getPatientList;
  }

  @Override
  public DiagnosisInfoVO getDiagnosisInfo(int diagId) {
    if (diagId == 0) {
      throw new BadRequestException("진료 ID가 들어오지 않았습니다.", new Throwable("no_diag_id"));
    }
    DiagnosisDTO diagnosisInfo = diagnosisDAO.getDiagnosisInfo(diagId);

    if (diagnosisInfo == null) {
      throw new NotFoundException("진료 정보가 존재하지 않습니다.", new Throwable("no_diagnosis_info"));
    }

    int patientId = diagnosisInfo.getPatientId();

    PatientsDTO patientInfo = patientsDAO.selectPatientByPatientId(patientId);

    if (patientInfo == null) {
      throw new NotFoundException("환자 정보가 존재하지 않습니다.", new Throwable("no_patient_info"));
    }

    return new DiagnosisInfoVO(diagnosisInfo, patientInfo);
  }



}
