package com.team1.healthcare.services;

import java.util.List;
import com.team1.healthcare.dto.PatientsDTO;
import com.team1.healthcare.vo.common.PatientSearchVO;

public interface IPatientService {

  /**
   * 환자 정보를 추가하기 위한 서비스 인터페이스
   * 
   * @param PatientsDTO patientInfo
   * @return true or false (성공 여부)
   */
  public boolean addPatientInfo(PatientsDTO patientInfo);

  /**
   * 환자 정보를 검색 혹은 다 보여주기 위한 서비스 인터페이스
   * 
   * @param PatientSearchVO patientSearchInfo
   * @return List<PatientsDTO>
   */
  public List<PatientsDTO> getPatientsListInfo(PatientSearchVO patientSearchInfo);

  /**
   * 환자 정보를 수정하기 위한 서비스 인터페이스
   * 
   * @param PatientsDTO patientInfo
   * @return true or false (성공 여부)
   */
  public boolean modifyPatientInfo(PatientsDTO patientInfo);

  /**
   * 환자 정보를 삭제하기 위한 서비스 인터페이스
   * 
   * @param int patientId
   * @return true or false (성공 여부)
   */
  public boolean removePatientInfo(int patientId);


}
