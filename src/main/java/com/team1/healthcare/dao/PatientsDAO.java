package com.team1.healthcare.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.team1.healthcare.dto.PatientsDTO;
import com.team1.healthcare.vo.common.PatientSearchVO;

@Mapper
public interface PatientsDAO {

  /**
   * 환자에 대한 상세정보 가져오기
   * 
   * @param int patientId
   * @return List<PatientsDTO>
   */
  public PatientsDTO selectPatientByPatientId(int patientId);

  /**
   * 환자 번호와 병원 코드로 환자 상세정보 가져오기
   * 
   * @param patientId
   * @param String hospitalCode
   * @return PatientsDTO
   */
  public PatientsDTO selectPatientByPatienIdAndHospitalCode(@Param("patientId") int patientId,
      @Param("hospitalCode") String hospitalCode);

  /**
   * 주소를 통해 환자정보 가져오기
   * 
   * @param String postal
   * @return PatientsDTO
   */
  public PatientsDTO selectPatientByPostalCode(@Param("postal") String postal,
      @Param("name") String patientName);

  /**
   * 병원코드를 통해 환자에 대한 리스트를 가져오기
   * 
   * @param patientName
   * @param String hospitalCode
   * @return List<PatientsDTO>
   */
  public List<PatientsDTO> selectHospitalPatientByPatientName(
      @Param("patientName") String patientName, @Param("hospitalCode") String hospitalCode);

  /**
   * 환자 정보 추가
   * 
   * @param PatientsDTO patientInfo
   * @return number
   */
  public int addPatient(PatientsDTO patientInfo);

  /**
   * 환자 정보 수정
   * 
   * @param PatientsDTO patientInfo
   * @return number
   */
  public int updatePatient(PatientsDTO patientInfo);

  /**
   * 환자 정보 삭제
   * 
   * @param patientId
   * @return number
   */
  public int deletePatient(int patientId);


  /**
   * 병원 코드를 통해 환자 정보 리스트 가져오기
   * 
   * @param String hospitalCode
   * @return List<PatientDTO>
   */
  public List<PatientsDTO> selectPatients(String hospitalCode);

  /**
   * 환자이름 검색을 통해 가져오는 환자 정보 리스트
   * 
   * @param PatientSearchVO patientSearchInfo
   * @return List<PatientDTO>
   */
  public List<PatientsDTO> selectPatientsByPatientName(PatientSearchVO patientSearchInfo);

  /**
   * 환자이름 검색을 통해 가져오는 환자 정보 리스트
   * 
   * @param PatientSearchVO patientSearchInfo
   * @return List<PatientDTO>
   */
  public List<PatientsDTO> getPatientInfoByName(PatientSearchVO patientSearchInfo);


}
