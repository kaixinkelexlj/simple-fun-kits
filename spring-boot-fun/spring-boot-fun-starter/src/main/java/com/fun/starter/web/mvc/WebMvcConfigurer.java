package com.fun.starter.web.mvc;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fun.starter.web.interceptor.MainInterceptor;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author xulujun
 * @date 2018/06/27
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new MainInterceptor()).addPathPatterns("/**");
  }

  @Override
  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(new FastJsonHttpMessageConverter());
    super.extendMessageConverters(converters);
  }

  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    /*configurer.mediaType("data", MediaType.APPLICATION_JSON);
    configurer.mediaType("json", MediaType.APPLICATION_JSON);
    configurer.defaultContentType(MediaType.APPLICATION_JSON);*/
  }
}
