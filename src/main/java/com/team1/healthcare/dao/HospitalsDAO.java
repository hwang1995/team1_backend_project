package com.team1.healthcare.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.team1.healthcare.dto.HospitalsDTO;

@Mapper
public interface HospitalsDAO {

  /**
   * 병원에 대한 상세정보 가져오기
   * 
   * @param String hospitalCode
   * @return HospitalsDTO
   */
  public HospitalsDTO getHospitalInfo(String hospitalCode);

  /**
   * 병원 정보 추가하기
   * 
   * @param HospitalsDTO hospitalIinfo
   * @return number
   */
  public int addHospitalInfo(HospitalsDTO hospitalIinfo);

  /**
   * 병원 정보에 대한 totalCount
   * 
   * @return number
   */
  public int getCount();

  /**
   * 병원 정보 수정하기
   * 
   * @param HospitalsDTO hospitalIinfo
   * @return number
   */
  public int updateHospitalInfo(HospitalsDTO hospitalIinfo);

  /**
   * 병원 정보 삭제하기
   * 
   * @param String hospitalCode
   * @return number
   */
  public int deleteHospitalInfo(String hospitalCode);

  /**
   * 병원 정보 리스트 얻기
   * 
   * @return List<HospitalsDTO>
   */
  public List<HospitalsDTO> getHospitalInfoList();
}
