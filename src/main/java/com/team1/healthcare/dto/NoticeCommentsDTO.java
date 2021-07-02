package com.team1.healthcare.dto;

import java.time.LocalDateTime;
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
  private int memberName;

  // 덧글 내용 (최대 100자)
  private String comment;

  // 덧글 생성 일
  private LocalDateTime createDate;

}
