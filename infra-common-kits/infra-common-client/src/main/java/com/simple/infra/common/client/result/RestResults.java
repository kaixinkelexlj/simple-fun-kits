package com.simple.infra.common.client.result;

import java.util.Map;
import java.util.Optional;


import org.apache.commons.lang3.StringUtils;

import com.simple.infra.common.client.result.RestResult.Meta;
import com.simple.infra.common.client.support.trace.Traces;

/**
 * @author xulujun
 * @date 2018/11/06
 */
public class RestResults {

    public static RestResult of(BaseResult result) {
        if (!result.isSuccess()) {
            return fail(result);
        }
        return success(result);
    }

    public static <T> RestResult of(SimpleResult<T> result) {
        if (!result.isSuccess()) {
            return fail(result);
        }
        return success(result);
    }

    public static <T> RestResult of(PaginationResult<T> result) {
        if (!result.isSuccess()) {
            return fail(result);
        }
        return success(result);
    }

    public static RestResult fail(BaseResult result) {
        return fail(result, null);
    }

    public static RestResult fail(BaseResult result, Map<String, ?> errorData) {
        result.setTraceId(Traces.getTraceId());
        Meta meta = new Meta();
        int code = result.getIntCode();
        meta.setCode(code < 0 ? 1 : result.getIntCode());
        meta.setErrorMsg(Optional.ofNullable(result.getMessageDisplay()).orElse(""));
        meta.setErrorType("Error::" + result.getIntCode());
        meta.setTraceId(result.getTraceId());
        if (errorData != null) {
            meta.setErrorData(errorData);
        } else if (result.getExtra() != null) {
            meta.setErrorData(result.getExtra());
        }
        RestResult response = new RestResult();
        response.setMeta(meta);
        response.setTraceId(result.getTraceId());
        response.setCode(String.valueOf(meta.getCode()));
        response.setMessage(result.getMessage());
        response.setPagination(null);
        return response;
    }

    public static RestResult success(BaseResult result) {
        return ofSuccess(result);
    }

    public static <T> RestResult success(SimpleResult<T> result) {
        RestResult restResult = ofSuccess(result);
        restResult.setData(result.getData());
        return restResult;
    }

    public static <T> RestResult success(PaginationResult<T> result) {
        RestResult restResult = ofSuccess(result);
        restResult.setData(result.getData());
        restResult.setPagination(result.getCount(), result.getPageNo(), result.getPageSize());
        return restResult;
    }

    public static Optional<String> getDisplayMessage(ApiResult result) {
        String messageDisplay = getDisplayMessageInternal(result);
        return StringUtils.isBlank(messageDisplay) ? Optional.empty() : Optional.of(messageDisplay);
    }

    private static String getDisplayMessageInternal(ApiResult result) {
        if (result == null) {
            return null;
        }
        if (result instanceof RestResult) {
            return Optional.ofNullable(((RestResult) result).getMeta())
                    .map(Meta::getErrorMsg)
                    .orElse(null);
        } else if (result instanceof BaseResult) {
            return ((BaseResult) result).getMessageDisplay();
        }
        return null;
    }

    private static <T> RestResult ofSuccess(BaseResult result) {
        result.setTraceId(Traces.getTraceId());
        Meta meta = new Meta();
        meta.setCode(0);
        meta.setTraceId(Traces.getTraceId());
        RestResult response = new RestResult();
        response.setMeta(meta);
        response.setPagination(null);
        return response;
    }

}
