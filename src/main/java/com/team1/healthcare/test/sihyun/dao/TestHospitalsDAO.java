package com.team1.healthcare.test.sihyun.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.team1.healthcare.dto.HospitalsDTO;

@Mapper
public interface TestHospitalsDAO {
  public HospitalsDTO getHospitalInfo(String hospitalCode);

  public int addHospitalInfo(HospitalsDTO hospitalIinfo);

  public int getCount();

  public int updateHospitalInfo(HospitalsDTO hospitalIinfo);

  public int deleteHospitalInfo(String hospitalCode);

  public List<HospitalsDTO> getHospitalInfoList();
}
