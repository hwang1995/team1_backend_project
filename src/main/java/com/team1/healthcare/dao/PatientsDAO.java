package com.team1.healthcare.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.team1.healthcare.dto.PatientsDTO;
import com.team1.healthcare.vo.common.PatientSearchVO;
import com.team1.healthcare.vo.patient.PatientVO;

@Mapper
public interface PatientsDAO {

  public PatientsDTO selectPatientByPatientId(int patientId);

  public PatientsDTO selectPatientByPostalCode(@Param("postal") String postal);

  public List<PatientsDTO> selectHospitalPatientByPatientName(
      @Param("patientName") String patientName, @Param("hospitalCode") String hospitalCode);

  public int addPatient(PatientsDTO patientInfo);

  public int updatePatient(PatientsDTO patientInfo);

  public int deletePatient(int patientId);

  public List<PatientsDTO> selectPatients(String hospitalCode);

  public List<PatientsDTO> selectPatientsByPatientName(PatientSearchVO patientSearchInfo);

  public List<PatientVO> getPatientInfoByName(PatientSearchVO patientSearchInfo);


}
