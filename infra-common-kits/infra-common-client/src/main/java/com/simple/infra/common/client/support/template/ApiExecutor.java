package com.simple.infra.common.client.support.template;


import com.simple.infra.common.client.exception.ApiException;
import com.simple.infra.common.client.request.BaseRequest;
import com.simple.infra.common.client.result.BaseResult;

/**
 * @author xulujun
 * @date 2018/07/13
 */
public interface ApiExecutor<T extends BaseRequest, R extends BaseResult> {

  /**
   * 执行实际调用
   */
  R doExecute(T apiRequest)
      throws ApiException;
}
