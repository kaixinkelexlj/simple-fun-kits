package com.simple.infra.common.client.result;

import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.infra.common.client.lang.ToString;
import com.simple.infra.common.client.support.log.Logs;
import com.simple.infra.common.client.support.trace.Traces;
import com.simple.infra.common.client.utils.CommonUtils;
import com.simple.infra.common.client.utils.Pager;

/**
 * 适配很多工程里现有的result结果
 *
 * @author xulujun
 * @date 2018/11/06
 */
public class RestResult extends SimpleResult<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestResult.class);

    private static final long serialVersionUID = 1502479460124581668L;

    public static final int DEFAULT_SUCCESS_META_CODE = 0;

    private Meta meta = new Meta();
    private Pager pagination;

    @Override
    public boolean isSuccess() {
        int code = Optional.ofNullable(getMeta()).map(Meta::getCode).orElse(0);
        return code == 0 || ResultCodes.SUCCESS.getCode().equals(String.valueOf(code));

    }

    @Override
    public String getMessage() {
        return Optional.ofNullable(getMeta())
                .map(meta -> CommonUtils.formatString("{}::{}", meta.getCode(),
                        StringUtils.isNotBlank(meta.getErrorMsg()) ? meta.getErrorMsg() : super.getMessage()))
                .orElse(super.getMessage());
    }

    @Override
    public String getTraceId() {
        try {
            //优先级 traceId > meta.getTraceId > Traces.getTraceId()
            return Optional.ofNullable(super.getTraceId()).orElse(
                    Optional.ofNullable(getMeta()).map(Meta::getTraceId).filter(
                            StringUtils::isNotBlank).orElse(Traces.getTraceId()));
        } catch (Exception ex) {
            Logs.error(ex, LOGGER, "exception");
            return Traces.getTraceId();
        }
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Pager getPagination() {
        return pagination;
    }

    public void setPagination(long total, int pageNo, int pageSize) {
        if (pagination == null) {
            pagination = new Pager();
        }
        pagination.setPageNo(pageNo);
        pagination.setPageSize(pageSize);
        pagination.setTotalCount(total);
    }

    public void setPagination(Pager pagination) {
        this.pagination = pagination;
    }

    public static class Meta extends ToString {

        private static final long serialVersionUID = 131383276637850354L;

        private int code = DEFAULT_SUCCESS_META_CODE;
        private String errorMsg;
        private String errorType;
        private String traceId;
        private Map<String, ?> errorData;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getTraceId() {
            return traceId;
        }

        public void setTraceId(String traceId) {
            this.traceId = traceId;
        }

        public Map<String, ?> getErrorData() {
            return errorData;
        }

        public void setErrorData(Map<String, ?> errorData) {
            this.errorData = errorData;
        }

        public String getErrorType() {
            return errorType;
        }

        public void setErrorType(String errorType) {
            this.errorType = errorType;
        }
    }


}
