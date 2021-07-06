package com.team1.healthcare.dto;

import com.team1.healthcare.vo.diagnosis.RegistDiagnosisVO;
import com.team1.healthcare.vo.diagnosis.VitalVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * VitalRecords �뿏�떚�떚�뒗 �쓽�궗媛� 吏꾨즺�떆�뿉 諛붿씠�깉 泥댄겕�쓽 �젙蹂대�� ���옣�븷 �뻹�뿉 �궗�슜�맗�땲�떎.
 * 
 * @author sungwookhwang
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class VitalRecordsDTO {
  // vital_records �뿏�떚�떚�쓽 PK
  private int vrId;

  // diagnosis �뿏�떚�떚�쓽 FK (�뼱�뼡 吏꾨즺�뿉�꽌 諛붿씠�깉 泥댄겕瑜� 諛쏆븯�뒗吏� �떇蹂꾪븯湲� �쐞�빐)
  private int diagId;

  // �삁�븬
  private int bloodPressure;

  // 留λ컯
  private int pulse;

  // �샇�씉 �닔
  private int respirationRate;

  // �삩�룄
  private int temperature;

  // member �뿏�떚�떚�쓽 FK (�뼱�뼡 �엫吏곸썝�씠 諛붿씠�깉 泥댄겕瑜� �뻽�뒗吏� �떇蹂꾪븯湲� �쐞�빐)
  private int memberId;

  public VitalRecordsDTO(VitalVO vitalInfo, RegistDiagnosisVO diagnosisInfo) {
//    this.bloodPressure = vitalInfo.getBloodPressure();
//    this.pulse = vitalInfo.getPulse();
//    this.respirationRate = vitalInfo.getRespirationRate();
//    this.temperature = vitalInfo.getTemperature();
//    this.diagId = diagnosisInfo.getDiagId();
//    this.memberId = diagnosisInfo.getMemberId();

  }
}
