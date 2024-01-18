package com.simple.infra.common.client.support.template;

import org.slf4j.Logger;

import com.simple.infra.common.client.exception.ApiException;
import com.simple.infra.common.client.request.BaseRequest;
import com.simple.infra.common.client.result.BaseResult;
import com.simple.infra.common.client.result.ResultCodes;
import com.simple.infra.common.client.result.Results;
import com.simple.infra.common.client.support.log.Logs;

/**
 * @author xulujun
 * @date 2018/07/08
 */
public class ApiService<T extends BaseRequest, R extends BaseResult> extends
        AbstractTemplateService<T, R> {

    public ApiService(T request, R result, Logger logger) {
        super(request, result, logger);
    }

    public R execute(ApiExecutor<T, R> apiExecutor) {
        try {
            result = apiExecutor.doExecute(request);
        } catch (ApiException ex) {
            Results.fail(result, ex.getErrorCode(), ex.getMessage());
            Logs.error(ex, logger, "ApiException");
            //logger.error(CommonUtils.formatString("{}-ApiException", calServiceId(ApiService.class,
            // ex)), ex);
        } catch (Exception ex) {
            Results.fail(result, ResultCodes.EXCEPTION.getCode(), ex.getMessage());
            Logs.error(ex, logger, "UnexpectedException");
      /*logger.error(
          CommonUtils.formatString("{}-UnexpectedException", calServiceId(ApiService.class, ex)),
          ex);*/
        }
        return result;
    }

}
