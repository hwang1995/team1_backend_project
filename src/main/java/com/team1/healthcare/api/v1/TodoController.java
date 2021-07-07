package com.team1.healthcare.api.v1;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.team1.healthcare.dto.TodosDTO;
import com.team1.healthcare.services.TodoServiceImpl;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/todo")
public class TodoController {
  @Autowired
  private TodoServiceImpl todoService;

  // 1. GET Todos By hospitalCode 가져오기
  @GetMapping("/hospitalCode")
  public List<TodosDTO> getTodosListByHospitalCode(String hospitalCode) {
    List<TodosDTO> list = todoService.showTodosListByHospitalCode(hospitalCode);
    return list;
  }

  // 1.5. GET Todos By memberId 가져오기
  @GetMapping("/memberId")
  public List<TodosDTO> getTodosListByMemberId(int memberId) {
    List<TodosDTO> list = todoService.showTodosListByMemberId(memberId);
    return list;
  }

  @PostMapping("")
  public boolean createTodo(@RequestBody TodosDTO todoInfo) {
    boolean result = todoService.addTodo(todoInfo);
    // log.info(todoInfo.toString());
    return result;
  }

  // 2. DELETE TODO 삭제하기

  @DeleteMapping("")
  public boolean removeTodo(@RequestParam int todoId) {
    boolean result = todoService.removeTodo(todoId);
    return result;
  }


}
