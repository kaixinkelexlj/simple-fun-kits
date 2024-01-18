package com.simple.infra.common.client.exception;


import com.simple.infra.common.client.result.ResultCode;

/**
 * @author xulujun
 * @date 2018/07/13
 */
public class ConvertException extends CustomRuntimeException {

  private static final long serialVersionUID = -7362471577961317725L;

  public ConvertException() {
  }

  public ConvertException(String messageFormat, Object... args) {
    super(messageFormat, args);
  }

  public ConvertException(Throwable cause, String messageFormat, Object... args) {
    super(cause, messageFormat, args);
  }

  public ConvertException(ResultCode resultCode,
      String messageFormat, Object... args) {
    super(resultCode, messageFormat, args);
  }

  public ConvertException(Throwable cause, ResultCode resultCode,
      String messageFormat, Object... args) {
    super(cause, resultCode, messageFormat, args);
  }
}
