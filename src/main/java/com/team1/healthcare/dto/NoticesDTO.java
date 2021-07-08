package com.team1.healthcare.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Notice 엔티티는 각 병원의 대한 공지사항의 정보를 담고 있습니다.
 * 
 * @author sungwookhwang
 *
 */
@Getter
@Setter
@ToString
public class NoticesDTO {
  // notices 엔티티의 PK
  private int noticeId;

  // 공지사항 게시물의 제목
  private String noticeTitle;

  // 공지사항 게시물의 내용 (HTML 코드가 저장)
  private String noticeContent;

  // 공지사항 게시물의 작성자
  private String noticeAuthor;

  // 공지사항 게시물의 작성일
  private LocalDateTime createDate;

  // hospitals 엔티티의 FK (병원을 식별하기 위하여)
  private String hospitalCode;

  // members 엔티티의 FK (작성자를 식별하기 위하여)
  private int memberId;

  // 공지사항의 게시 목록에서 먼저 보이는 내용 (텍스트)
  private String noticeHeadText;

  // 공지사항의 게시 목록에서 먼저 보이는 내용 (이미지)
  private String noticeHeadImage;

  // 공지사항의 조회수
  private int noticeCount;

  @JsonIgnore
  public boolean isNull() {
    Integer memberIdWrapper = new Integer(memberId);
    boolean isTitle = this.noticeTitle.trim().isEmpty();
    boolean isContent = this.noticeContent.trim().isEmpty();
    boolean isAuthor = this.noticeAuthor.trim().isEmpty();
    boolean isCode = this.hospitalCode.trim().isEmpty();
    boolean isHeadText = this.noticeHeadText.trim().isEmpty();
    if (memberIdWrapper == null || isTitle || isContent || isAuthor || isCode || isHeadText) {
      return true;
    }
    return false;

  }

  @JsonIgnore
  public boolean isUpdateNull() {
    boolean isTitle = this.noticeTitle.trim().isEmpty();
    boolean isContent = this.noticeContent.trim().isEmpty();
    boolean isHeadText = this.noticeHeadText.trim().isEmpty();
    if (isTitle || isContent || isHeadText) {
      return true;
    }
    return false;

  }
}
