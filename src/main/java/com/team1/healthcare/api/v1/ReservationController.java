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
import com.team1.healthcare.dto.DiagnosisDTO;
import com.team1.healthcare.dto.MembersDTO;
import com.team1.healthcare.dto.PatientsDTO;
import com.team1.healthcare.services.DiagnosisServiceImpl;
import com.team1.healthcare.vo.common.PatientSearchVO;
import com.team1.healthcare.vo.common.WeekNoWithMemberVO;
import com.team1.healthcare.vo.diagnosis.DiagnosisUpdateVO;
import com.team1.healthcare.vo.diagnosis.ReservationVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {
  @Autowired
  private DiagnosisServiceImpl diagnosisService;

  // 1. POST 진료 접수(예약) 목록 보기
  @PostMapping("/data")
  public List<ReservationVO> getReservationInfo(
      @RequestBody WeekNoWithMemberVO weekNoWithMemberVO) {
    List<ReservationVO> diagnosisInfo =
        diagnosisService.showWeeklyReservationList(weekNoWithMemberVO);
    return diagnosisInfo;
  }


  // 2. POST 진료 접수(예약) 하기
  @PostMapping("")
  public boolean addReservationInfo(@RequestBody DiagnosisDTO diagnosisInfo) {
    boolean result = diagnosisService.addReservationInfo(diagnosisInfo);
    return result;
  }

  // 3. PUT 진료 접수(예약) 수정하기
  @PutMapping("")
  public String modifyReservationInfo(@RequestBody DiagnosisUpdateVO diagnosisUpdateVO) {
    boolean result = diagnosisService.modifyReservationInfo(diagnosisUpdateVO);

    return "수정이 완료되었습니다.";
  }

  // 4. DELETE 진료 접수(예약) 삭제하기
  @PutMapping("/{diagId}")
  public boolean removeReservationInfo(@PathVariable int diagId) {
    boolean result = diagnosisService.removeReservationInfo(diagId);

    return result;
  }

  // 환자 검색
  @PostMapping("/patient")
  public List<PatientsDTO> getPatientInfo(@RequestBody PatientSearchVO patientVo) {
    List<PatientsDTO> patients = diagnosisService.getPatientsInfo(patientVo);
    return patients;
  }

  // 의사 검색
  @GetMapping("/doctor/{hospitalCode}")
  public List<MembersDTO> getDoctorInfo(@PathVariable String hospitalCode) {
    List<MembersDTO> doctors = diagnosisService.getDoctorsInfo(hospitalCode);
    return doctors;
  }



  // 예약환자 검색

  @PostMapping("/waitingPatient")
  public List<ReservationVO> getSearchReservationPatient(
      @RequestBody PatientSearchVO patientSearchVO) {

    List<ReservationVO> diagnosisInfo =
        diagnosisService.showReservationWaitingList(patientSearchVO);

    return diagnosisInfo;
  }

}
