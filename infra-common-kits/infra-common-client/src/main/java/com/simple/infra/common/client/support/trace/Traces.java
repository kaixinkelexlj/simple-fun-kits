package com.simple.infra.common.client.support.trace;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.simple.infra.common.client.support.context.ApplicationEnv;
import com.simple.infra.common.client.support.context.RequestLevelEnum;
import com.simple.infra.common.client.utils.NetUtils;

/**
 * @author xulujun
 * @date 2018/07/30
 */
public class Traces {

    private static final String UNSET = "#unset#";

    private static final TraceProvider TRACE_DELEGATE = new DefaultTraceProvider();

    public static TraceProvider getTraceDelegate() {
        return TRACE_DELEGATE;
    }

    public static String resetAndGet() {
        reset();
        return getTraceId();
    }

    public static void setTraceId() {
        TraceId.create();
    }

    public static void setTraceId(String traceId) {
        TraceId.create(traceId);
    }

    public static String getTraceId() {
        return TraceId.get();
    }

    public static String generateSubSpanId() {
        return TraceId.get();
    }

    public static boolean isUserPresent() {
        return RequestHolder.isUserPresent();
    }

    public static boolean isApiPresent() {
        return RequestHolder.isPathPresent();
    }

    public static String getUserName() {
        String userName = RequestHolder.getUserName();
        return StringUtils.isNotBlank(userName) ? userName : UNSET;
    }

    public static String getApi() {
        String api = RequestHolder.getPath();
        return StringUtils.isNotBlank(api) ? api : UNSET;
    }

    public static String getHostAndIp() {
        return getHost() + "(" + getIp() + ")";
    }

    public static String getIp() {
        try {
            return NetUtils.getLocalHost();
        } catch (Exception ignore) {
        }
        return StringUtils.EMPTY;
    }

    public static String getHost() {
        try {
            return NetUtils.getLocalAddress().getHostName();
        } catch (Exception ignore) {
        }
        return StringUtils.EMPTY;
    }

    public static String getEnv() {
        return Optional.ofNullable(ApplicationEnv.current()).map(Enum::toString).orElse(UNSET);
    }

    public static RequestLevelEnum getLevelEnum() {
        return RequestHolder.getRequestLevelEnum();
    }

    public boolean isLevelPresent() {
        return RequestHolder.isLevelPresent();
    }

    public static String getLevel() {
        return RequestHolder.getRequestLevel();
    }

    public static void reset() {
        resetTraceId();
        RequestHolder.reset();
    }

    public static void resetTraceId() {
        TraceId.reset();
    }

    /**
     * 注意必须和reset成对使用，否则可能因为LogMessage使用InheritableThreadLocal，出现traceId复用
     */
    public static void copy(TraceContext traceContext) {
        setTraceId(traceContext.getTraceId());
        RequestHolder.copy(traceContext);
    }
}
