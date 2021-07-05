package com.team1.healthcare.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
  private String occuredDate; // 예외 발생 시각
  private String status; // 예외 상태 코드
  private String message; // 예외 메시지
  private String content; // 예외가 어느 요청에서 났는가?
}
