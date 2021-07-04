package com.team1.healthcare.aop;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.team1.healthcare.exception.UserNotFoundException;
import com.team1.healthcare.vo.response.ExceptionResponse;

/**
 * Exception Handling을 위한 Aspect
 * 
 * @author sungwookhwang
 *
 */
@RestController
@ControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {


  /**
   * 전역적인 Exception이 터질 경우 500 INTERNAL SERVER ERROR를 보내고, 알수 없는 에러라는 메시지를 보낸다.
   * 
   * @param e
   * @param request
   * @return ResponseEntity
   */
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> allException(Exception e, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now().toString(),
        "unknownError", e.getMessage(), request.getDescription(false));
    return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public final ResponseEntity<Object> userNotFoundException(Exception e, Throwable t,
      WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now().toString(),
        t.getCause().getMessage(), e.getMessage(), request.getDescription(false));
    return new ResponseEntity<Object>(exceptionResponse, HttpStatus.UNAUTHORIZED);
  }



}
