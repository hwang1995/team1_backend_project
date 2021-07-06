package com.team1.healthcare.dto;

import java.time.LocalDateTime;
import com.team1.healthcare.vo.diagnosis.RegistDiagnosisVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DiagnosticTest �뿏�떚�떚�뒗 吏꾨떒 寃��궗 湲곕줉�쓽 �젙蹂대�� �떞怨� �엳�뒿�땲�떎. �뿰愿��맂 �뿏�떚�떚濡쒕뒗 DiagnosticTestRecord媛� �엳�뒗�뜲 1 : N�쓽 愿�怨꾨�� 留브퀬 �엳�쑝硫�
 * 議고쉶 �떆�뿉 DiagnosticTest�쓽 PK瑜� �넻�빐 �듅�젙 �씪�뿉 留↔꺼吏� 吏꾨떒 寃��궗�쓽 �빆紐⑸뱾�쓣 �솗�씤�븷 �닔 �엳�뒿�땲�떎.
 * 
 * @author sungwookhwang
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class DiagnosticTestsDTO {
  // diagnostic_test �뿏�떚�떚�쓽 PK
  private int diagTestId;

  // members �뿏�떚�떚�쓽 FK (吏꾨떒 寃��궗 湲곕줉�쓣 泥섎갑�븳 �쓽�궗瑜� �떇蹂꾪븯湲� �쐞�빐)
  private int memberId;

  // patients �뿏�떚�떚�쓽 FK (吏꾨떒 寃��궗 湲곕줉�쓣 諛쏆� �솚�옄瑜� �떇蹂꾪븯湲� �쐞�빐)
  private int patientId;

  // 吏꾨떒 寃��궗 湲곕줉�쓽 ���븳 �긽�깭
  // - DIAGNOSTIC_PENDING (��湲�)
  // - DIAGNOSTIC_PROCESSING (�젒�닔)
  // - DIAGNOSTIC_COMPLETED (�셿猷�)
  private String inspectionStatus;

  // 吏꾨떒 寃��궗 湲곕줉�쓣 �벑濡앺븳 �궇吏�
  private LocalDateTime createdDate;

  // diagnosis �뿏�떚�떚�쓽 FK (�솚�옄媛� �뼱�뼚�븳 吏꾨즺瑜� 諛쏆븯�뒗吏� �떇蹂꾪븯湲� �쐞�빐)
  private int diagId;

  private String hospitalCode;

  public DiagnosticTestsDTO(RegistDiagnosisVO diagnosisInfo) {
//    this.memberId = diagnosisInfo.getMemberId();
//    this.patientId = diagnosisInfo.getPatientId();
//    this.diagId = diagnosisInfo.getDiagId();
//    this.hospitalCode = diagnosisInfo.getHospitalCode();
//    this.inspectionStatus = "DIAGNOSTIC_PENDING";
//    this.createdDate = LocalDateTime.now();
  }

}
