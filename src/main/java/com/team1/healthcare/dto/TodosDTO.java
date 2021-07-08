package com.team1.healthcare.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TodosDTO {
  // 투두의 PK
  private int todoId;

  // 투두의 내용
  private String todoContent;

  // 투두의 생성 일
  private LocalDateTime createdDate;

  // 임직원의 FK
  private int memberId;

  // 병원을 식별하기 위한 FK
  private String hospitalCode;

  @JsonIgnore
  public boolean isNull() {
    Integer memberIdWrapper = new Integer(memberId);

    if (todoContent.trim().isEmpty() || memberIdWrapper == null || hospitalCode.trim().isEmpty()) {
      return true;
    } else {
      return false;
    }
  }

}
