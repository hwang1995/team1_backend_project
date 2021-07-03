package com.team1.healthcare.api.v1;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.team1.healthcare.vo.diagnosis.DiagnosisListVO;
import com.team1.healthcare.vo.diagnosis.RegistDiagnosisVO;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/diagnosis")
@Slf4j
public class DiagnosisController {

  // 1. GET 해당 병원의 환자 진료 기록 목록 가져오기
  @GetMapping("")
  public List<DiagnosisListVO> getDiagnosisList() {
    // 1. 바디에 있는 userInfo 가져온다.
    // userInfo - memberId, memberEmail, memberAuthority, hospitalCode

    // 2. 서비스에 userInfo를 보내고 List<DiagnosisVO> 받는다.

    // 3. 리턴한다.


    return null;
  }

  @PostMapping("")
  public String testRegistDiagnosis(@RequestBody RegistDiagnosisVO registInfo) {
    log.info(registInfo.toString());
    return "hello";
  }
  // 2. GET 해당 환자의 진료 기록 목록 가져오기
  // 3. PUT 진료 내용 수정하기 (의사가 예약된 진료를 보고 내용을 추가하려고)

}
