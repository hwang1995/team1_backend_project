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

  @Override
  public boolean addHospital(HospitalsDTO hospitalInfo) {
    String hospitalCodeBefore = CommonUtils.createHospitalCode();
    int hospitalCodeAfter = hospitalsDAO.getCount() + 1;
    hospitalInfo.setHospitalCode(hospitalCodeBefore + "_" + hospitalCodeAfter);

    if (hospitalInfo.isNull()) {
      throw new BadRequestException("잘못된 데이터 정보입니다. 올바른 정보인지 확인해주세요",
          new Throwable("Wrong data with addHospital"));
    }

    int result = hospitalsDAO.addHospitalInfo(hospitalInfo);

    if (result != 1) {
      throw new ConflictRequestException("추가에 실패하였습니다",
          new Throwable("Wrong Insert with addHospital"));
    }

    return true;
  }

  @Override
  public boolean deleteHospital(String hospitalCode) {

    if (hospitalCode == null) {
      throw new BadRequestException("잘못된 데이터 정보입니다. 올바른 정보인지 확인해주세요",
          new Throwable("Wrong data with deleteHopsital"));
    }

    int result = hospitalsDAO.deleteHospitalInfo(hospitalCode);

    if (result != 1) {
      throw new ConflictRequestException("삭제에 실패하였습니다",
          new Throwable("Wrong Insert with deleteHospital"));
    }

    return true;
  }

  @Override
  public boolean modifyHospital(HospitalsDTO hospitalInfo) {

    if (hospitalInfo.isNull()) {
      throw new BadRequestException("잘못된 데이터 정보입니다. 올바른 정보인지 확인해주세요",
          new Throwable("Wrong data with modifyHospital"));
    }

    int result = hospitalsDAO.updateHospitalInfo(hospitalInfo);

    if (result != 1) {
      throw new ConflictRequestException("수정하는데 실패하였습니다",
          new Throwable("Wrong Insert with modifyHospital"));
    }
    return true;
  }

  @Override
  public List<HospitalsDTO> showHospitalInfos() {

    List<HospitalsDTO> hospitals = hospitalsDAO.getHospitalInfoList();

    if (hospitals.size() == 0) {
      throw new NoContentException("데이터가 검색이되지 않았습니다.",
          new Throwable("No Data with showHospitalInfos"));
    }

    return hospitals;
  }

  @Override
  public HospitalsDTO showHospitalDetail(String hospitalCode) {

    if (hospitalCode == null) {
      throw new BadRequestException("잘못된 데이터 정보입니다. 올바른 정보인지 확인해주세요",
          new Throwable("Wrong data with showHospitalDetail"));
    }

    HospitalsDTO hospitalInfo = hospitalsDAO.getHospitalInfo(hospitalCode);

    if (hospitalInfo == null) {
      throw new NotFoundException("데이터가 존재하지 않습니다. 다시 확인해주세요",
          new Throwable("Not Found Data with showHospitalDetail"));
    }
    return hospitalInfo;
  }


}
