package com.simple.infra.common.client.result;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;

import com.simple.infra.common.client.lang.ToString;
import com.simple.infra.common.client.support.trace.Traces;

/**
 * @author xulujun
 * @date 2018/07/06
 */
public class BaseResult extends ToString implements ApiResult {

    private static final long serialVersionUID = 8123340587495679489L;


    public BaseResult() {
        this(true, ResultCodes.SUCCESS.getCode(), ResultCodes.SUCCESS.getMessage());
    }

    public BaseResult(boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.traceId = Traces.getTraceId();
    }

    /**
     * 执行结果成功与否 true / false
     */
    private boolean success = true;

    /**
     * 执行结果Code标识
     */
    private String code = ResultCodes.SUCCESS.getCode();

    /**
     * 执行结果信息
     */
    private String message = ResultCodes.SUCCESS.getMessage();

    /**
     * 需要提示给用户的错误信息
     */
    private String messageDisplay;

    /**
     * 每次请求会话标识，全局唯一，用于请求链路分析,与BaseRequest中的 traceId保持一致
     */
    private String traceId;

    /**
     * extra info
     */
    private Map<String, Object> extra = new HashMap<String, Object>();

    @Override
    public String getTraceId() {
        return traceId;
    }

    @Override
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageDisplay() {
        return messageDisplay;
    }

    public void setMessageDisplay(String messageDisplay) {
        this.messageDisplay = messageDisplay;
    }

    public Map<String, Object> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, Object> extra) {
        this.extra = extra;
    }

    public void add2Extra(String key, Object value) {
        this.extra.put(key, value);
    }

    public Object getExtraValue(String key) {
        if (extra == null) {
            return null;
        }
        return extra.get(key);
    }

    public boolean isExtraPresent() {
        return this.extra != null && !this.extra.isEmpty();
    }

    public int getIntCode() {
        return NumberUtils.toInt(this.getCode(), ResultCodes.INVALID_CODE_NUMER);
    }

}
