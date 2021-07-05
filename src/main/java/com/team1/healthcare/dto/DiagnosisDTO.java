package com.team1.healthcare.dto;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Diagnosis 엔티티는 진료 예약 및 진행의 정보를 담고 있습니다.
 * 
 * @author sungwookhwang
 *
 */
@Getter
@Setter
@ToString
public class DiagnosisDTO {
  // diagnosis 엔티티의 PK
  private int diagId;

  // 진료를 조회할 시 주 별로 가져오기 위해 필요한 컬럼
  private int weekNo;

  // 진료 예약 시작 시간을 기록하기 위함.
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime startDate;

  // 진료 예약 종료 시간을 기록하기 위함.
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime endDate;

  // 진료 예약 시에 환자가 병원의 내방한 목적을 기록하기 위함.
  private String visitPurpose;

  // 의사가 진료 진행 시에 환자의 소견을 적기 위함.
  private String drOpinion;

  // 의사가 진료 진행 시에 환자에게 약을 처방 했는지 여부를 확인하기 위함.
  private boolean isPharmacy;

  // 의사가 진료 진행 시에 환자에게 주사를 처방 했는지 여부를 확인하기 위함.
  private boolean isInjector;

  // 의사가 진료 진행 시에 환자에게 진단 검사를 추가하였는지 여부를 확인하기 위함.
  private boolean isDiagnosticTest;

  // 의사가 진료 진행 시에 바이탈 체크를 실시 했는지 여부를 확인하기 위함.
  private boolean isVital;

  // 진료 예약 상태를 판별하기 위한 컬럼
  // RESERVATION_REGISTER (예약 접수)
  // RESERVATION_COMPLETED (진료 완료)
  // RESERVATION_CANCELED (진료 취소)
  private String reservationStatus;

  // members 엔티티의 FK (진료 예약을 한 의사를 식별하기 위해)
  private int memberId;

  // patients 엔티티의 FK (진료 예약을 한 환자를 식별하기 위해)
  private int patientId;


}
