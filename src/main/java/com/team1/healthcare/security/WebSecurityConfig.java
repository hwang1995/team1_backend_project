package com.team1.healthcare.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import lombok.extern.log4j.Log4j;

// @Configuration
// @EnableGlobalAuthentication
// @EnableWebSecurity
@Log4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private DataSource dataSource;

  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    log.info("HTTP BASIC");
    http.httpBasic().disable();


    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    log.info("SESSION");
    http.csrf().disable();
    log.info("CSRF");
    http.cors();
    log.info("CORS");
    http.addFilterBefore(new JwtAuthenticationFilter(userDetailsService),
        UsernamePasswordAuthenticationFilter.class);
    log.info("FILTER");

    http.authorizeRequests().expressionHandler(securityExpressionHandler()).anyRequest().denyAll();

    // .antMatchers("/**").permitAll();
  }

  // DataSource 설정
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(
        "select member_email as username, member_pw as password, member_enabled as enabled from members where member_email=?")
        .authoritiesByUsernameQuery(
            "select member_email as username, member_authority as authority from members where member_email=?")
        .passwordEncoder(new BCryptPasswordEncoder());
  }

  // 사용자의 상세 정보를 가져오는 서비스 객체를 관리 객체로 등록 > JwtAuthenticationFilter 사용
  @Bean
  @Override
  public UserDetailsService userDetailsServiceBean() throws Exception {
    return super.userDetailsServiceBean();
  }

  // 인증된 정보를 관리하는 객체를 Spring 관리 객체로 등록 -> AuthController에 사용
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  // 권한 계층 설정 객체 생성
  public RoleHierarchyImpl roleHierarchyImpl() {
    RoleHierarchyImpl roleHierarchyImpl = new RoleHierarchyImpl();
    roleHierarchyImpl.setHierarchy("ROLE_DEVELOP > ROLE_DIRECTOR");
    return roleHierarchyImpl;
  }

  public SecurityExpressionHandler<FilterInvocation> securityExpressionHandler() {
    DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler =
        new DefaultWebSecurityExpressionHandler();
    defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchyImpl());
    log.info(defaultWebSecurityExpressionHandler.toString());
    return defaultWebSecurityExpressionHandler;
  }


  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();

    configuration.addAllowedOrigin("*");
    configuration.addAllowedMethod("*");
    configuration.addAllowedHeader("*");
    configuration.setAllowCredentials(false);

    UrlBasedCorsConfigurationSource ccs = new UrlBasedCorsConfigurationSource();
    ccs.registerCorsConfiguration("/**", configuration);

    return ccs;
  }



}
