package com.team1.healthcare;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

  @GetMapping("/image")
  public void getImage(HttpServletResponse response, String path) {
    try {
      String home = System.getProperty("user.home");
      String specifyDest = home + "/images" + path;
      response.setContentType("image/jpeg");

      InputStream is = new FileInputStream(specifyDest);
      OutputStream os = response.getOutputStream();
      FileCopyUtils.copy(is, os);
      os.flush();
      is.close();
      os.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
