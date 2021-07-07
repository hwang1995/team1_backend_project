package com.team1.healthcare.config;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import lombok.extern.slf4j.Slf4j;

@EnableTransactionManagement
@Configuration
@Slf4j
public class AppConfig {
  @Autowired
  private DataSource dataSource;

  @Bean
  public PlatformTransactionManager transactionManager() throws URISyntaxException,
      GeneralSecurityException, ParseException, IOException, SQLException {

    return new DataSourceTransactionManager(dataSource);
  }
}
