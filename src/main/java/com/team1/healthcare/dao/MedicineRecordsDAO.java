package com.team1.healthcare.dao;

import org.apache.ibatis.annotations.Mapper;
import com.team1.healthcare.vo.diagnosis.MedicineResultVO;

@Mapper
public interface MedicineRecordsDAO {
  public int addMedicineRecord(MedicineResultVO medicineInfo);
}
