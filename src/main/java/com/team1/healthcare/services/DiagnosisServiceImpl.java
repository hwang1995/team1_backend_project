package com.team1.healthcare.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.team1.healthcare.vo.auth.UserInfoVO;
import com.team1.healthcare.vo.diagnosis.DiagnosisListVO;
import com.team1.healthcare.vo.diagnosis.RegistDiagnosisVO;

@Service
public class DiagnosisServiceImpl implements IDiagnosisService {

  @Override
  public List<DiagnosisListVO> showTodayDiagnosisList(UserInfoVO userInfo) {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public boolean registDiagnosisInfo(RegistDiagnosisVO diagnosisInfo) {
    // TODO Auto-generated method stub
    return false;
  }


}
