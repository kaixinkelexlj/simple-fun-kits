package com.simple.infra.common.client.exception;


import com.simple.infra.common.client.result.ResultCode;

/**
 * @author xulujun
 * @date 2018/07/08
 */
public class ArgsCheckException extends CustomRuntimeException {

  private static final long serialVersionUID = 2452634455494130035L;

  public ArgsCheckException() {
  }

  public ArgsCheckException(String messageFormat, Object... args) {
    super(messageFormat, args);
  }

  public ArgsCheckException(Throwable cause, String messageFormat, Object... args) {
    super(cause, messageFormat, args);
  }

  public ArgsCheckException(ResultCode resultCode,
      String messageFormat, Object... args) {
    super(resultCode, messageFormat, args);
  }

  public ArgsCheckException(Throwable cause, ResultCode resultCode,
      String messageFormat, Object... args) {
    super(cause, resultCode, messageFormat, args);
  }

  public static ArgsCheckException byResultCode(ResultCode resultCode, Object... args) {
    return new ArgsCheckException(resultCode, resultCode.getMessage(), args);
  }

  public static ArgsCheckException byResultCode(Throwable throwable, ResultCode resultCode, Object... args) {
    return new ArgsCheckException(throwable, resultCode, resultCode.getMessage(), args);
  }
}
