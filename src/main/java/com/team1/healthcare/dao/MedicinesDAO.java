package com.team1.healthcare.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.team1.healthcare.dto.MedicinesDTO;

@Mapper
public interface MedicinesDAO {
  public List<MedicinesDTO> getMedicineInfoByMedicineName(String medicineName);

  // 진료 접수 시에 내복약과 외용약이면서 medicineName에 부합하는 내용을 반환하기 위한 쿼리
  public List<MedicinesDTO> getMedicineInfoByMedicineNameWithMedicines(String medicineName);

  // 진료 접수 시에 주사약이면서 medicineName에 부합하는 내용을 반환하기 위한 쿼리
  public List<MedicinesDTO> getInjectorInfoByMedicineName(String medicineName);

  public MedicinesDTO getMedicineInfoByMedicineId(int medicineId);

}
