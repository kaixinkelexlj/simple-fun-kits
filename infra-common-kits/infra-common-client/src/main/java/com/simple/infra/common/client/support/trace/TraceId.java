package com.simple.infra.common.client.support.trace;

import org.apache.commons.lang3.StringUtils;

import com.simple.infra.common.client.utils.CommonUtils;

/**
 * @author xulujun
 * @date 2018/7/10
 */
public class TraceId {

    private static final ThreadLocal<String> TRACE_ID = new ThreadLocal<>();

    private TraceId() {
    }

    public static void create() {
        create(null);
    }

    public static void create(String value) {
        if (StringUtils.isNotBlank(value)) {
            TRACE_ID.set(value);
            return;
        }
        String id = Traces.getTraceDelegate().traceId().orElse(CommonUtils.getUUID());
        TRACE_ID.set(id);
    }

    public static String get() {
        String val = TRACE_ID.get();
        if (val == null) {
            create(null);
            val = TRACE_ID.get();
        }
        return val;
    }

    public static void reset() {
        TRACE_ID.remove();
    }

}