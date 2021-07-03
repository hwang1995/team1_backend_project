package com.team1.healthcare.dao;

import org.apache.ibatis.annotations.Mapper;
import com.team1.healthcare.dto.HospitalsDTO;

@Mapper
public interface HospitalsDAO {
  public HospitalsDTO getHospitalInfo(String hospitalCode);
}
