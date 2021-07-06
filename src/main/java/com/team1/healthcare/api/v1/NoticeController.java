package com.team1.healthcare.api.v1;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.team1.healthcare.dto.NoticeCommentsDTO;
import com.team1.healthcare.dto.NoticesDTO;
import com.team1.healthcare.services.NoticeServiceImpl;
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

  // 4. POST 해당 병원의 공지사항 게시물을 추가하기

  // 5. POST 게시물을 추가 및 수정시에 이미지를 서버에 올리고 클라이언트에 URL을 전달한다.
  // 6. PUT 게시물 수정하기
  // 7. DELETE 게시물 삭제하기



}
