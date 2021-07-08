package com.team1.healthcare.api.v1;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.team1.healthcare.exception.BadRequestException;
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


  @PutMapping("")
  public boolean registDiagnosisInfo(@RequestBody RegistDiagnosisVO diagnosisInfo) {
    log.info(diagnosisInfo.toString());
    boolean result = diagnosisService.registDiagnosisInfo(diagnosisInfo);
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

  @PutMapping("/diagnostic/status")
  public boolean changeStatus(@RequestBody Map<String, Object> statusInfo) {
    String status = statusInfo.get("status").toString();

    int diagTestId = Integer.parseInt(statusInfo.get("diagTestId").toString());

    if (status.equals("completed")) {

      boolean result = diagnosisService.changeStatusCompleted(diagTestId);
      return result;
    }

    if (status.equals("processing")) {
      boolean result = diagnosisService.changeStatusProcessing(diagTestId);
      return result;
    }

    if (status.equals("pending")) {
      boolean result = diagnosisService.changeStatusPending(diagTestId);
      return result;
    }
    // boolean result = diagnosisService.changeStatusCompleted(diagTestId);
    throw new BadRequestException("정확한 매개변수를 입력해주세요", new Throwable("incorrect_parameters"));
  }


}
