package com.team1.healthcare.vo.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RestResponse<T> {
  private String status;
  private T data;
}
