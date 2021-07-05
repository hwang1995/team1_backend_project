package com.team1.healthcare.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.team1.healthcare.dto.PatientsDTO;
import com.team1.healthcare.vo.common.PatientSearchVO;

@Service
public class PatientServiceImpl implements IPatientService {

  @Override
  public boolean addPatientInfo(PatientsDTO patientInfo) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public List<PatientsDTO> getPatientsListInfo(PatientSearchVO patientSearchInfo) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean modifyPatientInfo(PatientsDTO patientInfo) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean removePatientInfo(int patientId) {
    // TODO Auto-generated method stub
    return false;
  }

}
