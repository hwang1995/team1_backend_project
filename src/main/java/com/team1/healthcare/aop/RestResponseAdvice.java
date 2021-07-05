package com.team1.healthcare.aop;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import com.team1.healthcare.vo.response.ExceptionResponse;
import com.team1.healthcare.vo.response.RestResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * RestController에서 반환하는 값에 RestRespones를 Wrapping 하기 위한 Aspect
 * 
 * @author sungwookhwang
 *
 */
@RestControllerAdvice
@Slf4j
public class RestResponseAdvice implements ResponseBodyAdvice<Object> {

  /**
   * Whether this component supports the given controller method return type and the selected
   * {@code HttpMessageConverter} type.
   */
  @Override
  public boolean supports(MethodParameter returnType,
      Class<? extends HttpMessageConverter<?>> converterType) {
    return !converterType.equals(StringHttpMessageConverter.class);
  }


  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType,
      MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
      ServerHttpRequest request, ServerHttpResponse response) {
    // Exception일 경우 그냥 내보내기 하자.
    if (body instanceof ExceptionResponse) {
      return body;
    }

    final RestResponse<Object> output = new RestResponse<>();
    output.setData(body);
    output.setStatus("success");
    return output;
  }



}
