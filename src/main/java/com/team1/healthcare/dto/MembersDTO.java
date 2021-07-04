package com.team1.healthcare.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.team1.healthcare.commons.CommonUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Members 엔티티는 임직원의 정보를 담고 있습니다.
 * 
 * @author sungwookhwang
 *
 */
@Getter
@Setter
@ToString
public class MembersDTO {
  // members 엔티티의 PK
  private int memberId;

  // 임직원의 이메일

  private String memberEmail;

  // 임직원의 비밀번호

  private String memberPw;

  // 임직원의 이름

  private String memberName;

  // 임직원의 전화번호

  private String memberTel;

  // 임직원의 상세 주소 1

  private String memberAddr1;

  // 임직원의 상세 주소 2
  private String memberAddr2;

  // 임직원의 우편번호
  private String memberPostal;

  // 의사의 진료실
  private String doctorRoom;

  // 임직원의 역할 (Spring Security에서도 사용)
  private String memberAuthority;

  // 임직원 계정의 활성화 여부
  private boolean memberEnabled;

  // hospitals 엔티티의 FK (임직원은 병원에 속해있기 때문에 FK로 설정)
  private String hospitalCode;

  // 임직원의 프로필 이미지 경로
  private String memberImage;

  // 임직원의 성별
  // - 남자 (MEN)
  // - 여자 (WOMEN)

  private String memberGender;

  // 임직원의 생년월일
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate memberBirth;

  // 임직원의 입사일
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime joinedDate;

  // 임직원의 자기 소개 (최대 333자 (한글로))
  private String memberIntroduction;

  // 프포필 색상이나 캘린더의 색상을 식별하기 위함.
  private String memberColor;

  // 임직원의 입사일자를 세팅하기 위해 필요한 메서드
  public void setCurrentTime() {
    joinedDate = LocalDateTime.now();
  }

  public void encryptPassword() {
    this.memberPw = CommonUtils.encryptPassword(memberPw);
  }



}
