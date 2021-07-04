package com.team1.healthcare.config;

import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MqttConfig {

  private static final String BROKER_URL = "tcp://localhost:1883";
  private static final String MQTT_CLIENT_ID = MqttAsyncClient.generateClientId();

  private MqttClient mqttClient;

  public MqttConfig() {
    try {
      mqttClient = new MqttClient(BROKER_URL, MQTT_CLIENT_ID, null);
      MqttConnectOptions options = new MqttConnectOptions();
      options.setAutomaticReconnect(true);
      mqttClient.connect(options);
    } catch (MqttException e) {
      e.printStackTrace();
    }
  }

  public void sendMessage(String topic, String priority, String message) {
    try {
      MqttMessage mqttMessage = new MqttMessage();

      JSONObject jsonObj = new JSONObject();
      jsonObj.put("topic", topic);
      jsonObj.put("priority", priority);
      jsonObj.put("message", message);

      String json = jsonObj.toString();
      mqttMessage.setPayload(json.getBytes());
      mqttMessage.setQos(0);

      mqttClient.publish(topic, mqttMessage);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
