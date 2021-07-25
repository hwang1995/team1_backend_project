package com.team1.healthcare.vo.diagnosis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosisUpdateVO {

  private int diagId;
  private int patientId;
  private int memberId;
  private String hospitalCode;
  private String visitPurpose;

  @JsonIgnore
  public boolean isNull() {
    Integer diagId = new Integer(this.diagId);
    Integer patientId = new Integer(this.patientId);
    Integer memberId = new Integer(this.memberId);
    if (diagId == null || hospitalCode == null || visitPurpose == null || patientId == null
        || memberId == null) {
      return true;
    }

    return false;
  }
}
