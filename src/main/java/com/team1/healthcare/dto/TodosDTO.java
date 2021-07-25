package com.team1.healthcare.dto;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
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
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private LocalDateTime createdDate;

  // 임직원의 FK
  private int memberId;

  // 병원을 식별하기 위한 FK
  private String hospitalCode;

  // 투두의 CHECKED 여부
  private boolean checked;

  // 투두의 작성자
  private String memberName;

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
