package com.team1.healthcare.api.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
  // 1. GET 로그인 하기

  @GetMapping("")
  public String getAuth() {
    return "hello";
  }

}
