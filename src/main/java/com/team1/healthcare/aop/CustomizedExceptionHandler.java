package com.team1.healthcare.aop;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.team1.healthcare.exception.BadRequestException;
import com.team1.healthcare.exception.ConflictRequestException;
import com.team1.healthcare.exception.NoContentException;
import com.team1.healthcare.exception.NotFoundException;
import com.team1.healthcare.exception.UserNotFoundException;
import com.team1.healthcare.vo.response.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Exception Handling을 위한 Aspect
 * 
 * @author sungwookhwang
 *
 */
@RestController
@EnableWebMvc
@ControllerAdvice
@Slf4j
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {


  /**
   * 전역적인 Exception이 터질 경우 500 INTERNAL SERVER ERROR를 보내고, 알수 없는 에러라는 메시지를 Throw
   * 
   * @param e
   * @param request
   * @return ResponseEntity
   */
  @ExceptionHandler({Exception.class})
  public final ResponseEntity<Object> allException(Exception e, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now().toString(),
        "unknownError", e.getMessage(), request.getDescription(false));
    return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * 사용자가 로그인을 할 시에 생길 문제를 Throw
   * 
   * @param e
   * @param t
   * @param request
   * @return ResponseEntity
   */
  @ExceptionHandler(UserNotFoundException.class)
  public final ResponseEntity<Object> userNotFoundException(Exception e, Throwable t,
      WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now().toString(),
        t.getCause().getMessage(), e.getMessage(), request.getDescription(false));
    return new ResponseEntity<Object>(exceptionResponse, HttpStatus.UNAUTHORIZED);
  }

  /**
   * 사용자가 올바르지 않은 요청을 할 시에 생길 문제를 Throw
   * 
   * @param e
   * @param t
   * @param request
   * @return ResponseEntity
   */
  @ExceptionHandler({BadRequestException.class})

  public final ResponseEntity<Object> badRequestException(Exception e, Throwable t,
      WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now().toString(),
        t.getCause().getMessage(), e.getMessage(), request.getDescription(false));
    return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   * 사용자가 올바른 요청을 하였지만, 비즈니스 로직상 불가능한 문제는 Throw
   * 
   * @param e
   * @param t
   * @param request
   * @return ResponseEntity
   */
  @ExceptionHandler(ConflictRequestException.class)
  public final ResponseEntity<Object> conflictRequestException(Exception e, Throwable t,
      WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now().toString(),
        t.getCause().getMessage(), e.getMessage(), request.getDescription(false));
    return new ResponseEntity<Object>(exceptionResponse, HttpStatus.CONFLICT);
  }

  /**
   * 존재해야하는 데이터를 요청했음에도 불구하고, 데이터가 없는 경우 Throw
   * 
   * @param e
   * @param t
   * @param request
   * @return ResponseEntity
   */
  @ExceptionHandler({NotFoundException.class})
  public final ResponseEntity<Object> notFoundException(Exception e, Throwable t,
      WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now().toString(),
        t.getCause().getMessage(), e.getMessage(), request.getDescription(false));
    return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  /**
   * 정상적으로 요청하였지만, 해당 데이터가 존재하지 않은 경우 Throw
   * 
   * @param e
   * @param t
   * @param request
   * @return ResponseEntity
   */
  @ExceptionHandler(NoContentException.class)
  public final ResponseEntity<Object> noContentException(Exception e, Throwable t,
      WebRequest request) {
    log.info("API URL  = " + request.getDescription(false));
    log.info("Error Message " + e.getMessage());
    return ResponseEntity.noContent().header("Content-Length", "0").build();

  }

  @ExceptionHandler({AccessDeniedException.class})
  public final ResponseEntity<Object> handleAccessDeniedException(Exception e, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now().toString(),
        "noAuthorized", e.getMessage(), request.getDescription(false));
    return new ResponseEntity<Object>(exceptionResponse, HttpStatus.UNAUTHORIZED);
  }



}
