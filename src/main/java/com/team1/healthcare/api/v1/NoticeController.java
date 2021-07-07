package com.team1.healthcare.api.v1;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.team1.healthcare.dto.NoticeCommentsDTO;
import com.team1.healthcare.dto.NoticesDTO;
import com.team1.healthcare.services.NoticeServiceImpl;
import com.team1.healthcare.vo.notice.AddNoticeImageVO;
import com.team1.healthcare.vo.notice.UpdateNoticeCommentVO;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/notice")
public class NoticeController {
  @Autowired

  private NoticeServiceImpl noticeService;

  // 1. GET 공지사항의 게시물 가져오기
  @GetMapping("")
  public List<NoticesDTO> getNoticesList(String hospitalCode) {
    List<NoticesDTO> list = noticeService.showNoticesByHospitalCode(hospitalCode);
    return list;
  }

  // 2. GET 해당 병원의 해당 공지사항의 댓글들 보여주기
  @GetMapping("/comments")
  public List<NoticeCommentsDTO> getNoticeCommentsList(int noticeId) {
    List<NoticeCommentsDTO> list = noticeService.showNoticeCommentsByNoticeId(noticeId);
    return list;
  }

  // 2.5. GET AND PUT 해당 병원의 공지사항의 상세정보 보여주기 + 조회수 업데이트
  @GetMapping("/notice")
  public NoticesDTO getNoticesList(int noticeId) {
    noticeService.updateNoticeCountByNoticeId(noticeId);
    NoticesDTO notice = noticeService.showNoticeDetailByNoticeId(noticeId);
    return notice;
  }


  // 3. POST 해당 병원의 공지사항 게시물을 생성하기

  @PostMapping("")
  public boolean createNotice(@RequestBody NoticesDTO noticeInfo) {
    boolean result = noticeService.addNotice(noticeInfo);
    return result;
  }

  // 3.5. POST 해당 병원의 해당 공지사항의 댓글을 생성하기
  @PostMapping("/comment")
  public boolean createNoticeComment(@RequestBody NoticeCommentsDTO noiceCommentInfo) {
    boolean result = noticeService.addComment(noiceCommentInfo);
    return result;
  }


  // 5. POST 게시물을 추가 및 수정시에 이미지를 서버에 올리고 클라이언트에 URL을 전달한다.

  @PostMapping("/images")
  public String uploadNoticeImages(@RequestBody AddNoticeImageVO noticeImageInfo) {
    String Base64String = noticeService.addNoticeImage(noticeImageInfo);
    return Base64String;
  }

  // 6. PUT 게시물 수정하기

  @PutMapping("")
  public boolean modifyNotice(@RequestBody NoticesDTO noticeInfo) {
    boolean result = noticeService.updateNotice(noticeInfo);
    return result;
  }

  @PutMapping("/comment")
  public boolean modifyComment(@RequestBody UpdateNoticeCommentVO updateCommentInfo) {
    boolean result = noticeService.modifyComment(updateCommentInfo);
    return result;
  }

  // 7. DELETE 게시물 삭제하기

  @DeleteMapping("")
  public boolean removeNotice(@RequestParam int noticeId) {
    boolean result = noticeService.removeNotice(noticeId);
    return result;
  }

  @DeleteMapping("/comment")
  public boolean removeComment(@RequestParam int noticeCommentId) {
    boolean result = noticeService.removeComment(noticeCommentId);
    return result;
  }



}
