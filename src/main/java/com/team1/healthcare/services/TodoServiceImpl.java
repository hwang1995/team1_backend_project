package com.team1.healthcare.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.team1.healthcare.dao.TodosDAO;
import com.team1.healthcare.dto.TodosDTO;
import com.team1.healthcare.exception.BadRequestException;
import com.team1.healthcare.exception.ConflictRequestException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TodoServiceImpl implements ITodoService {

  @Autowired
  private TodosDAO todosDAO;

  /**
   * Todo를 추가하기 위한 서비스 메서드 시나리오 : 1-1. 요청받은 todoInfo.isNull이 true인 경우 BadRequestException을 처리해준다.
   * 1-2. 비즈니스 로직 중 불가능하거나 모순이 생긴 경우 ConflictRequestException을 처리해준다. 2-1. 올바른 요청이 들어왔을 경우,
   * todosDAO.insertTodo의 값이 1이 아닐 경우 result = false를 리턴해준다. 2-2. 올바른 요청이 들어왔을 경우,
   * todosDAO.insertTodo의 값이 1일 경우 result = true를 리턴해준다.
   * 
   * @param : TodosDTO todoInfo
   * @return :
   * @협력 객체 : TodosDAO
   */
  @Override
  public boolean addTodo(TodosDTO todoInfo) {
    if (todoInfo.isNull()) {
      throw new BadRequestException("잘못된 todoInfo값을 받았습니다.", new Throwable("Request-Error"));
    }

    int row = todosDAO.insertTodo(todoInfo);

    if (row != 1) {
      throw new ConflictRequestException("ConflictRequest가 발생했습니다.",
          new Throwable("ConflictRequest"));
    }

    return true;
  }

  /**
   * Todo를 제거하기 위한 서비스 메서드 시나리오 : 1-1. 요청받은 int todoId이 0 값인 경우 BadRequestException을 처리해준다. 1-3.
   * 비즈니스 로직 중 불가능하거나 모순이 생긴 경우 ConflictRequestException을 처리해준다. 2-1. 올바른 요청이 들어왔지만 ,
   * todosDAO.deleteByTodoId의 값이 1이 아닐 경우 result = false를 리턴해주고 NotFoundException을 처리해준다. 2-2. 올바른
   * 요청이 들어왔을 경우, todosDAO.deleteByTodoId의 값이 1일 경우 result = true를 리턴해준다.
   * 
   * @param : int todoId
   * @return : number (영향받은 행 수)
   * @협력 객체 : TodosDAO
   */
  @Override
  public boolean removeTodo(int todoId) {
    // TODO Auto-generated method stub
    int row = todosDAO.deleteByTodoId(todoId);
    if (row != 1) {
      if (todoId == 0) {
        throw new BadRequestException("잘못된 todoId값을 받았습니다.", new Throwable("Request-Error"));
      } else if (row != 1) {
        throw new ConflictRequestException("ConflictRequest가 발생했습니다.",
            new Throwable("Conflict-Request"));
      }
    }
    return true;
  }

  // 비즈니스 로직 중 불가능하거나 모순이 생긴 경우 ConflictRequestException을 처리해준다.
  /**
   * 병원코드 (식별자)로 Todo 리스트를 조회하기 위한 서비스 메서드 시나리오 : 1-1. 요청받은 String hospitalCode이 null 값인 경우
   * BadRequestException을 처리해준다. 1-3. 비즈니스 로직 중 불가능하거나 모순이 생긴 경우 ConflictRequestException을 처리해준다.
   * 2-1. 올바른 요청이 들어왔지만 , todosDAO.selectTodosByHospitalCode의 값이 없어서 불러올 수 없는 경우 NoContentException을
   * 처리해준다. 2-2. 올바른 요청이 들어왔고, todosDAO.selectTodosByHospitalCode의 값이 있지만 불러올 수 없는 경우
   * NotFoundException을 처리해준다. 2-2. 올바른 요청이 들어왔고, todosDAO.selectTodosByHospitalCode의 값이 제대로인 경우
   * todos를 리턴해준다.
   * 
   * @param : String hospitalCode
   * @return : List<TodosDTO>
   * @협력 객체 : TodosDAO
   */
  @Override
  public List<TodosDTO> showTodosListByHospitalCode(String hospitalCode) {
    // TODO Auto-generated method stub
    if (hospitalCode == null) {
      throw new BadRequestException("잘못된 hospitalCode 값을 받았습니다.", new Throwable("Request-Error"));
    }
    List<TodosDTO> todos = todosDAO.selectTodosByHospitalCode(hospitalCode);
    if (todos == null || todos.size() == 0) {
      throw new ConflictRequestException("ConflictRequest가 발생했습니다.",
          new Throwable("Conflict-Request"));
    }
    return todos;
  }

  /**
   * 임직원 ID (식별자)로 Todo 리스트를 조회하기 위한 서비스 메서드 시나리오 : 1-1. 요청받은 int memberId이 null 값인 경우
   * BadRequestException을 처리해준다. 1-3. 비즈니스 로직 중 불가능하거나 모순이 생긴 경우 ConflictRequestException을 처리해준다.
   * 2-1. 올바른 요청이 들어왔지만 , todosDAO.selectTodosByMemberId의 값이 없어서 불러올 수 없는 경우 NoContentException를
   * 처리해준다. 2-1. 올바른 요청이 들어왔지만 , todosDAO.selectTodosByMemberId의 값이 있지만 불러올 수 없는 경우
   * NotFoundException를 처리해준다. 2-3. 올바른 요청이 들어왔고, todosDAO.selectTodosByMemberId의 값이 제대로인 경우 todos를
   * 리턴해준다.
   * 
   * @param : int memberId
   * @return : List<TodosDTO>
   * @협력 객체 : TodosDAO
   */
  @Override
  public List<TodosDTO> showTodosListByMemberId(int memberId) {
    // TODO Auto-generated method stub
    if (memberId == 0) {
      throw new BadRequestException("잘못된 memberId 값을 받았습니다.", new Throwable("Request-Error"));
    }
    List<TodosDTO> todos = todosDAO.selectTodosByMemberId(memberId);

    return todos;
  }

  @Override
  public boolean updateTodoCheckedIn(int todoId) {
    if (todoId == 0) {
      throw new BadRequestException("잘못된 noticeId값을 받았습니다.", new Throwable("Request-Error"));
    }
    int row = todosDAO.updateCheckedIn(todoId);
    if (row != 1) {
      throw new ConflictRequestException("ConflictRequest가 발생했습니다.",
          new Throwable("ConflictRequest"));
    }
    return true;
  }

  @Override
  public boolean updateTodoCheckedOut(int todoId) {
    if (todoId == 0) {
      throw new BadRequestException("잘못된 noticeId값을 받았습니다.", new Throwable("Request-Error"));
    }
    int row = todosDAO.updateCheckedOut(todoId);
    if (row != 1) {
      throw new ConflictRequestException("ConflictRequest가 발생했습니다.",
          new Throwable("ConflictRequest"));
    }
    return true;
  }

}
