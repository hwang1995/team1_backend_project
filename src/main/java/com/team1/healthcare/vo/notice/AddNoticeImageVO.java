package com.team1.healthcare.vo.notice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddNoticeImageVO {
  private String hospitalCode;
  private String imageName; // 'uuid.jpg / jpeg / png/ ...'
  private String base64Content;

  public boolean isNull() {
    if (hospitalCode == null || imageName == null || base64Content == null) {
      return true;
    } else {
      return false;
    }
  }

}
