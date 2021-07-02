package com.team1.healthcare.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.team1.healthcare.dto.DiagnosisDTO;

@Mapper
public interface DiagnosisDAO {

  public List<DiagnosisDTO> selectDiagnosisListByMemberId(int memberId);

}
