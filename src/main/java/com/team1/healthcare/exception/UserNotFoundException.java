package com.team1.healthcare.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 로그인 시에 존재하지 않는 병원이나, 존재하지 않는 계정의 Exception을 처리하기 위한 Class
 * 
 * @author sungwookhwang
 *
 */
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UserNotFoundException extends RuntimeException {
  private static final long serialVersionUID = -2271082697259307165L;

  public UserNotFoundException(String errorMessage, Throwable errorCode) {
    super(errorMessage, errorCode);
  }
}
