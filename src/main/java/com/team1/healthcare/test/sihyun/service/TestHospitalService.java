package com.team1.healthcare.test.sihyun.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.team1.healthcare.dto.HospitalsDTO;
import com.team1.healthcare.test.sihyun.dao.TestHospitalsDAO;

@Service
public class TestHospitalService {
  private static final Logger logger = LoggerFactory.getLogger(TestHospitalService.class);

  @Autowired
  private TestHospitalsDAO testhospitaldao;

  public List<HospitalsDTO> getHospitalInfo() {
    return testhospitaldao.getHospitalInfoList();
  }

  public void addHospital(HospitalsDTO hospital) {
    int count = testhospitaldao.getCount();
    hospital.setHospitalCode(createHospitalCode() + count);
    testhospitaldao.addHospitalInfo(hospital);
  }

  public void updateHospital(HospitalsDTO hospital) {
    testhospitaldao.updateHospitalInfo(hospital);
  }

  public void deleteHospital(String hospitalCode) {
    testhospitaldao.deleteHospitalInfo(hospitalCode);
  }



  private String createHospitalCode() {
    String PREFIX_HOSPITAL = "";
    for (int i = 0; i < 4; i++) {
      char uc = (char) ((int) (Math.random() * 26 + 65));
      PREFIX_HOSPITAL += uc;
    }
    return PREFIX_HOSPITAL;
  }
}
