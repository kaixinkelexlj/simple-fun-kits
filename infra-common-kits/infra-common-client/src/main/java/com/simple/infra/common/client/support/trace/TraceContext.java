package com.simple.infra.common.client.support.trace;

/**
 * @author xulujun
 * @date 2018/08/23
 */
public class TraceContext {

    private String traceId;
    private String userName;
    private String api;
    private String level;
    private String env;

    public TraceContext() {
    }

    public TraceContext(String traceId, String userName) {
        this.traceId = traceId;
        this.userName = userName;
    }

    public static TraceContext create() {
        TraceContext traceContext = new TraceContext(Traces.getTraceId(), Traces.getUserName());
        traceContext.setApi(Traces.getApi());
        traceContext.setLevel(Traces.getLevel());
        traceContext.setEnv(Traces.getEnv());
        return traceContext;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }
}
