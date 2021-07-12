package com.team1.healthcare.services;

import java.util.List;
import com.team1.healthcare.vo.common.PushMessageVO;

public interface IMqttService {
  public boolean saveMqttMessage(PushMessageVO pushMessage);

  public List<PushMessageVO> getNotificationsByTopic(String topic);
}
