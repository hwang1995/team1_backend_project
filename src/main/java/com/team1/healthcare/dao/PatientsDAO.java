package com.team1.healthcare.dao;

import org.apache.ibatis.annotations.Mapper;
import com.team1.healthcare.dto.PatientsDTO;

@Mapper
public interface PatientsDAO {

  public PatientsDTO selectPatientByPatientId(int patientId);

}
