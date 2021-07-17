package com.team1.healthcare.services;

import java.util.List;
import com.team1.healthcare.dto.NoticeCommentsDTO;
import com.team1.healthcare.dto.NoticesDTO;
import com.team1.healthcare.vo.notice.AddNoticeImageVO;
import com.team1.healthcare.vo.notice.SearchNoticeByHospitalCodeAndTitleVO;
import com.team1.healthcare.vo.notice.UpdateNoticeCommentVO;

public interface INoticeService {
  /**
   * 공지사항 게시물을 추가하기 위한 서비스 인터페이스
   * 
   * @param NoticesDTO noticeInfo
   * @return true or false (게시물이 추가된 여부를 알기 위해)
   */
  public boolean addNotice(NoticesDTO noticeInfo);

  /**
   * 공지사항 게시물을 삭제하기 위한 서비스 인터페이스
   * 
   * @param int noticeId
   * @return true or false (게시물이 삭제된 여부를 알기 위해)
   */
  public boolean removeNotice(int noticeId);

  /**
   * 공지사항 게시물을 수정하기 위한 서비스 인터페이스
   * 
   * @param NoticesDTO noticeInfo
   * @return true or false (게시물이 수정된 여부를 알기 위해)
   */
  public boolean updateNotice(NoticesDTO noticeInfo);

  /**
   * 해당 병원의 공지사항 게시물 리스트를 가져오기 위한 서비스 인터페이스
   * 
   * @param String hospitalCode
   * @return List<NoticesDTO>
   */
  public List<NoticesDTO> showNoticesByHospitalCode(String hospitalCode);

  /**
   * 해당 병원의 제목을 포함한 공지사항 게시물 리스트를 가져오기 위한 서비스 인터페이스
   * 
   * @param SearchNoticeByHospitalCodeAndTitleVO searchNoticeByHospitalAndTitle
   * @return List<NoticesDTO>
   */
  public List<NoticesDTO> showNoticesByHospitalCodeAndTitle(
      SearchNoticeByHospitalCodeAndTitleVO searchNoticeByHospitalAndTitle);


  /**
   * 공지사항 게시물의 상세 정보를 가져오기 위한 서비스 인터페이스
   * 
   * @param int noticeId
   * @return NoticesDTO
   */
  public NoticesDTO showNoticeDetailByNoticeId(int noticeId);

  /**
   * 공게시물의 식별자 (ID)로 조회수를 업데이트 하기 위한 위한 서비스 인터페이스
   * 
   * @param int noticeId
   * @return true or false (게시물의 조회수가 수정된 여부를 알기 위해)
   */
  public boolean updateNoticeCountByNoticeId(int noticeId);

  /**
   * 게시물의 식별자(ID)로 덧글 리스트를 가져오기 위한 서비스 인터페이스
   * 
   * @param int noticeId
   * @return List<NoticeCommentsDTO>
   */
  public List<NoticeCommentsDTO> showNoticeCommentsByNoticeId(int noticeId);

  /**
   * 해당 게시물의 덧글을 추가하기 위한 서비스 인터페이스
   * 
   * @param NoticeCommentsDTO noticeCommentInfo
   * @return true or false (덧글이 성공적으로 추가된 여부를 알기 위해)
   */
  public boolean addComment(NoticeCommentsDTO noticeCommentInfo);

  /**
   * 해당 게시물의 덧글을 삭제하기 위한 서비스 인터페이스
   * 
   * @param int noticeCommentId
   * @return true or false (덧글을 성공적으로 삭제된 여부를 알기 위해)
   */
  public boolean removeComment(int noticeCommentId);

  /**
   * 해당 게시물의 덧글들을 삭제하기 위한 서비스 인터페이스
   * 
   * @param int noticeId
   * @return true or false (덧글들이 성공적으로 삭제된 여부를 알기 위해)
   */
  public boolean removeComments(int noticeId);

  /**
   * 해당 게시물의 덧글을 수정하기 위한 서비스 인터페이스
   * 
   * @param UpdateNoticeCommentVO updateCommentInfo
   * @return true or false (덧글이 성공적으로 수정된 여부를 알기 위해)
   */
  public boolean modifyComment(UpdateNoticeCommentVO updateCommentInfo);

  /**
   * 해당 게시물의 이미지를 추가하기 위한 서비스 인터페이스
   * 
   * @param AddNoticeImageVO noticeImageInfo
   * @return imageSrc (이미지 경로가 리턴됩니다)
   */
  public String addNoticeImage(AddNoticeImageVO noticeImageInfo);

  /**
   * 댓글을 삭제하기 위한 서비스 메서드 시나리오 : 1-1. 요청받은 noticeId이 null인 경우 BadRequestException을 처리해준다. 2-1. 올바른
   * 요청이 들어왔을 경우, noticeCommentsDAO.deleteComment의 값이 1이 아닐 경우 result = false를 리턴해준다. 2-2. 올바른 요청이
   * 들어왔을 경우, noticeCommentsDAO.deleteComment의 값이 1일 경우 result = true를 리턴해준다.
   * 
   * @param : int noticeCommentsId
   * @return : True or False (댓글이 삭제된 여부를 알기 위해)
   * @협력 객체 : NoticeCommentsDAO
   */


}
