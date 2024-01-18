package com.simple.infra.common.client.support.template;

import org.slf4j.Logger;

import com.simple.infra.common.client.exception.HttpException;
import com.simple.infra.common.client.exception.RemoteServiceException;
import com.simple.infra.common.client.utils.CommonUtils;

/**
 * @author xulujun
 * @date 2018/08/23
 */
public abstract class RemoteExecutor<R> implements TemplateSupport {

    protected abstract void beforeExecute();

    protected abstract R doExecute();

    /**
     * 远程调用参数，打印exception日志使用
     *
     * @see RemoteExecutor#onException
     */
    protected abstract Object getRemoteRequest();

    protected void onArgsCheckException(R result, Logger logger,
            RuntimeException ex) {
        throw ex;
    }

    protected void onHttpException(R result, Logger logger, HttpException ex) {
        error(ex, logger, "call remote HttpException, args[{}]",
                CommonUtils.toJSONString(getRemoteRequest()));
        throw ex;
    }

    protected void onException(R result, Logger logger, Exception ex) {
        error(ex, logger, "call remote exception, args[{}]",
                CommonUtils.toJSONString(getRemoteRequest()));
        throw new RemoteServiceException(ex, "call remote exception, args[{}]",
                CommonUtils.toJSONString(getRemoteRequest()));
    }


}
