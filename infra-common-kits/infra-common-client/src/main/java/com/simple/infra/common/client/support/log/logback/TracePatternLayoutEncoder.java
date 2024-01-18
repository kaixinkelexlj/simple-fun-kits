package com.simple.infra.common.client.support.log.logback;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;

/**
 * @author xulujun
 * @date 2018/08/09
 */
public class TracePatternLayoutEncoder extends PatternLayoutEncoder {

  static {
    PatternLayout.defaultConverterMap.putIfAbsent("trace", TraceConverter.class.getName());
  }

  @Override
  public void start() {
    super.start();
  }

}
