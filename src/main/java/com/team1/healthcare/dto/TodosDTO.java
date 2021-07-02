package com.team1.healthcare.dto;

import java.time.LocalDateTime;
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

}
