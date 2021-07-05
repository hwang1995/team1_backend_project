package com.team1.healthcare.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.team1.healthcare.dto.TodosDTO;

@Mapper
public interface TodosDAO {
  /**
   * 병원코드 (식별자)로 Todo 리스트를 조회하는 쿼리
   * 
   * @param String hospitalCode
   * @return List<TodosDTO>
   */
  public List<TodosDTO> selectTodosByHospitalCode(String hospitalCode);

  /**
   * 임직원 ID(식별자)로 Todo 리스트를 조회하는 쿼리
   * 
   * @param int memberId
   * @return List<TodosDTO>
   */
  public List<TodosDTO> selectTodosByMemberId(int memberId);

  /**
   * Todo를 추가하기 위한 쿼리
   * 
   * @param TodosDTO todoInfo
   * @return number (영향받은 행 수)
   */
  public int insertTodo(TodosDTO todoInfo);

  /**
   * Todo를 제거하기 위한 쿼리
   * 
   * @param int todoId
   * @return number (영향받은 행 수)
   */
  public int deleteByTodoId(int todoId);
}
