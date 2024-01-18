package com.simple.infra.common.client.exception;

import com.simple.infra.common.client.result.ResultCode;

/**
 * @author xulujun
 * @date 2018/07/19
 */
public class HttpException extends CustomRuntimeException {

  private static final long serialVersionUID = -7249965337046676722L;

  public HttpException() {
  }

  public HttpException(String url, String response) {
    this("url[{}], response[{}]", url, response);
  }

  public HttpException(Throwable cause, String url, String response) {
    this(cause, "error[{}], url[{}], response[{}]", cause.getMessage(), url, response);
  }

  public HttpException(String messageFormat, Object... args) {
    super(messageFormat, args);
  }

  public HttpException(Throwable cause, String messageFormat, Object... args) {
    super(cause, messageFormat, args);
  }

  public HttpException(ResultCode resultCode,
      String messageFormat, Object... args) {
    super(resultCode, messageFormat, args);
  }

  public HttpException(Throwable cause, ResultCode resultCode,
      String messageFormat, Object... args) {
    super(cause, resultCode, messageFormat, args);
  }
}
