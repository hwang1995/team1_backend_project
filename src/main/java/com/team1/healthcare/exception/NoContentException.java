package com.team1.healthcare.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 정상적인 실행이지만, 특정 리소스가 있을 수도 없을 수도 있는 경우 Throw
 * 
 * @author sungwookhwang
 *
 */
@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoContentException extends RuntimeException {

  private static final long serialVersionUID = 1764373287169052379L;

  public NoContentException(String errorMessage, Throwable errorCode) {
    super(errorMessage, errorCode);
  }

}
