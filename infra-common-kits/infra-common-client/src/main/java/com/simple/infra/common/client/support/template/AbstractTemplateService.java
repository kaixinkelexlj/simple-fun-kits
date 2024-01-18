package com.simple.infra.common.client.support.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.infra.common.client.lang.Assert;
import com.simple.infra.common.client.request.BaseRequest;
import com.simple.infra.common.client.result.BaseResult;

/**
 * @author xulujun
 * @date 2018/07/09
 */
public abstract class AbstractTemplateService<T extends BaseRequest, R extends BaseResult> implements
        TemplateService<T, R> {

    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractTemplateService.class);
    protected T request;
    protected R result;
    //protected ServiceId serviceId;
    protected Logger logger;


    public AbstractTemplateService(T request, R result, Logger logger) {
        Assert.notNull(request);
        Assert.notNull(result);
        //Assert.notNull(serviceId);
        if (logger == null) {
            logger = LOGGER;
        }

        this.request = request;
        this.result = result;
        //this.serviceId = serviceId;
        this.logger = logger;
    }


}
