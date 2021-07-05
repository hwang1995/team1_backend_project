package com.team1.healthcare.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.team1.healthcare.dto.HospitalsDTO;

@Service
public class HospitalServiceImpl implements IHospitalService {

  @Override
  public boolean addHospital(HospitalsDTO hospitalInfo) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean deleteHospital(String hospitalCode) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean modifyHospital(HospitalsDTO hospitalInfo) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public List<HospitalsDTO> showHospitalInfos() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public HospitalsDTO showHospitalDetail(String hospitalCode) {
    // TODO Auto-generated method stub
    return null;
  }


}
