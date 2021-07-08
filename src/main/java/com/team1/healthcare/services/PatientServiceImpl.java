package com.team1.healthcare.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.team1.healthcare.dao.PatientsDAO;
import com.team1.healthcare.dto.PatientsDTO;
import com.team1.healthcare.exception.BadRequestException;
import com.team1.healthcare.exception.ConflictRequestException;
import com.team1.healthcare.exception.NoContentException;
import com.team1.healthcare.vo.common.PatientSearchVO;

@Service
public class PatientServiceImpl implements IPatientService {

  @Autowired
  PatientsDAO patientsDAO;

  @Override
  public boolean addPatientInfo(PatientsDTO patientInfo) {
    // 1) controller에서 넘어온 patientInfo 값이 null인지 아닌지 체크
    if (patientInfo.isNull()) {
      throw new BadRequestException("잘못된 데이터 정보입니다. 올바른 정보인지 확인해주세요",
          new Throwable("Wrong data with addPatientInfo"));
    }
    // 2) 추가하기전 해당 환자의 정보가 저장되어있는지 확인
    PatientsDTO patients = patientsDAO.selectPatientByPostalCode(patientInfo.getPatientPostal());

    if (patients != null) {
      throw new ConflictRequestException("이미 존재하는 환자입니다. 추가할 수 없습니다.",
          new Throwable("Can not Insert for patient"));
    }
    // 3) 환자를 추가
    int result = patientsDAO.addPatient(patientInfo);
    // 4) 추가가 안되었을 경우
    if (result != 1) {
      throw new ConflictRequestException("추가하는데 실패하였습니다",
          new Throwable("Wrong Insert with addPatientInfo"));
    }
    return true;
  }

  @Override
  public List<PatientsDTO> getPatientsListInfo(PatientSearchVO patientSearchInfo) {

    // 1) controller에서 넘어온 patientSearchInfo 값이 null인지 아닌지 체크
    if (patientSearchInfo.isNull()) {
      throw new BadRequestException("잘못된 데이터 정보입니다. 올바른 정보인지 확인해주세요",
          new Throwable("Wrong data with getPatientListInfo"));
    }

    List<PatientsDTO> resultDTO = patientsDAO.selectPatientsByPatientName(patientSearchInfo);

    if (resultDTO.size() == 0) {
      throw new NoContentException("검색 결과가 없습니다.",
          new Throwable("No Searching Data with getPatientsListInfo"));
    }

    return resultDTO;
  }

  public List<PatientsDTO> getPatientListInfoByHospitalCode(String hospitalCode) {
    if (hospitalCode == null) {
      throw new BadRequestException("잘못된 데이터 정보입니다. 올바른 정보인지 확인해주세요",
          new Throwable("Wrong data with getPatientListInfoByHospitalCode"));
    }

    List<PatientsDTO> resultDTO = patientsDAO.selectPatients(hospitalCode);

    if (resultDTO.size() == 0) {
      throw new NoContentException("검색 결과가 없습니다.",
          new Throwable("No Searching Data with getPatientListInfoByHospitalCode"));
    }

    return resultDTO;
  }

  @Override
  public boolean modifyPatientInfo(PatientsDTO patientInfo) {
    // 1) controller에서 넘어온 patientInfo 값이 null인지 아닌지 체크
    if (patientInfo.isUpdateNull() || new Integer(patientInfo.getPatientId()) == null) {
      throw new BadRequestException("잘못된 데이터 정보입니다. 올바른 정보인지 확인해주세요",
          new Throwable("Wrong data with modifyPatientInfo"));
    }
    // 2) 업데이트 정보 넘기기
    int result = patientsDAO.updatePatient(patientInfo);

    // 3) 업데이트 된 행수가 1이 아닐 경우 에러를 발생시킨다
    if (result != 1) {
      throw new ConflictRequestException("수정하는데 실패하였습니다",
          new Throwable("Wrong update with updatePatient"));
    }

    return true;
  }

  @Override
  public boolean removePatientInfo(int patientId) {
    if (new Integer(patientId) == null) {
      throw new BadRequestException("잘못된 데이터 정보입니다. 올바른 정보인지 확인해주세요",
          new Throwable("Wrong data with removePatientInfo"));
    }

    int result = patientsDAO.deletePatient(patientId);

    if (result != 1) {
      throw new ConflictRequestException("수정하는데 실패하였습니다",
          new Throwable("Wrong update with removePatientInfo"));
    }
    return true;
  }

}
