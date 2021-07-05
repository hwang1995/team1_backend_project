package com.team1.healthcare.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.team1.healthcare.dto.NoticeCommentsDTO;
import com.team1.healthcare.dto.NoticesDTO;
import com.team1.healthcare.vo.notice.AddNoticeImageVO;
import com.team1.healthcare.vo.notice.UpdateNoticeCommentVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NoticeServiceImpl implements INoticeService {
  @Override
  public boolean addNotice(NoticesDTO noticeInfo) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean removeNotice(int noticeId) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean updateNotice(NoticesDTO noticeInfo) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public List<NoticesDTO> showNoticesByHospitalCode(String hospitalCode) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NoticesDTO showNoticeDetailByNoticeId(int noticeId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<NoticeCommentsDTO> showNoticeCommentsByNoticeId(int noticeId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean addComment(NoticeCommentsDTO noticeCommentInfo) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean removeComment(int noticeCommentsId) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean modifyComment(UpdateNoticeCommentVO updateCommentInfo) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String addNoticeImage(AddNoticeImageVO noticeImageInfo) {
    // TODO Auto-generated method stub
    return null;
  }

}
