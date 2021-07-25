package com.team1.healthcare.vo.diagnostic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosticTestResultVO {
  private int diagTestRecordId;
  private double diagTestValue;
  private int inspectorMemberId;

}
