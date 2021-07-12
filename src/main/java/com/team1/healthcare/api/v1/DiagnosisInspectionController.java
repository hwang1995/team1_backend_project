package com.team1.healthcare.api.v1;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.team1.healthcare.exception.BadRequestException;
import com.team1.healthcare.services.DiagnosisServiceImpl;
import com.team1.healthcare.vo.common.DateWithHospitalCode;
import com.team1.healthcare.vo.diagnostic.DiagnosticTestRecordsVO;
import com.team1.healthcare.vo.diagnostic.DiagnosticTestResultVO;
import com.team1.healthcare.vo.diagnostic.DiagnosticVO;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/diagnosis/inspection")
public class DiagnosisInspectionController {
  @Autowired
  private DiagnosisServiceImpl diagnosisService;

  // 1. 진단 검사의 ID로 진단 검사 상세 리스트를 출력
  @GetMapping("/{diagTestId}")
  public List<DiagnosticTestRecordsVO> showDiagnosticTestListByDiagTestId(
      @PathVariable int diagTestId) {
    List<DiagnosticTestRecordsVO> result =
        diagnosisService.showDiagnosticTestListByDiagTestId(diagTestId);
    return result;
  }

  // 2. 병원 코드로 일주일 동안의 진단 검사의 기록 리스트를 출력
  @GetMapping("")
  public List<DiagnosticVO> showWeeklyDiagnosticTestListByHospitalCode(
      @RequestBody DateWithHospitalCode hospitalInfo) {
    List<DiagnosticVO> result =
        diagnosisService.showWeeklyDiagnosticTestListByHospitalCode(hospitalInfo);
    return result;
  }

  // 3. 진단 검사 상세의 정보를 바꾸기
  @PutMapping("/status")
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

  // 7. PUT 해당 환자의 진단 검사 상세에서 결과를 입력시에 값을 추가
  @PutMapping("")
  public boolean changeDiagnosticValue(@RequestBody List<DiagnosticTestResultVO> resultInfo) {
    boolean result = diagnosisService.changeDiagnosticValue(resultInfo);
    return result;
  }

  // 1. GET 해당 환자의 진단 검사 목록 가져오기
  // 3. GET 해당 환자의 진단 검사 상세 페이지에서 엑셀 저장을 누를 시에 CSV 제공
  // 4. GET 해당 병원의 진단 검사 목록 가져오기


}
