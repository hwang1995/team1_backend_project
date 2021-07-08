package com.team1.healthcare.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 공지사항의 커멘트를 담고 있습니다.
 * 
 * @author sungwookhwang
 *
 */
@Getter
@Setter
@ToString
public class NoticeCommentsDTO {
  // 덧글 번호
  private int noticeCommentId;

  // member 엔티티의 FK (임직원을 식별하기 위해)
  private int memberId;

  // member 이름
  private String memberName;

  // 덧글 내용 (최대 100자)
  private String comment;

  // 덧글 생성 일
  private LocalDateTime createdDate;

  // 게시물을 식별하기 위한 notice 엔티티 FK
  private int noticeId;

  @JsonIgnore
  public boolean isNull() {
    Integer noticeIdWrapper = new Integer(noticeId);
    Integer memberIdWrapper = new Integer(memberId);
    Integer noticeCommentIdWrapper = new Integer(noticeCommentId);

    if (noticeIdWrapper == null || memberIdWrapper == null || comment == null || memberName == null
        || noticeCommentIdWrapper == null || createdDate == null) {
      return true;
    } else {
      return false;
    }
  }

}
