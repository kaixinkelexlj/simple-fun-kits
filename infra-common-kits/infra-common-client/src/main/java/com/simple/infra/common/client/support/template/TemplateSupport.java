package com.simple.infra.common.client.support.template;


import org.apache.commons.lang3.StringUtils;

import com.simple.infra.common.client.exception.ArgsCheckException;
import com.simple.infra.common.client.request.PaginationRequest;
import com.simple.infra.common.client.result.BaseResult;
import com.simple.infra.common.client.result.ResultCode;
import com.simple.infra.common.client.result.ResultCodes;
import com.simple.infra.common.client.result.Results;
import com.simple.infra.common.client.support.log.LoggerSupport;

/**
 * @author xulujun
 * @date 2018/07/09
 */
public interface TemplateSupport extends LoggerSupport {

  default void checkArgs(boolean expression, String messageFmt, Object... args)
      throws ArgsCheckException {
    if (!expression) {
      throw new ArgsCheckException(ResultCodes.ILLEGAL_PARAM, messageFmt, args);
    }
  }

  default void checkArgs(boolean expression, ResultCode resultCode, Object... args)
          throws ArgsCheckException {
    if (!expression) {
      throw ArgsCheckException.byResultCode(resultCode, resultCode.getMessage(), args);
    }
  }

  default void checkNotNull(Object object, String messageFmt, Object... args) {
    checkArgs(object != null, messageFmt, args);
  }

  default void checkNotBlank(String value, String messageFmt, Object... args) {
    checkArgs(StringUtils.isNotBlank(value), messageFmt, args);
  }


  default void checkArgsWithErrorMessage(BaseResult result, boolean expression,
      String messageFmt, Object... args) {
    if (!expression) {
      Results.failWithDisplayMessage(result, ResultCodes.ILLEGAL_PARAM, messageFmt, args);
      throw new ArgsCheckException(ResultCodes.ILLEGAL_PARAM, messageFmt, args);
    }
  }

  default void checkPaginationArgs(PaginationRequest request) throws ArgsCheckException {
    checkArgs(request.getPageNo() > 0, "invalid pageNo, pageNo >= 1");
    checkArgs(request.getPageSize() > 0, "invalid pageSize, pageSize > 0 ");
  }

}
