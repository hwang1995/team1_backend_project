package com.team1.healthcare.api.v1;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.team1.healthcare.dto.DiagnosticInspectionsDTO;
import com.team1.healthcare.dto.MedicinesDTO;
import com.team1.healthcare.services.DiagnosisServiceImpl;
import com.team1.healthcare.vo.auth.UserInfoVO;
import com.team1.healthcare.vo.common.DateWithHospitalCode;
import com.team1.healthcare.vo.diagnosis.DiagnosisHistoryVO;
import com.team1.healthcare.vo.diagnosis.DiagnosisListVO;
import com.team1.healthcare.vo.diagnosis.RegistDiagnosisVO;
import com.team1.healthcare.vo.diagnostic.DiagnosticTestRecordsVO;
import com.team1.healthcare.vo.diagnostic.DiagnosticVO;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/diagnosis")
@Slf4j
public class DiagnosisController {

  @Autowired
  private DiagnosisServiceImpl diagnosisService;

  // 1. GET 해당 병원의 환자 진료 기록 목록 가져오기
  @GetMapping("")
  public List<DiagnosisListVO> getDiagnosisList(@RequestBody UserInfoVO userInfo) {
    List<DiagnosisListVO> result = diagnosisService.showTodayDiagnosisList(userInfo);

    return result;
  }

  @PostMapping("")
  public String testRegistDiagnosis(@RequestBody RegistDiagnosisVO registInfo) {
    log.info(registInfo.toString());
    return "hello";
  }
  // 2. GET 해당 환자의 진료 기록 목록 가져오기
  // 3. PUT 진료 내용 수정하기 (의사가 예약된 진료를 보고 내용을 추가하려고)

  @PutMapping("")
  public boolean registDiagnosisInfo(@RequestBody RegistDiagnosisVO diagnosisInfo) {
    log.info(diagnosisInfo.toString());
    boolean result = diagnosisService.registDiagnosisInfo(diagnosisInfo);
    return result;
  }

  @GetMapping("/search/medicine")
  public List<MedicinesDTO> searchMedicineList(String medicineName) {
    List<MedicinesDTO> result = diagnosisService.searchMedicineList(medicineName);

    return result;
  }

  @GetMapping("/search/injector")
  public List<MedicinesDTO> searchInjectorList(String medicineName) {
    List<MedicinesDTO> result = diagnosisService.searchInjectorList(medicineName);
    return result;
  }

  @GetMapping("/search/diagnostic")
  public List<DiagnosticInspectionsDTO> searchDiagnosticList(String bundleName) {
    List<DiagnosticInspectionsDTO> result =
        diagnosisService.searchDiagnosticListByBundleName(bundleName);
    return result;
  }

  @GetMapping("/search/diagnostic-code")
  public List<DiagnosticInspectionsDTO> searchDiagnosticListByCode(String bundleCode) {
    List<DiagnosticInspectionsDTO> result =
        diagnosisService.searchDiagnosticListByBundleCode(bundleCode);
    return result;
  }

  @GetMapping("/history")
  public List<DiagnosisHistoryVO> showDiagnosisHistoryByPatientId(int patientId) {
    List<DiagnosisHistoryVO> result = diagnosisService.showDiagnosisHistoryByPatientId(patientId);
    return result;
  }

  @GetMapping("/diagnostic/{diagTestId}")
  public List<DiagnosticTestRecordsVO> showDiagnosticTestListByDiagTestId(
      @PathVariable int diagTestId) {
    List<DiagnosticTestRecordsVO> result =
        diagnosisService.showDiagnosticTestListByDiagTestId(diagTestId);
    return result;
  }

  @GetMapping("/diagnostic")
  public List<DiagnosticVO> showWeeklyDiagnosticTestListByHospitalCode(
      @RequestBody DateWithHospitalCode hospitalInfo) {
    List<DiagnosticVO> result =
        diagnosisService.showWeeklyDiagnosticTestListByHospitalCode(hospitalInfo);
    return result;
  }
}
