package com.team1.healthcare.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.team1.healthcare.dto.NoticeCommentsDTO;
import com.team1.healthcare.vo.notice.UpdateNoticeCommentVO;

@Mapper
public interface NoticeCommentsDAO {
  /**
   * 게시물의 식별자 (ID)로 댓글 리스트를 조회하기 위한 쿼리
   * 
   * @param noticeId
   * @return List<NoticeCommentsDTO>
   */
  public List<NoticeCommentsDTO> selectCommentsByNoticeId(int noticeId);

  /**
   * 덧글을 추가하기 위한 쿼리
   * 
   * @param NoticeCommentsDTO noticeCommentInfo
   * @return number (영향 받은 행 수)
   */
  public int insertComment(NoticeCommentsDTO noticeCommentInfo);

  /**
   * 덧글을 업데이트 위한 쿼리
   * 
   * @param UpdateNoticeCommentVO updateNoticeCommentInfo
   * @return number (영향 받은 행 수)
   */
  public int updateComment(UpdateNoticeCommentVO updateNoticeCommentInfo);

  /**
   * 덧글을 삭제하기 위한 쿼리
   * 
   * @param int noticeCommentsId
   * @return number (영향 받은 행 수)
   */
  public int deleteComment(int noticeCommentId);

}
