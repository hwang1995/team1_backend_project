package com.team1.healthcare.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.team1.healthcare.dto.DiagnosticInspectionsDTO;

@Mapper
public interface DiagnosticInspectionsDAO {

  /**
   * 진단 검사를 그룹 명으로 조회하기 위한 쿼리
   * 
   * @param String bundleName
   * @return List<DiagnosticInspectionsDTO>
   */
  public List<DiagnosticInspectionsDTO> selectInspectionListByBundleName(String bundleName);


  /**
   * 진단 검사를 그룹 코드로 조회하기 위한 쿼리
   * 
   * @param String bundleCode
   * @return List<DiagnosticInspectionsDTO>
   */
  public List<DiagnosticInspectionsDTO> selectInspectionListByBundleCode(String bundleCode);
}
