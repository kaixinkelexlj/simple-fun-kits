package com.simple.infra.common.client.result;

/**
 * @author xulujun
 * @date 2018/07/10
 */
public interface ApiResult {

  /**
   * 调用是否成功
   */
  boolean isSuccess();

  void setTraceId(String traceId);

  String getTraceId();
}
