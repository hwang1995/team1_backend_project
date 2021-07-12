package com.team1.healthcare.api.v1;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.team1.healthcare.config.MqttTemplate;
import com.team1.healthcare.services.MqttServiceImpl;
import com.team1.healthcare.vo.common.PushMessageVO;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/mqtt")
public class MqttController {

  @Autowired
  private MqttTemplate mqttTemplate;

  @Autowired
  private MqttServiceImpl mqttService;

  @PostMapping("")
  public boolean sentMqttMessage(@RequestBody PushMessageVO pushMessage) {

    try {
      mqttTemplate.sendMessage(pushMessage);
      mqttService.saveMqttMessage(pushMessage);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  @GetMapping("/notification")
  public List<PushMessageVO> getNotificationsByTopic(String topic) {
    List<PushMessageVO> result = mqttService.getNotificationsByTopic(topic);
    return result;
  }

}
