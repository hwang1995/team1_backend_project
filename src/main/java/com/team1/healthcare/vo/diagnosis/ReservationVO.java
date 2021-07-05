package com.team1.healthcare.vo.diagnosis;

import java.time.LocalDateTime;
import com.team1.healthcare.dto.DiagnosisDTO;
import com.team1.healthcare.dto.MembersDTO;
import com.team1.healthcare.dto.PatientsDTO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ReservationVO {
  private int id; // diagId
  private int calendarId; // weekNum
  private String title; // patientName
  private String category; // time
  private LocalDateTime start;
  private LocalDateTime end;
  private String bgColor; // member -> color
  private String color; // textColor -> white
  private String drOpinion;
  private String doctorRoom;
  private String doctorName;

  private int patientId;
  private String patientName;
  private LocalDateTime patientBirth;

  public ReservationVO(DiagnosisDTO diagnosisInfo, PatientsDTO patientInfo, MembersDTO memberInfo) {
    // DiagnosisDTO
    this.id = diagnosisInfo.getDiagId();
    this.calendarId = diagnosisInfo.getWeekNo();
    this.start = diagnosisInfo.getStartDate();
    this.end = diagnosisInfo.getEndDate();
    this.drOpinion = diagnosisInfo.getDrOpinion();

    // PatientsDTO
    this.title = patientInfo.getPatientName();
    this.patientId = patientInfo.getPatientId();
    this.patientName = patientInfo.getPatientName();
    this.patientBirth = patientInfo.getPatientBirth();

    // MembersDTO
    this.bgColor = memberInfo.getMemberColor();
    this.doctorName = memberInfo.getMemberName();
    this.doctorRoom = memberInfo.getDoctorRoom();

    // Constant
    this.category = "time";
    this.color = "white";

  }



}
