package com.simple.infra.common.client.support.template;

import com.simple.infra.common.client.exception.ArgsCheckException;
import com.simple.infra.common.client.exception.ServiceException;
import com.simple.infra.common.client.request.BaseRequest;
import com.simple.infra.common.client.result.BaseResult;

/**
 * @author xulujun
 * @date 2018/07/13
 */
public interface BizExecutor<T extends BaseRequest, R extends BaseResult> extends TemplateSupport {


  /**
   * 执行参数校验，request参数准备等
   */
  void beforeExecute(T request, R result)
      throws ArgsCheckException, ServiceException;

  /**
   * 执行业务逻辑
   */
  void execute(T request, R result)
      throws Exception;

}
