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
import com.team1.healthcare.dto.HospitalsDTO;
import com.team1.healthcare.services.HospitalServiceImpl;

@RestController
@RequestMapping("/api/v1/hospital")
public class HospitalController {

  @Autowired
  private HospitalServiceImpl hospitalService;

  // 1. GET 병원의 목록 보여주기
  @GetMapping("")
  public List<HospitalsDTO> getHospitalListInfo() {
    return hospitalService.showHospitalInfos();
  }

  // 2. GET 해당 병원의 정보 보여주기
  @GetMapping("/{hospitalCode}")
  public HospitalsDTO gethospitalInfo(@PathVariable String hospitalCode) {
    return hospitalService.showHospitalDetail(hospitalCode);
  }

  // 3. POST 병원 정보를 추가하기
  @PostMapping("")
  public boolean addHospitalInfo(@RequestBody HospitalsDTO hospitalInfo) {
    boolean result = hospitalService.addHospital(hospitalInfo);
    return result;
  }

  // 4. PUT 병원 정보를 수정하기
  @PutMapping("")
  public boolean modifyHospitalsInfo(@RequestBody HospitalsDTO hospitalInfo) {
    boolean result = hospitalService.modifyHospital(hospitalInfo);
    return result;
  }

  // 5. DELETE 병원 정보를 삭제하기
  @DeleteMapping("/{hospitalCode}")
  public boolean removeHospitalInfo(@PathVariable String hospitalCode) {
    boolean result = hospitalService.deleteHospital(hospitalCode);
    return result;
  }
}
