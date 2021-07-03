package com.team1.healthcare.services;

import java.util.List;
import com.team1.healthcare.vo.auth.UserInfoVO;
import com.team1.healthcare.vo.diagnosis.DiagnosisVO;

public interface IDiagnosisService {
  public List<DiagnosisVO> getDiagnosisList(UserInfoVO userInfo);
}
