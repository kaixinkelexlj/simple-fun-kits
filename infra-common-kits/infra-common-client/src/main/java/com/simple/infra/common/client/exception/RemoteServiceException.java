package com.simple.infra.common.client.exception;

import com.simple.infra.common.client.result.ResultCode;

/**
 * @author xulujun
 * @date 2018/07/08
 */
public class RemoteServiceException extends CustomRuntimeException {

  private static final long serialVersionUID = 468790656163041610L;


  public RemoteServiceException() {
  }

  public RemoteServiceException(String messageFormat, Object... args) {
    super(messageFormat, args);
  }

  public RemoteServiceException(Throwable cause, String messageFormat, Object... args) {
    super(cause, messageFormat, args);
  }

  public RemoteServiceException(ResultCode resultCode,
      String messageFormat, Object... args) {
    super(resultCode, messageFormat, args);
  }

  public RemoteServiceException(Throwable cause, ResultCode resultCode,
      String messageFormat, Object... args) {
    super(cause, resultCode, messageFormat, args);
  }
}
