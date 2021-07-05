package com.team1.healthcare.test.sihyun.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.team1.healthcare.dto.HospitalsDTO;
import com.team1.healthcare.test.sihyun.service.TestHospitalService;

@RestController
@RequestMapping("/api/test1/hospital")
public class TestHospitalController {
  private static final Logger logger = LoggerFactory.getLogger(TestHospitalController.class);

  @Autowired
  TestHospitalService testHospitalService;

  // 1. GET 병원의 목록 보여주기
  @GetMapping("")
  public Map<String, Object> getHospital() {

    List<HospitalsDTO> hospitals = testHospitalService.getHospitalInfo();
    Map<String, Object> map = new HashMap<>();
    map.put("hospital", hospitals);
    return map;
  }

  // 2. GET 해당 병원의 정보 보여주기
  // 3. POST 병원 정보를 추가하기
  @PostMapping("")
  public void addHostpital(@RequestBody HospitalsDTO hospital) {
    testHospitalService.addHospital(hospital);
  }

  // 4. PUT 병원 정보를 수정하기
  @PutMapping("")
  public void updateHospital(@RequestBody HospitalsDTO hospital) {
    testHospitalService.updateHospital(hospital);
  }

  // 5. DELETE 병원 정보를 삭제하기
  @DeleteMapping("/{hospitalCode}")
  public void deleteHospital(@PathVariable String hospitalCode) {
    // logger.info("delete", hospitalCode);
    testHospitalService.deleteHospital(hospitalCode);
  }
}
