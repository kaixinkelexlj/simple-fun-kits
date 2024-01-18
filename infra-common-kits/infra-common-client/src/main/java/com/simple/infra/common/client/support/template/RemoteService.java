package com.simple.infra.common.client.support.template;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.infra.common.client.exception.ArgsCheckException;
import com.simple.infra.common.client.exception.HttpException;
import com.simple.infra.common.client.lang.Assert;

/**
 * @author xulujun
 * @date 2018/08/23
 */
public class RemoteService<R> implements TemplateSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteService.class);

    private Logger logger;
    private RemoteExecutor<R> executor;
    private R result;

    public RemoteService(R result, Logger logger,
            RemoteExecutor<R> executor) {
        Assert.notNull(result);
        Assert.notNull(executor);
        this.logger = Optional.ofNullable(logger).orElse(LOGGER);
        this.executor = executor;
        this.result = result;
    }

    public R execute() {
        Assert.notNull(executor);
        try {
            executor.beforeExecute();
            return executor.doExecute();
        } catch (ArgsCheckException | IllegalArgumentException ex) {
            executor.onArgsCheckException(result, logger, ex);
        } catch (HttpException ex) {
            executor.onHttpException(result, logger, ex);
        } catch (Exception ex) {
            executor.onException(result, logger, ex);
        }
        return result;
    }

}
