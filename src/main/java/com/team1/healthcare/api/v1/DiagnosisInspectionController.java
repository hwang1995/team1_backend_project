package com.team1.healthcare.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/diagnosis/inspection")
public class DiagnosisInspectionController {
  // 1. GET 해당 환자의 진단 검사 목록 가져오기
  // 2. GET 해당 환자의 진단 검사 상세 가져오기
  // 3. GET 해당 환자의 진단 검사 상세 페이지에서 엑셀 저장을 누를 시에 CSV 제공
  // 4. GET 해당 병원의 진단 검사 목록 가져오기
  // 5. POST 해당 환자의 진단 검사를 추가하기
  // 6. PUT 해당 환자의 진단 검사 상세에서 바코드 출력을 누를 시에 진단 검사의 상태를 진행중으로 바꾸기
  // 7. PUT 해당 환자의 진단 검사 상세에서 결과를 입력시에 값을 추가
  // 8. PUT 해당 환자의 진단 검사 상세에서 접수 취소를 누를 시에 상태를 취소로 바꾼다.


}
