package com.team1.healthcare.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 잘못된 요청이 들어올 시 BadRequestException을 처리해준다.
 * 
 * @author sungwookhwang
 *
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

  private static final long serialVersionUID = -5990187117837923779L;

  public BadRequestException(String errorMessage, Throwable errorCode) {
    super(errorMessage, errorCode);
  }

}
