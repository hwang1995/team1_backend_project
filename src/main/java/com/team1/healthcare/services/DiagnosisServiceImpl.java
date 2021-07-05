package com.team1.healthcare.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.team1.healthcare.vo.DiagnosisVO;
import com.team1.healthcare.vo.UserInfoVO;

@Service
public class DiagnosisServiceImpl implements IDiagnosisService {

  // @Autowired
  // private DiagnosisDAO diagnosisDAO;
  //
  // @Autowired
  // private PatientsDAO patientsDAO;

  @Override
  public List<DiagnosisVO> getDiagnosisList(UserInfoVO userInfo) {
    // Diagnosis는 memberId로 식별하기도 하며, 오늘날짜를 가져와야 한다.
    //
    // List<DiagnosisDTO> diagnosisList =
    // diagnosisDAO.selectDiagnosisListByMemberId(userInfo.getMemberId());
    //
    // 보내줄 List 객체 생성
    // List<DiagnosisVO> sendDiagnosisList = new ArrayList<DiagnosisVO>();
    // for (DiagnosisDTO diagnosis : diagnosisList) {
    // // DiagnosisDTO에 있는 patientId로 patient를 조회한다.
    // int patientId = diagnosis.getPatientId();
    // PatientsDTO patientInfo = patientsDAO.selectPatientByPatientId(patientId);
    // DiagnosisVO diagnosisVO = new DiagnosisVO(patientInfo, diagnosis);
    // sendDiagnosisList.add(diagnosisVO);
    // }
    //
    //
    // // DiagnosisDTO에 있는 patientId로 patient를 조회한다.
    //
    //
    // return sendDiagnosisList;

    return null;
  }


}
