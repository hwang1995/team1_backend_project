package com.team1.healthcare.vo.notice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateNoticeCommentVO {
  private int noticeCommentId;
  private String comment;

  public boolean isNull() {

    Integer noticeCommentIdWrapper = new Integer(noticeCommentId);
    if (noticeCommentIdWrapper == null || comment == null) {
      return true;
    } else {
      return false;
    }
  }
}
