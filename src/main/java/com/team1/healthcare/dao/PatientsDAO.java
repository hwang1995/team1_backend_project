package com.team1.healthcare.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.team1.healthcare.dto.PatientsDTO;

@Mapper
public interface PatientsDAO {

  public PatientsDTO selectPatientByPatientId(int patientId);

  public List<PatientsDTO> selectHospitalPatientByPatientName(
      @Param("patientName") String patientName, @Param("hospitalCode") String hospitalCode);

}
