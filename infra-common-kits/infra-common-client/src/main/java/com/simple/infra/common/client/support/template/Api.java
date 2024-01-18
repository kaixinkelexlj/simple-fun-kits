package com.simple.infra.common.client.support.template;

import org.slf4j.Logger;

import com.simple.infra.common.client.request.BaseRequest;
import com.simple.infra.common.client.result.BaseResult;

/**
 * @author xulujun
 * @date 2018/07/13
 */
public interface Api {

    static <T extends BaseRequest, R extends BaseResult> ApiService<T, R> of(T request, R result, Logger logger) {
        return new ApiService<T, R>(request, result, logger);
    }

}
