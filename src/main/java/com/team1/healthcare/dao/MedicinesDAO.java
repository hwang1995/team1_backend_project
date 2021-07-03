package com.team1.healthcare.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.team1.healthcare.dto.MedicinesDTO;

@Mapper
public interface MedicinesDAO {
  public List<MedicinesDTO> getMedicineInfoByMedicineName(String medicineName);

}
