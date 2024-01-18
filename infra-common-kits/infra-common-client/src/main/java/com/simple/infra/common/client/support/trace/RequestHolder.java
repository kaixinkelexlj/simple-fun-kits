package com.simple.infra.common.client.support.trace;

import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.simple.infra.common.client.lang.Assert;
import com.simple.infra.common.client.model.BaseDO;
import com.simple.infra.common.client.support.context.RequestLevelEnum;

/**
 * @author xulujun
 * @date 2018/12/03
 */
public class RequestHolder {

    private static final ThreadLocal<String> PATH_THREAD_LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<RequestUser> DIDI_USER_THREAD_LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<RequestLevelEnum> REQUEST_LEVEL_ENUM_THREAD_LOCAL = new ThreadLocal<>();

    public static void setPath(String path) {
        PATH_THREAD_LOCAL.set(path);
    }

    public static String getPath() {
        return PATH_THREAD_LOCAL.get();
    }

    public static boolean isPathPresent() {
        return Optional.ofNullable(PATH_THREAD_LOCAL.get()).isPresent();
    }

    public static void setUser(RequestUser requestUser) {
        Assert.notNull(requestUser);
        DIDI_USER_THREAD_LOCAL.set(requestUser);
    }

    public static RequestUser getUser() {
        return userOptional().orElse(null);
    }

    public static boolean isUserPresent() {
        return userOptional().isPresent();
    }

    public static String getUserName() {
        return Optional.ofNullable(getUser()).map(RequestUser::getUserName).orElse(null);
    }

    private static Optional<RequestUser> userOptional() {
        return Optional.ofNullable(DIDI_USER_THREAD_LOCAL.get());
    }

    public static boolean isLevelPresent() {
        return REQUEST_LEVEL_ENUM_THREAD_LOCAL.get() != null;
    }

    public static void setLevel(RequestLevelEnum levelEnum) {
        REQUEST_LEVEL_ENUM_THREAD_LOCAL.set(levelEnum);
    }

    public static void setLevel(String level) {
        REQUEST_LEVEL_ENUM_THREAD_LOCAL.set(RequestLevelEnum.of(level));
    }

    public static RequestLevelEnum getRequestLevelEnum() {
        return Optional.ofNullable(REQUEST_LEVEL_ENUM_THREAD_LOCAL.get())
                .orElse(RequestLevelEnum.Default);
    }

    public static String getRequestLevel() {
        return getRequestLevelEnum().name();
    }

    public static void reset() {
        PATH_THREAD_LOCAL.remove();
        DIDI_USER_THREAD_LOCAL.remove();
        REQUEST_LEVEL_ENUM_THREAD_LOCAL.remove();
    }

    public static void copy(TraceContext traceContext) {
        setPath(traceContext.getApi());
        setUser(RequestUser.of(traceContext.getUserName()));
        setLevel(RequestLevelEnum.of(traceContext.getLevel()));
    }

    public static class RequestUser extends BaseDO {

        private static final long serialVersionUID = -217772393051467311L;

        private String userName;
        private String ticket;
        private Long userId;

        public static RequestUser of(Long userId) {
            return of(userId, userId != null && userId > 0 ? ("u_" + userId) : "");
        }

        public static RequestUser of(Long userId, String userName) {
            RequestUser user = new RequestUser();
            user.setUserId(userId);
            user.setUserName(userName);
            return user;
        }

        public static RequestUser of(String userName) {
            return of(userName, null);
        }

        public static RequestUser of(String userName, String userTicket) {
            if (StringUtils.isBlank(userName)) {
                return null;
            }
            RequestUser user = new RequestUser();
            user.setUserName(userName);
            user.setTicket(userTicket);
            return user;
        }

        public RequestUser() {
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getTicket() {
            return ticket;
        }

        public void setTicket(String ticket) {
            this.ticket = ticket;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof RequestUser)) {
                return false;
            }
            RequestUser requestUser = (RequestUser) o;
            return Objects.equals(userName, requestUser.userName) && Objects.equals(userId, requestUser.getUserId());
        }

        @Override
        public int hashCode() {
            return Objects.hash(userName);
        }
    }

}
