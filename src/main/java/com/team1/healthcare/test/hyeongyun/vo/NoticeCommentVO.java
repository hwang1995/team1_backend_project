package com.team1.healthcare.test.hyeongyun.vo;

import java.time.LocalDateTime;
import com.team1.healthcare.dto.NoticeCommentsDTO;
import com.team1.healthcare.dto.NoticesDTO;

public class NoticeCommentVO {
  // Notices 엔티티
  private String hospitalCode;
  private int noticeId;

  // NoticeComment 엔티티
  private int noticeCommentId;
  private int memberId;
  private String memberName;
  private String comment;
  private LocalDateTime createdDate;


  public NoticeCommentVO(NoticesDTO noticeInfo, NoticeCommentsDTO noticeCommentInfo) {
    // Notices 엔티티
    this.hospitalCode = noticeInfo.getHospitalCode();
    this.noticeId = noticeInfo.getNoticeId();


    // NoticeComment 엔티티
    this.noticeCommentId = noticeCommentInfo.getNoticeCommentId();
    this.memberId = noticeCommentInfo.getMemberId();
    this.memberName = noticeCommentInfo.getMemberName();
    this.comment = noticeCommentInfo.getComment();
    this.createdDate = noticeCommentInfo.getCreateDate();

  }
}
