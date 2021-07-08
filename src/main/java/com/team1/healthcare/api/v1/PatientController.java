package com.team1.healthcare.api.v1;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.team1.healthcare.dto.PatientsDTO;
import com.team1.healthcare.services.PatientServiceImpl;
import com.team1.healthcare.vo.common.PatientSearchVO;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/patient")
public class PatientController {

  @Autowired
  private PatientServiceImpl patientServiceImpl;

  // 1. GET 해당 병원의 해당 환자의 목록 가져오기
  @GetMapping("/search/{hospitalCode}")
  public List<PatientsDTO> getPatientsListInfo(@PathVariable String hospitalCode) {
    return patientServiceImpl.getPatientListInfoByHospitalCode(hospitalCode);
  }

  // 2. GET 해당 병원의 해당 환자 검색하기
  @GetMapping("")
  public List<PatientsDTO> getPatientsList(@RequestBody PatientSearchVO patientSearchVO) {
    return patientServiceImpl.getPatientsListInfo(patientSearchVO);
  }

  // 3. POST 해당 병원의 해당 환자를 추가하기
  @PostMapping("")
  public boolean addPatient(@RequestBody PatientsDTO patientInfo) {
    log.info(patientInfo.toString());
    boolean result = patientServiceImpl.addPatientInfo(patientInfo);
    return result;
  }

  // 4. PUT 해당 병원의 해당 환자의 정보를 수정하기
  @PutMapping("")
  public boolean modifyPatient(@RequestBody PatientsDTO patientInfo) {
    boolean result = patientServiceImpl.modifyPatientInfo(patientInfo);
    return result;
  }

  // 5. DELETE 해당 병원에서 해당 환자의 정보를 삭제하기
  @DeleteMapping("/{patientId}")
  public boolean deletePatient(@PathVariable int patientId) {
    boolean result = patientServiceImpl.removePatientInfo(patientId);
    return result;
  }
}
