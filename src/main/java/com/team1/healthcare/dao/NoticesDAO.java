package com.team1.healthcare.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.team1.healthcare.dto.NoticesDTO;
import com.team1.healthcare.vo.notice.AddNoticeImageVO;
import com.team1.healthcare.vo.notice.SearchNoticeByHospitalCodeAndTitleVO;

@Mapper
public interface NoticesDAO {
  /**
   * 공지사항 게시물을 삽입하기 위한 쿼리
   * 
   * @param NoticesDTO noticeInfo
   * @return number (영향받은 행 수)
   */
  public int insertNotice(NoticesDTO noticeInfo);

  

  /**
   * 게시물의 식별자 (ID)로 조회수를 업데이트 하기 위한 쿼리
   * 
   * @param int noticeId
   * @return number (영향받은 행 수)
   */
  public int updateNoticeCountByNoticeId(int noticeId);

  /**
   * 게시물의 내용을 업데이트 하기 위한 쿼리
   * 
   * @param NoticesDTO noticeInfo
   * @return number (영향받은 행 수)
   */
  public int updateNotice(NoticesDTO noticeInfo);

  /**
   * 게시물의 식별자 (ID)로 게시물을 삭제하기 위한 쿼리
   * 
   * @param int noticeId
   * @return number (영향받은 행 수)
   */
  public int deleteByNoticeId(int noticeId);

  /**
   * 병원 코드 (식별자)로 게시물의 리스트를 조회하기 위한 쿼리
   * 
   * @param String hospitalCode
   * @return List<NoticesDTO>
   */
  public List<NoticesDTO> selectNoticesByHospitalCode(String hospitalCode);



  /**
   * 병원 코드 (식별자), 공지사항 제목으로 게시물의 리스트를 조회하기 위한 쿼리
   * 
   * @param SearchNoticeByHospitalCodeAndTitleVO searchNoticeByHospitalAndTitle
   * @return List<NoticesDTO>
   */
  public List<NoticesDTO> selectNoticesByHospitalCodeAndTitle(
      SearchNoticeByHospitalCodeAndTitleVO searchNoticeByHospitalAndTitle);

  /**
   * 게시물의 식별자(ID)로 게시물의 상세 정보를 조회하기 위한 쿼리
   * 
   * @param int noticeId
   * @return NoticesDTO
   */
  public NoticesDTO selectNoticeDetailByNoticeId(int noticeId);

  /**
   * 게시물에 사진을 첨부하기 위한 쿼리
   * 
   * @param noticeId
   * @return
   */
  public String UploadNoticeImage(AddNoticeImageVO noticeImageInfo);



}
