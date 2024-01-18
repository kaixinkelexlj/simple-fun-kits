package com.simple.infra.common.client.exception;

import com.simple.infra.common.client.lang.Assert;
import com.simple.infra.common.client.result.ResultCode;
import com.simple.infra.common.client.utils.CommonUtils;

/**
 * @author xulujun
 * @date 2018/07/07
 */
@SuppressWarnings("AlibabaAbstractClassShouldStartWithAbstractNaming")
public abstract class CustomRuntimeException extends RuntimeException {

  private static final long serialVersionUID = 897353941033169096L;

  protected boolean fillStackTrace = false;
  protected String errorCode;

  public CustomRuntimeException() {
  }

  /**
   * @see CommonUtils#formatString(String, Object...)
   */
  public CustomRuntimeException(String messageFormat, Object... args) {
    super(CommonUtils.formatString(messageFormat, args));
    this.fillStackTrace = false;
  }

  public CustomRuntimeException(Throwable cause, String messageFormat, Object... args) {
    super(CommonUtils.formatString(messageFormat, args), cause);
    this.fillStackTrace = true;
  }

  /**
   * @see CommonUtils#formatString(String, Object...)
   */
  public CustomRuntimeException(ResultCode resultCode, String messageFormat, Object... args) {
    super(resultCode.getCode() + "::" + CommonUtils.formatString(messageFormat, args));
    Assert.notNull(resultCode);
    this.fillStackTrace = true;
    this.errorCode = resultCode.getCode();
  }

  public CustomRuntimeException(Throwable cause, ResultCode resultCode, String messageFormat,
      Object... args) {
    super(resultCode.getCode() + "::" + CommonUtils.formatString(messageFormat, args), cause);
    Assert.notNull(resultCode);
    this.fillStackTrace = true;
    this.errorCode = resultCode.getCode();
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public boolean isFillStackTrace() {
    return fillStackTrace;
  }

  @Override
  public synchronized Throwable fillInStackTrace() {
    if (!fillStackTrace) {
      return this;
    }
    return super.fillInStackTrace();
  }
}
