package com.team1.healthcare.services;

import java.util.List;
import com.team1.healthcare.vo.auth.UserInfoVO;
import com.team1.healthcare.vo.diagnosis.DiagnosisListVO;
import com.team1.healthcare.vo.diagnosis.RegistDiagnosisVO;

public interface IDiagnosisService {
  public List<DiagnosisListVO> showTodayDiagnosisList(UserInfoVO userInfo);

  public boolean registDiagnosisInfo(RegistDiagnosisVO diagnosisInfo);


}
