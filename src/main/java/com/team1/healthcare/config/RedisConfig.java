package com.team1.healthcare.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Configuration
@Component
@PropertySource("classpath:/credentials.properties")
@EnableRedisRepositories
public class RedisConfig {

  @Value("${redis.hostname}")
  private String redisHost;

  @Value("${redis.port}")
  private int redisPort;

  @Value("${redis.password}")
  private String redisPassword;

  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
    RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
    configuration.setHostName(redisHost);
    configuration.setPort(redisPort);
    configuration.setPassword(redisPassword.toCharArray());

    return new LettuceConnectionFactory(configuration);
  }

  @Bean
  public RedisTemplate<byte[], byte[]> redisTemplate() {
    RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory());
    redisTemplate.setHashValueSerializer(new StringRedisSerializer());
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new StringRedisSerializer());
    redisTemplate.setHashKeySerializer(new StringRedisSerializer());
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }

}
