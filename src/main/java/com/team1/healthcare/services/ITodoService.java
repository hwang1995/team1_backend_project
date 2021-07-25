package com.team1.healthcare.services;

import java.util.List;
import com.team1.healthcare.dto.TodosDTO;

public interface ITodoService {
  /**
   * Todo를 추가하기 위한 서비스 인터페이스
   * 
   * @param TodosDTO todoInfo
   * @return boolean (Todo가 추가 되었는 지 여부를 알기 위해)
   */
  public boolean addTodo(TodosDTO todoInfo);

  /**
   * Todo를 제거하기 위한 서비스 인터페이스
   * 
   * @param int todoId
   * @return boolean (Todo가 제거 되었는지 여부를 알기 위해)
   */
  public boolean removeTodo(int todoId);

  /**
   * Todo의 checked의 값을 변경하기 위한 서비스 인터페이스
   * 
   * @param int todoId
   * @return true or false (Todo가 수정 되었는지 여부를 알기 위해)
   */

  public boolean updateTodoCheckedIn(int todoId);

  /**
   * Todo의 checked의 값을 변경하기 위한 서비스 인터페이스
   * 
   * @param int todoId
   * @return true or false (Todo가 수정 되었는지 여부를 알기 위해)
   */

  public boolean updateTodoCheckedOut(int todoId);


  /**
   * 병원 코드 (식별자)로 Todo를 조회하기 위한 서비스 인터페이스
   * 
   * @param String hospitalCode
   * @return List<TodosDTO>
   */
  public List<TodosDTO> showTodosListByHospitalCode(String hospitalCode);

  /**
   * 임직원 ID (식별자)로 Todo를 조회하기 위한 서비스 인터페이스
   * 
   * @param int memberId
   * @return List<TodosDTO>
   */
  public List<TodosDTO> showTodosListByMemberId(int memberId);
}
