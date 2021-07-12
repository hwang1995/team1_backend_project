package com.team1.healthcare.vo.diagnosis;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime start;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime end;
  private String bgColor; // member -> color
  private String color; // textColor -> white
  private String visitPurpose;
  private String doctorRoom;
  private String doctorName;


  private int patientId;
  private String patientName;
  private LocalDate patientBirth;

  public ReservationVO(DiagnosisDTO diagnosisInfo, PatientsDTO patientInfo, MembersDTO memberInfo) {
    // DiagnosisDTO
    this.id = diagnosisInfo.getDiagId();
    this.calendarId = diagnosisInfo.getWeekNo();
    this.start = diagnosisInfo.getStartDate();
    this.end = diagnosisInfo.getEndDate();
    this.visitPurpose = diagnosisInfo.getVisitPurpose();

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

  @JsonIgnore
  public boolean isNull() {
    Integer id = new Integer(this.id);
    Integer calendarId = new Integer(this.calendarId);
    Integer patientId = new Integer(this.patientId);
    if (id == null || calendarId == null || title == null || category == null || start == null
        || end == null || bgColor == null || color == null || visitPurpose == null
        || doctorRoom == null || patientId == null || patientName == null || patientBirth == null) {
      return true;
    }
    return false;
  }


}
