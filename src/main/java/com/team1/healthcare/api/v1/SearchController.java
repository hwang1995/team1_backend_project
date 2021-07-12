package com.team1.healthcare.api.v1;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.team1.healthcare.dto.DiagnosticInspectionsDTO;
import com.team1.healthcare.dto.DiagnosticTestsDTO;
import com.team1.healthcare.dto.MedicinesDTO;
import com.team1.healthcare.services.DiagnosisServiceImpl;
import com.team1.healthcare.vo.common.PatientSearchVO;
import com.team1.healthcare.vo.diagnostic.DiagnosticTestRecordsVO;
import com.team1.healthcare.vo.patient.PatientVO;
import lombok.extern.slf4j.Slf4j;

@RestController

@RequestMapping("/api/v1/search")
@PropertySource("classpath:credentials.properties")
@Slf4j
public class SearchController {

  @Value("${hivemq.host}")
  private String host;
  @Autowired
  private DiagnosisServiceImpl diagnosisService;

  @GetMapping("/medicine")
  public List<MedicinesDTO> searchMedicineList(String medicineName) {
    List<MedicinesDTO> result = diagnosisService.searchMedicineList(medicineName);
    log.info(host);

    return result;
  }

  @GetMapping("/injector")
  public List<MedicinesDTO> searchInjectorList(String medicineName) {
    List<MedicinesDTO> result = diagnosisService.searchInjectorList(medicineName);
    return result;
  }

  @GetMapping("/diagnostic")
  public List<DiagnosticInspectionsDTO> searchDiagnosticList(String bundleName) {
    List<DiagnosticInspectionsDTO> result =
        diagnosisService.searchDiagnosticListByBundleName(bundleName);
    return result;
  }

  @GetMapping("/diagnostic-code")
  public List<DiagnosticInspectionsDTO> searchDiagnosticListByCode(String bundleCode) {
    List<DiagnosticInspectionsDTO> result =
        diagnosisService.searchDiagnosticListByBundleCode(bundleCode);
    return result;
  }

  @GetMapping("/patient")
  public List<PatientVO> searchPatientInfoByName(@RequestBody PatientSearchVO patientInfo) {
    List<PatientVO> result = diagnosisService.searchPatientInfoByName(patientInfo);
    return result;
  }

  @GetMapping("/diagnosticHistory")
  public List<List<DiagnosticTestRecordsVO>> showDiagnosticTestListByPatientId(int patientId) {
    // PatientID에 따른 DiagnosticTestDTO를 가져온다.
    List<DiagnosticTestsDTO> diagnosticInfo =
        diagnosisService.getDiagnosticTestsByPatientId(patientId);
    List<List<DiagnosticTestRecordsVO>> result = new ArrayList<>();

    // 값 가져오기
    diagnosticInfo.forEach(info -> {
      int diagTestId = info.getDiagTestId();
      List<DiagnosticTestRecordsVO> data =
          diagnosisService.showDiagnosticTestListByDiagTestId(diagTestId);
      result.add(data);
    });

    return result;
  }
}
