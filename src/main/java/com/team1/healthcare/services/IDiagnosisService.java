package com.team1.healthcare.services;

import java.util.List;
import com.team1.healthcare.vo.DiagnosisVO;
import com.team1.healthcare.vo.UserInfoVO;

public interface IDiagnosisService {
  public List<DiagnosisVO> getDiagnosisList(UserInfoVO userInfo);
}
