package com.simple.infra.common.client.result;

import java.util.List;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;

import com.simple.infra.common.client.lang.Assert;
import com.simple.infra.common.client.utils.CommonUtils;

/**
 * @author xulujun
 * @date 2018/07/07
 */
public class Results {

    public static BaseResult newBaseResult() {
        return new BaseResult(true, ResultCodes.SUCCESS.getCode(),
                ResultCodes.SUCCESS.getMessage());
    }

    public static <T> SimpleResult<T> newSimpleResult() {
        return new SimpleResult<>();
    }

    public static <T> SimpleResult<T> newSimpleResult(T data) {
        return new SimpleResult<>(data);
    }

    public static <T> ListResult<T> newListResult() {
        return new ListResult<T>();
    }

    public static <T> ListResult<T> newListResult(List<T> data) {
        ListResult<T> result = new ListResult<T>();
        success(result, data);
        return result;
    }

    public static <T> ListResult<T> newListResult(List<T> data, Integer count) {
        ListResult<T> result = new ListResult<T>();
        success(result, data, count);
        return result;
    }

    public static <T> PaginationResult<T> newPaginationResult() {
        return new PaginationResult<>();
    }

    public static <T> PaginationResult<T> newPaginationResult(List<T> data, Integer pageNo,
            Integer pageSize, Integer count) {
        return new PaginationResult<>(data, pageNo, pageSize, count);
    }

    public static <T extends BaseResult> T fail(Class<T> resultClass, String code,
            String messageFormat, Object... args) {
        T result = newResultInstance(resultClass);
        fail(result, code, messageFormat, args);
        return result;
    }

    public static <T extends BaseResult> T fail(Class<T> resultClass, ResultCode resultCode) {
        T result = newResultInstance(resultClass);
        fail(result, resultCode, resultCode.getMessage());
        return result;
    }

    public static <T extends BaseResult> T fail(Class<T> resultClass, ResultCode resultCode,
            String messageFormat,
            Object... args) {
        T result = newResultInstance(resultClass);
        fail(result, resultCode, messageFormat, args);
        return result;
    }

    /**
     * 构造的错误消息直接被前端展示给用户
     *
     * @param messageFormat {}或%s占位
     */
    public static <T extends BaseResult> T failWithDisplayMessage(Class<T> resultClass, String code,
            String messageFormat,
            Object... args) {
        T result = newResultInstance(resultClass);
        failWithDisplayMessage(result, code, messageFormat, args);
        return result;
    }

    public static <T extends BaseResult> T failWithDisplayMessage(Class<T> resultClass,
            ResultCode resultCode,
            String messageFormat, Object... args) {
        T result = newResultInstance(resultClass);
        failWithDisplayMessage(result, resultCode, messageFormat, args);
        return result;
    }

    public static void fail(BaseResult result, String code, String messageFormat, Object... args) {
        Assert.notNull(result);
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(CommonUtils
                .formatString(Optional.ofNullable(messageFormat).orElse(ResultCodes.ERROR.getName()),
                        args));
    }

    public static void fail(BaseResult result, ResultCode resultCode) {
        fail(result, resultCode, resultCode.getMessage());
    }

    public static void fail(BaseResult result, ResultCode resultCode, String messageFormat,
            Object... args) {
        Assert.notNull(result);
        Assert.notNull(resultCode);
        result.setSuccess(false);
        result.setCode(resultCode.getCode());
        result.setMessage(CommonUtils.formatString(messageFormat, args));
    }

    /**
     * 构造的错误消息直接被前端展示给用户
     *
     * @param messageFormat {}或%s占位
     */
    public static void failWithDisplayMessage(BaseResult result, String code, String messageFormat,
            Object... args) {
        Assert.notNull(result);
        Assert.hasLength(messageFormat, "messageFormat is blank");
        result.setSuccess(false);
        result.setCode(code);
        String message = CommonUtils.formatString(messageFormat, args);
        result.setMessage(message);
        result.setMessageDisplay(message);
    }

    public static void failWithDisplayMessage(BaseResult result, ResultCode resultCode,
            String messageFormat, Object... args) {
        Assert.notNull(result);
        Assert.hasLength(messageFormat, "messageFormat is blank");
        result.setSuccess(false);
        result.setCode(resultCode.getCode());
        String message = CommonUtils.formatString(messageFormat, args);
        result.setMessage(message);
        result.setMessageDisplay(message);
    }

    public static void success(BaseResult result) {
        Assert.notNull(result);
        result.setSuccess(true);
        result.setCode(ResultCodes.SUCCESS.getCode());
        result.setMessage(ResultCodes.SUCCESS.getMessage());
    }

    public static <T> void success(SimpleResult<T> result, T data) {
        Assert.notNull(result);
        success(result);
        result.setData(data);
    }

    public static <T> void success(ListResult<T> result, List<T> data) {
        Assert.notNull(result);
        success(result);
        result.setData(data);
        result.setCount(Optional.ofNullable(data).map(List::size).orElse(0));
    }

    public static <T> void success(ListResult<T> result, List<T> data, Integer count) {
        Assert.notNull(result);
        success(result);
        result.setData(data);
        result.setCount(count);
    }

    public static <T> void success(PaginationResult<T> result, List<T> data, Integer pageNo,
            Integer pageSize, Integer count) {
        success(result);
        result.setData(data);
        result.setPageNo(pageNo);
        result.setPageSize(pageSize);
        result.setCount(count);
    }

    public static <T extends BaseResult> void copy(T target, T source) {
        Assert.notNull(target);
        if (source == null) {
            return;
        }
        try {
            BeanUtils.copyProperties(target, source);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    private static <T extends BaseResult> T newResultInstance(Class<T> resultClass) {
        try {
            return resultClass.newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("can't create instance for[" + resultClass.getName() + "]");
        }
    }

}
