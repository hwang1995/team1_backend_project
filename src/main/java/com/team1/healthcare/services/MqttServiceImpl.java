package com.team1.healthcare.services;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team1.healthcare.config.RedisConfig;
import com.team1.healthcare.exception.BadRequestException;
import com.team1.healthcare.exception.NotFoundException;
import com.team1.healthcare.vo.common.PushMessageVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MqttServiceImpl implements IMqttService {

  @Override
  public boolean saveMqttMessage(PushMessageVO pushMessage) {
    ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);

    @SuppressWarnings("unchecked")
    RedisTemplate<String, String> redisTemplate =
        (RedisTemplate<String, String>) ctx.getBean("redisTemplate");
    String sendTopic = pushMessage.getTopic();
    log.trace(sendTopic);
    JSONObject jsonObj = new JSONObject(pushMessage);
    String serializeObj = jsonObj.toString();

    final ListOperations<String, String> stringListOps = redisTemplate.opsForList();
    stringListOps.leftPush(sendTopic, serializeObj);

    ctx.close();


    return true;
  }

  @SuppressWarnings("resource")
  @Override
  public List<PushMessageVO> getNotificationsByTopic(String topic) {
    ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);

    @SuppressWarnings("unchecked")
    RedisTemplate<String, String> redisTemplate =
        (RedisTemplate<String, String>) ctx.getBean("redisTemplate");
    final ListOperations<String, String> stringListOps = redisTemplate.opsForList();
    ObjectMapper objectMapper = new ObjectMapper();

    if (topic == null || topic.trim().isEmpty()) {
      throw new BadRequestException("토픽이 존재하지 않거나 공백입니다.", new Throwable("no_topics"));
    }

    Long listSize = stringListOps.size(topic);

    if (listSize == 0) {
      throw new NotFoundException("수신 받은 메시지가 존재하지 않습니다.", new Throwable("no_message"));
    }

    List<String> data = stringListOps.range(topic, 0, listSize);
    List<PushMessageVO> result = new ArrayList<PushMessageVO>();

    data.forEach(str -> {
      PushMessageVO pushVO;
      try {
        pushVO = objectMapper.readValue(str, PushMessageVO.class);
        log.info(pushVO.toString());
        result.add(pushVO);
      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }

    });

    ctx.close();
    return result;
  }

}
