package com.team1.healthcare.services;

import java.util.List;
import com.team1.healthcare.dto.HospitalsDTO;

public interface IHospitalService {


  /**
   * 병원 정보를 추가하기 위한 서비스 인터페이스
   * 
   * @param HospitalsDTO hospitalInfo
   * @return true or false (성공 여부)
   */
  public boolean addHospital(HospitalsDTO hospitalInfo);

  /**
   * 병원 정보를 삭제하기 위한 서비스 인터페이스
   * 
   * @param String hospitalCode
   * @return true or false (성공 여부)
   */
  public boolean deleteHospital(String hospitalCode);

  /**
   * 병원 정보를 수정하기 위한 서비스 인터페이스
   * 
   * @param HospitalsDTO hospitalInfo
   * @return true or false (성공 여부)
   */
  public boolean modifyHospital(HospitalsDTO hospitalInfo);

  /**
   * 병원 정보 리스트를 가져오기 위한 서비스 인터페이스
   * 
   * @return List<HospitalsDTO>
   */
  public List<HospitalsDTO> showHospitalInfos();

  /**
   * 병원 상세 정보를 가져오기 위한 서비스 인터페이스
   * 
   * @param String hospitalCode
   * @return HospitalsDTO
   */
  public HospitalsDTO showHospitalDetail(String hospitalCode);
}
