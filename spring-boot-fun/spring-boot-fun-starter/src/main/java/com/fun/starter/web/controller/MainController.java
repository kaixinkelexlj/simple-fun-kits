package com.fun.starter.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

  @Autowired
  private Environment environment;

  @GetMapping({"/", "/index.htm"})
  public String root() {
    return "index";
  }

  /**
   * 健康检查
   */
  @GetMapping("/checkpreload.htm")
  public @ResponseBody
  String checkPreload() {
    return "success";
  }

  @GetMapping({"/cur-profile.json", "/cur-profile.data"})
  @ResponseBody
  public String[] profile() {
    if (environment == null) {
      return new String[]{"<not set>"};
    }
    return environment.getActiveProfiles();
  }

}
