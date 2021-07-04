package com.team1.healthcare.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 비즈니스 로직 중 불가능하거나 모순이 생긴 경우 Throw
 * 
 * @author sungwookhwang
 *
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictRequestException extends RuntimeException {

  private static final long serialVersionUID = 4690435364332080796L;

  public ConflictRequestException(String errorMessage, Throwable errorCode) {
    super(errorMessage, errorCode);
  }


}
