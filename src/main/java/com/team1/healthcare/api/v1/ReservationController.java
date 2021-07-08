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
import com.team1.healthcare.vo.diagnosis.ReservationVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {
  @Autowired
  DiagnosisServiceImpl diagnosis;


  // 2. POST 진료 접수(예약) 하기
  @PostMapping("")
  public String addReservationInfo(@RequestBody DiagnosisDTO diagnosisInfo) {
    boolean result = diagnosis.addReservationInfo(diagnosisInfo);
    return "성공적으로 추가되었습니다";
  }

  // 3. PUT 진료 접수(예약) 수정하기
  @PutMapping("/{diagId}/{visitPurpose}")
  public String modifyReservationInfo(@PathVariable int diagId, @PathVariable String visitPurpose) {
    diagnosis.modifyReservationInfo(diagId, visitPurpose);

    return "성공적으로 수정되었습니다";
  }

  // 4. DELETE 진료 접수(예약) 삭제하기
  @PutMapping("/{diagId}")
  public String removeReservationInfo(@PathVariable int diagId) {
    log.info("dta" + diagId);
    diagnosis.removeReservationInfo(diagId);

    return "성공적으로 삭제되었습니다";
  }

  // 환자 검색
  @GetMapping("")
  public List<PatientsDTO> getPatientInfo(@RequestBody PatientSearchVO patientVo) {
    List<PatientsDTO> patients = diagnosis.getPatientsInfo(patientVo);
    return patients;
  }

  @GetMapping("/{hospitalCode}")
  public List<MembersDTO> getDoctorInfo(@PathVariable String hospitalCode) {
    List<MembersDTO> doctors = diagnosis.getDoctorsInfo(hospitalCode);
    return doctors;
  }

  // 1. GET 진료 접수(예약) 목록 보기
  @GetMapping("/getReservation")
  public List<ReservationVO> getReservationInfo(
      @RequestBody WeekNoWithMemberVO weekNoWithMemberVO) {
    List<ReservationVO> diagnosisInfo = diagnosis.showWeeklyReservationList(weekNoWithMemberVO);
    return diagnosisInfo;
  }

  @GetMapping("/getSearchReservationPatient")
  public List<ReservationVO> getSearchReservationPatient(
      @RequestBody PatientSearchVO patientSearchVO) {

    List<ReservationVO> diagnosisInfo = diagnosis.showReservationWaitingList(patientSearchVO);

    return diagnosisInfo;
  }

}
