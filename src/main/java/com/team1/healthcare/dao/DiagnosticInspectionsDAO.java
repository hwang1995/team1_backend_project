package com.team1.healthcare.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.team1.healthcare.dto.DiagnosticInspectionsDTO;

@Mapper
public interface DiagnosticInspectionsDAO {

  public List<DiagnosticInspectionsDTO> selectInspectionListByBundleName(String bundleName);

  public List<DiagnosticInspectionsDTO> selectInspectionListByBundleCode(String bundleCode);
}
