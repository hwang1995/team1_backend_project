package com.team1.healthcare.services;

import java.io.File;
import java.util.Base64;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.team1.healthcare.dao.NoticeCommentsDAO;
import com.team1.healthcare.dao.NoticesDAO;
import com.team1.healthcare.dto.NoticeCommentsDTO;
import com.team1.healthcare.dto.NoticesDTO;
import com.team1.healthcare.vo.notice.AddNoticeImageVO;
import com.team1.healthcare.vo.notice.SearchNoticeByHospitalCodeAndTitleVO;
import com.team1.healthcare.vo.notice.UpdateNoticeCommentVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NoticeServiceImpl implements INoticeService {
  @Autowired
  private NoticesDAO noticesDAO;
  @Autowired
  private NoticeCommentsDAO noticeCommentsDAO;

  /**
   * 공지사항 게시물을 추가하기 위한 서비스 메서드 시나리오 : 1-1. 요청받은 noticeInfo.isNull가 true인 경우 BadRequestException을
   * 처리해준다. 1-2. 비즈니스 로직 중 불가능하거나 모순이 생긴 경우 ConflictRequestException을 처리해준다. 2-1. 올바른 요청이 들어왔을 경우,
   * noticesDAO.insertNotice의 값이 1이 아니 result = false를 리턴해준다. 2-2. 올바른 요청이 들어왔을 경우,
   * noticesDAO.insertNotice의 값이 1일 경우 result = true를 리턴해준다.
   * 
   * @param : NoticesDTO noticeInfo
   * @return : True or False (게시물이 추가된 여부를 알기 위해)
   * @협력 객체 : NoticesDAO
   */
  @Override
  public boolean addNotice(NoticesDTO noticeInfo) {
    // TODO Auto-generated method stub
    boolean result = true;
    int row = noticesDAO.insertNotice(noticeInfo);
    if (row != 1) {
      result = false;
    }
    return result;
  }

  /**
   * 공지사항 게시물을 제거하기 위한 서비스 메서드 시나리오 : 1-1. 요청받은 noticeId이 null인 경우 BadRequestException을 처리해준다. 2-1.
   * 올바른 요청이 들어왔을 경우, noticesDAO.deleteByNoticeId의 값이 1이 아닐 경우 result = false를 리턴해준다. 2-2. 올바른 요청이
   * 들어왔을 경우, noticesDAO.deleteByNoticeId의 값이 1일 경우 result = true를 리턴해준다.
   * 
   * @param : int noticeId
   * @return : True or False (게시물이 제거된 여부를 알기 위해)
   * @협력 객체 : NoticesDAO
   */
  @Override
  public boolean removeNotice(int noticeId) {
    // TODO Auto-generated method stub
    boolean result = true;
    int row = noticesDAO.deleteByNoticeId(noticeId);
    if (row == 0) {
      result = false;
    }
    return result;
  }

  /**
   * 공지사항 게시물을 수정하기 위한 서비스 메서드 시나리오 : 1-1. 요청받은 noticeInfo.isNull의 값이 true인 경우 BadRequestException을
   * 처리해준다. 2-1. 올바른 요청이 들어왔을 경우, noticesDAO.updateNotice의 값이 1이 아닐 경우 result = false를 리턴해준다. 2-2.
   * 올바른 요청이 들어왔을 경우, noticesDAO.updateNotice의 값이 1일 경우 result = true를 리턴해준다.
   * 
   * @param : NoticesDTO noticeInfo
   * @return : True or False (게시물이 수정된 여부를 알기 위해)
   * @협력 객체 : NoticesDAO
   */
  @Override
  public boolean updateNotice(NoticesDTO noticeInfo) {
    // TODO Auto-generated method stub
    boolean result = true;
    int row = noticesDAO.updateNotice(noticeInfo);
    if (row == 0) {
      result = false;
    }
    return result;
  }

  /**
   * 해당 병원의 공지사항 게시물 리스트를 가져오기 위한 서비스 메서드 시나리오 : 1-1. 요청받은 String hospitalCode의 값이 null인 경우
   * BadRequestException을 처리해준다. 2-1. 올바른 요청이 들어왔지만 , noticesDAO.selectNoticesByHospitalCode의 값이 없어서
   * 불러올 수 없는 경우 NoContentException을 처리해준다. 2-2. 올바른 요청이 들어왔고,
   * noticesDAO.selectNoticesByHospitalCode의 값이 제대로인 경우 notices를 리턴해준다.
   * 
   * @param : String hospitalCode
   * @return : List<NoticesDTO>
   * @협력 객체 : NoticesDAO
   */
  @Override
  public List<NoticesDTO> showNoticesByHospitalCode(String hospitalCode) {
    // TODO Auto-generated method stub
    List<NoticesDTO> notices = noticesDAO.selectNoticesByHospitalCode(hospitalCode);
    return notices;
  }

  /**
   * 해당 병원의 제목을 포함한 공지사항 게시물 리스트를 가져오기 위한 서비스 메서드 시나리오 : 1-1. 요청받은
   * searchNoticeByHospitalAndTitle.isNull의 값이 true인 경우 BadRequestException을 처리해준다. 2-1. 올바른 요청이
   * 들어왔지만 , noticesDAO.selectNoticesByHospitalCodeAndTitle(searchNoticeByHospitalAndTitle)의 값이 없어서
   * 불러올 수 없는 경우 NoContentException을 처리해준다. 2-2. 올바른 요청이 들어왔고,
   * noticesDAO.selectNoticesByHospitalCodeAndTitle(searchNoticeByHospitalAndTitle)의 값이 제대로인 경우
   * notices를 리턴해준다.
   * 
   * @param : SearchNoticeByHospitalCodeAndTitleVO searchNoticeByHospitalAndTitle
   * @return : List<NoticesDTO>
   * @협력 객체 : NoticesDAO
   */
  @Override
  public List<NoticesDTO> showNoticesByHospitalCodeAndTitle(
      SearchNoticeByHospitalCodeAndTitleVO searchNoticeByHospitalAndTitle) {
    // TODO Auto-generated method stub
    List<NoticesDTO> notices =
        noticesDAO.selectNoticesByHospitalCodeAndTitle(searchNoticeByHospitalAndTitle);
    return notices;
  }

  /**
   * 공지사항 게시물의 상세 정보를 가져오기 위한 서비스 메서드 시나리오 : 요청받은 noticeId의 값이 null인 경우 BadRequestException을 처리해준다.
   * 2-1. 올바른 요청이 들어왔을 경우, noticesDAO.selectNoticeDetailByNoticeId(noticeId) 값이 값이 없어서 불러올 수 없는 경우
   * NoContentException을 처리해준다. 2-3. 올바른 요청이 들어왔고,
   * noticesDAO.selectNoticeDetailByNoticeId(noticeId)의 값이 제대로인 경우 notice를 리턴해준다.
   * 
   * @param : int noticeId
   * @return : NoticesDTO notice
   * @협력 객체 : NoticesDAO
   */
  @Override
  public NoticesDTO showNoticeDetailByNoticeId(int noticeId) {
    // TODO Auto-generated method stub
    NoticesDTO notice = noticesDAO.selectNoticeDetailByNoticeId(noticeId);
    return notice;
  }

  /**
   * 공지사항 게시물의 조회수를 수정하기 위한 서비스 메서드 시나리오 : 1-1. 요청받은 noticeId의 값이 null인 경우 BadRequestException을
   * 처리해준다. 2-1. 올바른 요청이 들어왔을 경우, noticesDAO.updateNoticeCountByNoticeId(noticeId)의 값이 1이 아닐 경우
   * result = false를 리턴해준다. 2-2. 올바른 요청이 들어왔을 경우, noticesDAO.updateNoticeCountByNoticeId(noticeId)의
   * 값이 1일 경우 result = true를 리턴해준다.
   * 
   * @param : int noticeId
   * @return : True or False (게시물의 조회수가 수정된 여부를 알기 위해)
   * @협력 객체 : NoticesDAO
   */
  @Override
  public boolean updateNoticeCountByNoticeId(int noticeId) {
    // TODO Auto-generated method stub
    boolean result = true;
    int row = noticesDAO.updateNoticeCountByNoticeId(noticeId);
    if (row == 0) {
      result = false;
    }
    return false;
  }


  /**
   * 게시물의 식별자 (ID)로 댓글 리스트를 조회하기 위한 서비스 메서드 시나리오 : 1-1. 요청받은 noticeId의 값이 null인 경우
   * BadRequestException을 처리해준다. 2-1. 올바른 요청이 들어왔을 경우,
   * noticeCommentsDAO.selectCommentsByNoticeId(noticeId) 값이 값이 없어서 불러올 수 없는 경우 NoContentException을
   * 처리해준다. 2-2. 올바른 요청이 들어왔고, noticeCommentsDAO.selectCommentsByNoticeId(noticeId)의 값이 있지만 불러올 수 없는
   * 경우 NotFoundException을 처리해준다. 2-3. 올바른 요청이 들어왔고,
   * noticeCommentsDAO.selectCommentsByNoticeId(noticeId)의 값이 제대로인 경우 noticeComments를 리턴해준다.
   * 
   * @param : int noticeId
   * @return : List<NoticeCommentsDTO> noticeComments
   * @협력 객체 : NoticeCommentsDAO
   */
  @Override
  public List<NoticeCommentsDTO> showNoticeCommentsByNoticeId(int noticeId) {
    // TODO Auto-generated method stub
    List<NoticeCommentsDTO> noticeComments = noticeCommentsDAO.selectCommentsByNoticeId(noticeId);
    return noticeComments;
  }

  /**
   * 게시물을 추가하기 위한 서비스 메서드 시나리오 : 1-1. 요청받은 noticeCommentInfo.isNull가 true인 경우 BadRequestException을
   * 처리해준다. 2-1. 올바른 요청이 들어왔을 경우, noticeCommentsDAO.insertComment의 값이 1이 아니 result = false를 리턴해준다.
   * 2-2. 올바른 요청이 들어왔을 경우, noticeCommentsDAO.insertComment의 값이 1일 경우 result = true를 리턴해준다.
   * 
   * @param : NoticeCommentsDTO noticeCommentInfo
   * @return : True or False (댓글이 추가된 여부를 알기 위해)
   * @협력 객체 : NoticeCommentsDAO
   */
  @Override
  public boolean addComment(NoticeCommentsDTO noticeCommentInfo) {
    // TODO Auto-generated method stub
    boolean result = true;
    int row = noticeCommentsDAO.insertComment(noticeCommentInfo);
    if (row == 0) {
      result = false;
    }
    return result;
  }

  /**
   * 댓글을 삭제하기 위한 서비스 메서드 시나리오 : 1-1. 요청받은 noticeCommentsId이 null인 경우 BadRequestException을 처리해준다.
   * 2-1. 올바른 요청이 들어왔을 경우, noticeCommentsDAO.deleteComment의 값이 1이 아닐 경우 result = false를 리턴해준다. 2-2.
   * 올바른 요청이 들어왔을 경우, noticeCommentsDAO.deleteComment의 값이 1일 경우 result = true를 리턴해준다.
   * 
   * @param : int noticeCommentsId
   * @return : True or False (댓글이 삭제된 여부를 알기 위해)
   * @협력 객체 : NoticeCommentsDAO
   */
  @Override
  public boolean removeComment(int noticeCommentsId) {
    // TODO Auto-generated method stub
    boolean result = true;
    int row = noticeCommentsDAO.deleteComment(noticeCommentsId);
    if (row == 0) {
      result = false;
    }
    return result;
  }

  /**
   * 덧글을 수정하기 위한 서비스 메서드 시나리오 : 1-1. 요청받은 updateCommentInfo.isNull의 값이 true인 경우 BadRequestException을
   * 처리해준다. 2-1. 올바른 요청이 들어왔을 경우, noticeCommentsDAO.modifyComment의 값이 1이 아닐 경우 result = false를
   * 리턴해준다. 2-2. 올바른 요청이 들어왔을 경우, noticeCommentsDAO.modifyComment의 값이 1일 경우 result = true를 리턴해준다.
   * 
   * @param : UpdateNoticeCommentVO updateCommentInfo
   * @return : True or False (댓글이 수정된 여부를 알기 위해)
   * @협력 객체 : NoticeCommentsDAO
   */
  @Override
  public boolean modifyComment(UpdateNoticeCommentVO updateCommentInfo) {
    // TODO Auto-generated method stub
    boolean result = true;
    int row = noticeCommentsDAO.updateComment(updateCommentInfo);
    if (row != 1) {
      result = false;
    }
    return result;
  }

  /**
   * 게시물에 사진을 첨부하기 위한 서비스 메서드 : 1-1. 요청받은 noticeImageInfo.isNull의 값이 true인 경우 BadRequestException을
   * 처리해준다. 2-1. 올바른 요청이 들어왔을 경우, noticeImageInfo을 리턴해준다.
   * 
   * @param : AddNoticeImageVO noticeImageInfo
   * @return : AddNoticeImageVO noticeImageInfo
   * @협력 객체 : NoticesDAO
   */
  @Override
  public String addNoticeImage(AddNoticeImageVO noticeImageInfo) {
    // TODO Auto-generated method stub
    String[] base64Str = noticeImageInfo.getBase64Content().split(",");

    // base64로 인코딩되어 있는 데이터를 디코딩하여 byte[]로 받음
    byte[] decodedBytes = Base64.getDecoder().decode(base64Str[1]);

    String defaultPath = System.getProperty("user.home") + "/images";
    String filePath = noticeImageInfo.getHospitalCode() + "/" + noticeImageInfo.getImageName();


    try {
      // 지정된 경로에 byte 배열로 받은 이미지를 만들어준다.
      FileUtils.writeByteArrayToFile(new File(defaultPath + filePath), decodedBytes);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return filePath;
  }


}
