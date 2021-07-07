package com.team1.healthcare.commons;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
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

  public static String createHospitalCode() {
    String PREFIX_HOSPITAL = "";
    for (int i = 0; i < 4; i++) {
      char uc = (char) ((int) (Math.random() * 26 + 65));
      PREFIX_HOSPITAL += uc;
    }
    return PREFIX_HOSPITAL;
  }

  public static long dateTimeToTimestamp(LocalDateTime ldt) {
    long epochSec = ldt.atZone(ZoneId.of("Asia/Seoul")).toEpochSecond();
    return epochSec;
  }

  public static String dateToString(LocalDate ld) {
    return ld.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")).toString();
  }

  public static String dateTimeToString(LocalDateTime ldt) {
    return ldt.format(
        DateTimeFormatter.ofPattern("yyyy-MM-dd a hh시 mm분").withLocale(Locale.forLanguageTag("ko")))
        .toString();
  }


}
