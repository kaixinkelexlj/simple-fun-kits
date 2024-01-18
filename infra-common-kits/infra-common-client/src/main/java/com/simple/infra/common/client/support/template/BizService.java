package com.simple.infra.common.client.support.template;

import java.sql.SQLException;

import org.slf4j.Logger;

import com.simple.infra.common.client.exception.ArgsCheckException;
import com.simple.infra.common.client.exception.ServiceException;
import com.simple.infra.common.client.request.BaseRequest;
import com.simple.infra.common.client.result.BaseResult;
import com.simple.infra.common.client.result.ResultCodes;
import com.simple.infra.common.client.result.Results;
import com.simple.infra.common.client.support.log.Logs;

/**
 * @author xulujun
 * @date 2018/07/08
 */
public class BizService<T extends BaseRequest, R extends BaseResult> extends
        AbstractTemplateService<T, R> implements TemplateSupport {


    public BizService(T request, R result, Logger logger) {
        super(request, result, logger);
    }


    public R execute(BizExecutor<T, R> bizExecutor) {
        Results.success(result);
        try {
            bizExecutor.beforeExecute(request, result);
            if (result.isSuccess()) {
                bizExecutor.execute(request, result);
            }
        } catch (ArgsCheckException ex) {
            //不打印日志
            if (result.isSuccess()) { //false时证明caller已经处理过结果，无需重新处理
                Results.fail(result, ResultCodes.ILLEGAL_PARAM.getCode(), ex.getMessage());
            }
        } catch (IllegalArgumentException ex) {
            Results.fail(result, ResultCodes.ILLEGAL_PARAM.getCode(), ex.getMessage());
        } catch (ServiceException ex) {
            Results.fail(result, ex.getErrorCode(), ex.getMessage());
            Logs.error(ex, logger, ex.getClass().getSimpleName(), ex);
        } catch (SQLException ex) {
            Results.fail(result, ResultCodes.ERROR_DB.getCode(), ex.getMessage());
            Logs.error(ex, logger, "SQLException", ex);
        } catch (Exception ex) {
            Results.fail(result, ResultCodes.EXCEPTION.getCode(), ex.getMessage());
            Logs.error(ex, logger, "UnexpectedException", ex);
        }
        return result;
    }


}
