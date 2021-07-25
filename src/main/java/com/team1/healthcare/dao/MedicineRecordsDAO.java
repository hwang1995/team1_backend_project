package com.team1.healthcare.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.team1.healthcare.dto.MedicineRecordsDTO;
import com.team1.healthcare.vo.diagnosis.MedicineResultVO;

@Mapper
public interface MedicineRecordsDAO {
  public int addMedicineRecord(MedicineResultVO medicineInfo);

  public List<MedicineRecordsDTO> selectMedicineRecordsByDiagId(int diagId);

  public List<MedicineRecordsDTO> selectPharmaciesByDiagId(int diagId);

  public List<MedicineRecordsDTO> selectInjectorsByDiagId(int diagId);


}
