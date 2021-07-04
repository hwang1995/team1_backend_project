package com.team1.healthcare.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 존재하지 않는 URL 인 경우 404 Throw
 * 
 * @author sungwookhwang
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

  private static final long serialVersionUID = -367217416653797142L;

  public NotFoundException(String errorMessage, Throwable errorCode) {
    super(errorMessage, errorCode);
  }

}
