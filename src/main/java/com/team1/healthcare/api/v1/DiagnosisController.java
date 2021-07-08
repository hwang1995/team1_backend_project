package com.team1.healthcare.api.v1;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.team1.healthcare.services.DiagnosisServiceImpl;
import com.team1.healthcare.vo.auth.UserInfoVO;
import com.team1.healthcare.vo.diagnosis.DiagnosisHistoryVO;
import com.team1.healthcare.vo.diagnosis.DiagnosisListVO;
import com.team1.healthcare.vo.diagnosis.RegistDiagnosisVO;
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



}
