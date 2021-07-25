package com.team1.healthcare.config;

import javax.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import com.team1.healthcare.vo.common.PushMessageVO;
import lombok.extern.slf4j.Slf4j;

@Component
@PropertySource("classpath:/credentials.properties")
@Slf4j
public class MqttTemplate {

  private static final String MQTT_CLIENT_ID = MqttAsyncClient.generateClientId();

  @Value("${mqtt.hostname}")
  private String hostname;

  @Value("${mqtt.port}")
  private int port;

  @Value("${mqtt.username}")
  private String username;

  @Value("${mqtt.password}")
  private String password;

  private MqttClient mqttClient;

  @PostConstruct
  void init() throws MqttException {
    try {
      final String BROKER_URL = "tcp://" + hostname + ":" + port;
      mqttClient = new MqttClient(BROKER_URL, MQTT_CLIENT_ID, null);
      MqttConnectOptions options = new MqttConnectOptions();
      // options.setUserName(username);
      // options.setPassword(password.toCharArray());
      options.setAutomaticReconnect(true);
      mqttClient.connect(options);
    } catch (MqttException e) {
      e.printStackTrace();
    }


  }

  public void sendMessage(PushMessageVO pushMessage) {
    try {
      MqttMessage mqttMessage = new MqttMessage();
      String topic = pushMessage.getTopic();

      JSONObject jsonObj = new JSONObject(pushMessage);
      String json = jsonObj.toString();
      mqttMessage.setPayload(json.getBytes());
      mqttMessage.setQos(0);
      log.info(pushMessage.toString());
      mqttClient.publish(topic, mqttMessage);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
