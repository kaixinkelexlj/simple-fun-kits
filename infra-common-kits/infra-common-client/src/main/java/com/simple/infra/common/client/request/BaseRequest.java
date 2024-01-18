package com.simple.infra.common.client.request;

import java.util.Optional;

import com.simple.infra.common.client.lang.ToString;

/**
 * @author xulujun
 * @date 2018/07/07
 */
public class BaseRequest extends ToString implements ApiRequest {

    private static final long serialVersionUID = 3729850788297785267L;

    /**
     * 每次请求会话标识，全局唯一，用于请求链路分析
     */
    private String traceId;

    private Long userId;

    private String userName;

    private String ticket;

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

    public boolean isUserPresent() {
        return Optional.ofNullable(userName).isPresent();
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public boolean isTicketPresent() {
        return Optional.ofNullable(ticket).isPresent();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isUserIdPresent() {
        return Optional.ofNullable(userId).isPresent();
    }
}
