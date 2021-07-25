package com.team1.healthcare.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.team1.healthcare.commons.CommonUtils;
import com.team1.healthcare.dao.HospitalsDAO;
import com.team1.healthcare.dto.HospitalsDTO;
import com.team1.healthcare.exception.BadRequestException;
import com.team1.healthcare.exception.ConflictRequestException;
import com.team1.healthcare.exception.NoContentException;
import com.team1.healthcare.exception.NotFoundException;

@Service
public class HospitalServiceImpl implements IHospitalService {

  @Autowired
  HospitalsDAO hospitalsDAO;

  // 병원정보추가하기
  @Override
  public boolean addHospital(HospitalsDTO hospitalInfo) {
    // hospitalCode _기준 앞자리 (A-Z 까지 중 랜덤 3자리)
    String hospitalCodeBefore = CommonUtils.createHospitalCode();
    // 병원정보에대한 총 count
    int hospitalCodeAfter = hospitalsDAO.getCount() + 1;
    // 위의 2데이터를 합쳐 hospitalCode를 만든다
    hospitalInfo.setHospitalCode(hospitalCodeBefore + "_" + hospitalCodeAfter);
    // hosptalInfo 의 값이 null이라면 발생시킬 익셉션 발생
    if (hospitalInfo.isNull()) {
      throw new BadRequestException("잘못된 데이터 정보입니다. 올바른 정보인지 확인해주세요",
          new Throwable("Wrong data with addHospital"));
    }

    // 추가한 결과값 받기
    int result = hospitalsDAO.addHospitalInfo(hospitalInfo);

    // 결과값이 1이 아니면 성공적인 추가가 아니므로 익셉션 발생
    if (result != 1) {
      throw new ConflictRequestException("추가에 실패하였습니다",
          new Throwable("Wrong Insert with addHospital"));
    }

    return true;
  }

  // 병원 정보 삭제
  @Override
  public boolean deleteHospital(String hospitalCode) {
    // 병원코드가 null 이라면 익셉션 발생
    if (hospitalCode == null) {
      throw new BadRequestException("잘못된 데이터 정보입니다. 올바른 정보인지 확인해주세요",
          new Throwable("Wrong data with deleteHopsital"));
    }
    // 삭제에 대한 결과값
    int result = hospitalsDAO.deleteHospitalInfo(hospitalCode);

    // 삭제 결과값이 1이아니라면 발생시킬 익셉션
    if (result != 1) {
      throw new ConflictRequestException("삭제에 실패하였습니다",
          new Throwable("Wrong Insert with deleteHospital"));
    }

    return true;
  }

  // 병원 정보 수정
  @Override
  public boolean modifyHospital(HospitalsDTO hospitalInfo) {

    // HospitalDTO 엔티티 중 하나라도 null이라면 발생시킬 익셉션
    if (hospitalInfo.isNull()) {
      throw new BadRequestException("잘못된 데이터 정보입니다. 올바른 정보인지 확인해주세요",
          new Throwable("Wrong data with modifyHospital"));
    }
    // 수정에 대한 결과값
    int result = hospitalsDAO.updateHospitalInfo(hospitalInfo);

    // 결과값이 1이 아니라면 발생시킬 익셉션
    if (result != 1) {
      throw new ConflictRequestException("수정하는데 실패하였습니다",
          new Throwable("Wrong Insert with modifyHospital"));
    }
    return true;
  }

  // 병원정보 리스트 보여주기
  @Override
  public List<HospitalsDTO> showHospitalInfos() {
    // 병원정보 리스트 가져오기
    List<HospitalsDTO> hospitals = hospitalsDAO.getHospitalInfoList();

    // 리스트안에 데이터가 없다면 익셉션 발생=
    if (hospitals.size() == 0) {
      throw new NoContentException("데이터가 검색이되지 않았습니다.",
          new Throwable("No Data with showHospitalInfos"));
    }

    return hospitals;
  }

  // 병원 상세 정보 보여주기=
  @Override
  public HospitalsDTO showHospitalDetail(String hospitalCode) {
    // hospitalCode가 null 이면 익셉션 발생
    if (hospitalCode == null) {
      throw new BadRequestException("잘못된 데이터 정보입니다. 올바른 정보인지 확인해주세요",
          new Throwable("Wrong data with showHospitalDetail"));
    }
    // HospitalDTO 정보 얻기
    HospitalsDTO hospitalInfo = hospitalsDAO.getHospitalInfo(hospitalCode);

    // hospitalDTO가 null 이라면 익셉션 발생
    if (hospitalInfo == null) {
      throw new NotFoundException("데이터가 존재하지 않습니다. 다시 확인해주세요",
          new Throwable("Not Found Data with showHospitalDetail"));
    }
    return hospitalInfo;
  }


}
