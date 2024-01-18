package com.simple.infra.common.client.exception;


import com.simple.infra.common.client.result.ResultCode;

/**
 * @author xulujun
 * @date 2018/07/08
 */
public class BizException extends CustomRuntimeException {

  private static final long serialVersionUID = -2264692872819633568L;

  public BizException() {
  }

  public BizException(String messageFormat, Object... args) {
    super(messageFormat, args);
  }

  public BizException(Throwable cause, String messageFormat, Object... args) {
    super(cause, messageFormat, args);
  }

  public BizException(ResultCode resultCode,
      String messageFormat, Object... args) {
    super(resultCode, messageFormat, args);
  }

  public BizException(Throwable cause, ResultCode resultCode,
      String messageFormat, Object... args) {
    super(cause, resultCode, messageFormat, args);
  }

  public static BizException byResultCode(ResultCode resultCode, Object... args) {
    return new BizException(resultCode, resultCode.getMessage(), args);
  }

  public static BizException byResultCode(Throwable cause, ResultCode resultCode, Object... args) {
    return new BizException(cause, resultCode, resultCode.getMessage(), args);
  }
}
