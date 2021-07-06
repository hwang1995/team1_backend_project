package com.team1.healthcare.api.v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.team1.healthcare.dto.NoticesDTO;
import com.team1.healthcare.services.NoticeServiceImpl;

@RestController
@RequestMapping("/api/v1/notice")
public class NoticeController {
  @Autowired
  private NoticeServiceImpl noticeService;

  // 1. GET 해당 병원의 대쉬보드에서 공지사항의 목록을 보여주기
  @GetMapping("")
  public Map<String, Object> getNoticesList(String hospitalCode) {
    List<NoticesDTO> list = new ArrayList<>();
    Map<String, Object> map = new HashMap<>();
    list = noticeService.showNoticesByHospitalCode(hospitalCode);
    map.put("notices", list);
    return map;
  }

  // 2. GET 공지사항의 게시물 가져오기
  // 3. GET 해당 병원의 공지사항 게시판에서 목록을 가져오기
  // 4. POST 해당 병원의 공지사항 게시물을 추가하기
  // 5. POST 게시물을 추가 및 수정시에 이미지를 서버에 올리고 클라이언트에 URL을 전달한다.
  // 6. PUT 게시물 수정하기
  // 7. DELETE 게시물 삭제하기



}
