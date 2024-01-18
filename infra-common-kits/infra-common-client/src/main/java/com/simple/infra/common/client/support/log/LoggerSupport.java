package com.simple.infra.common.client.support.log;

import org.slf4j.Logger;

/**
 * @author xulujun
 * @date 2018/07/17
 */
public interface LoggerSupport {

  default void info(Logger logger, String messageFormat, Object... args) {
    Logs.info(logger, messageFormat, args);
  }

  default void warn(Logger logger, String messageFormat, Object... args) {
    Logs.warn(logger, messageFormat, args);
  }

  default void error(Logger logger, String messageFormat, Object... args) {
    Logs.error(logger, messageFormat, args);
  }

  default void error(Throwable e, Logger logger, String messageFormat,
      Object... args) {
    Logs.error(e, logger, messageFormat, args);
  }

}
