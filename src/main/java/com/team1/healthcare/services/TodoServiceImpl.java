package com.team1.healthcare.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.team1.healthcare.dto.TodosDTO;
import com.team1.healthcare.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TodoServiceImpl implements ITodoService {
  @Override
  public boolean addTodo(TodosDTO todoInfo) {
    
    if(todoInfo == null) {
      throw new BadRequestException("Todo 정보가 올바르지 않습니다.", new Throwable("no_todo_information"));
    }
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean removeTodo(int todoId) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public List<TodosDTO> showTodosListByHospitalCode(String hospitalCode) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<TodosDTO> showTodosListByMemberId(int memberId) {
    // TODO Auto-generated method stub
    return null;
  }

}
