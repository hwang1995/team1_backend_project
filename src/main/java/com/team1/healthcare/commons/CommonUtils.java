package com.team1.healthcare.commons;

import java.util.List;
import java.util.Map;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CommonUtils {

  /**
   * Spring Security 암호화를 위한 정적 메서드
   * 
   * @param plainPassword
   * @return saltedPassword
   */
  public static String encryptPassword(String plainPassword) {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return passwordEncoder.encode(plainPassword);
  }

  /**
   * DTO, VO의 객체등이 비어있는지 확인하는 정적 메서드
   * 
   * @param obj
   * @return true || false
   */
  public static boolean isEmpty(Object obj) {
    if (obj == null)
      return true;
    if ((obj instanceof String) && (((String) obj).trim().length() == 0)) {
      return true;
    }

    if (obj instanceof Map) {
      return ((Map<?, ?>) obj).isEmpty();
    }

    if (obj instanceof Map) {
      return ((Map<?, ?>) obj).isEmpty();
    }

    if (obj instanceof List) {
      return ((List<?>) obj).isEmpty();
    }

    if (obj instanceof Object[]) {
      return (((Object[]) obj).length == 0);
    }

    return false;

  }

}
