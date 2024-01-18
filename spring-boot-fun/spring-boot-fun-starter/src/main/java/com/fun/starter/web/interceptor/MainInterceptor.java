package com.fun.starter.web.interceptor;

import com.alibaba.fastjson.JSON;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author xulujun
 * @date 2018/07/11
 */
public class MainInterceptor implements HandlerInterceptor {

  private static final Logger LOGGER = LoggerFactory.getLogger(MainInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object o) throws Exception {
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)
      throws Exception {
    LOGGER.info(JSON.toJSONString(modelAndView));
  }

  @Override
  public void afterCompletion(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

  }
}
