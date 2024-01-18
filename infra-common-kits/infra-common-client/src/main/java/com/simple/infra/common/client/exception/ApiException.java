package com.simple.infra.common.client.exception;

import com.simple.infra.common.client.result.ResultCode;

/**
 * @author xulujun
 * @date 2018/07/08
 */
public class ApiException extends CustomRuntimeException {

  private static final long serialVersionUID = -2264692872819633568L;

  public ApiException(String messageFormat, Object... args) {
    super(messageFormat, args);
  }

  public ApiException(Throwable cause, String messageFormat, Object... args) {
    super(cause, messageFormat, args);
  }

  public ApiException(ResultCode resultCode,
      String messageFormat, Object... args) {
    super(resultCode, messageFormat, args);
  }

  public ApiException(Throwable cause, ResultCode resultCode,
      String messageFormat, Object... args) {
    super(cause, resultCode, messageFormat, args);
  }
}
