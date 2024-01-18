package com.simple.infra.common.client.support.template;

import org.slf4j.Logger;

import com.simple.infra.common.client.request.BaseRequest;
import com.simple.infra.common.client.result.BaseResult;

/**
 * @author xulujun
 * @date 2018/07/13
 */
public interface Biz {

    static <T extends BaseRequest, R extends BaseResult> BizService<T, R> of(T request,
            R result, Logger logger) {
        return new BizService<T, R>(request, result, logger);
    }

}
