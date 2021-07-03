package com.team1.healthcare.api.v1;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.team1.healthcare.dto.HospitalsDTO;
import com.team1.healthcare.dto.MembersDTO;
import com.team1.healthcare.security.JwtUtil;
import com.team1.healthcare.services.AuthServiceImpl;
import com.team1.healthcare.services.MemberServiceImpl;
import com.team1.healthcare.vo.auth.LoginVO;
import com.team1.healthcare.vo.auth.UserInfoVO;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {
  // 1. GET 로그인 하기
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private MemberServiceImpl memberService;

  @Autowired
  private AuthServiceImpl authService;


  @GetMapping("")
  public UserInfoVO getAuth(@RequestBody LoginVO loginInfo, HttpServletResponse response) {
    // UserInfoVO userInfo = authService.processAuthentication(loginInfo);
    HospitalsDTO hospitalInfo = authService.isExistedHospital(loginInfo);
    String memberEmail = loginInfo.getMemberEmail();
    String memberPw = loginInfo.getMemberPw();

    if (hospitalInfo == null) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return null;
    }
    try {
      UsernamePasswordAuthenticationToken upat =
          new UsernamePasswordAuthenticationToken(memberEmail, memberPw);
      Authentication authentication = authenticationManager.authenticate(upat);

      SecurityContextHolder.getContext().setAuthentication(authentication);

      String authToken = JwtUtil.createToken(memberEmail);
      MembersDTO memberInfo = authService.getLoginMemberInfo(loginInfo);
      log.info(memberInfo.toString());
      UserInfoVO userInfo = new UserInfoVO(memberInfo, authToken);

      return userInfo;

    } catch (Exception e) {
      e.printStackTrace();
    }

    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    return null;


  }

  @PostMapping("")
  public String addMembers(@RequestBody MembersDTO memberInfo) {
    boolean result = memberService.addMembers(memberInfo);

    if (result) {
      return "success";
    }
    return "failure";
  }


}
