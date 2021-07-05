package com.team1.healthcare.dao;

import org.apache.ibatis.annotations.Mapper;
import com.team1.healthcare.dto.VitalRecordsDTO;
import com.team1.healthcare.vo.diagnosis.VitalResultVO;

@Mapper
public interface VitalRecordsDAO {
  public int addVitalRecord(VitalResultVO vitalInfo);

  public VitalRecordsDTO selectVitalRecordByDiagId(int diagId);
}
