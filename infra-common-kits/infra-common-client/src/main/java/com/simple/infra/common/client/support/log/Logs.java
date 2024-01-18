package com.simple.infra.common.client.support.log;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import com.simple.infra.common.client.support.trace.Traces;
import com.simple.infra.common.client.utils.CommonUtils;
import com.simple.infra.common.client.utils.NetUtils;

/**
 * @author xulujun
 * @date 2018/07/17
 */
public class Logs {

    public static final String DEFAULT_LOG_TAG = "_undef";

    // 外部可以全局修改，关闭check-style
    // CHECKSTYLE:OFF
    public static volatile String GLOBAL_LOG_TAG = DEFAULT_LOG_TAG;
    // CHECKSTYLE:ON

    private static final String LOG_FORMAT =
            "%2$s||uri=%5$s||traceid=%1$s||host=%3$s||_msg=%4$s";

    public static void info(Logger logger, String messageFormat, Object... args) {
        info(null, logger, messageFormat, args);
    }

    public static void info(String logTag, Logger logger, String messageFormat,
            Object... args) {
        logger.info(CommonUtils.formatString(LOG_FORMAT,
                Traces.getTraceId(),
                StringUtils.defaultIfBlank(logTag, GLOBAL_LOG_TAG),
                NetUtils.getLocalHost(),
                CommonUtils.formatString(messageFormat, args),
                Traces.getApi()
        ));

    }

    public static void warn(Logger logger, String messageFormat, Object... args) {
        warn(null, logger, messageFormat, args);
    }

    public static void warn(String logTag, Logger logger, String messageFormat,
            Object... args) {
        logger.warn(CommonUtils.formatString(LOG_FORMAT,
                Traces.getTraceId(),
                StringUtils.defaultIfBlank(logTag, GLOBAL_LOG_TAG),
                NetUtils.getLocalHost(),
                CommonUtils.formatString(messageFormat, args),
                Traces.getApi()
        ));
    }


    public static void error(Logger logger, String messageFormat,
            Object... args) {
        error((String) null, logger, messageFormat, args);
    }

    public static void error(String logTag, Logger logger, String messageFormat,
            Object... args) {
        logger.error(
                CommonUtils.formatString(LOG_FORMAT,
                        Traces.getTraceId(),
                        StringUtils.defaultIfBlank(logTag, GLOBAL_LOG_TAG),
                        NetUtils.getLocalHost(),
                        CommonUtils.formatString(messageFormat, args),
                        Traces.getApi()
                ));
    }

    public static void error(Throwable e, Logger logger) {
        error(e, logger, "exception");
    }

    public static void error(Throwable e, Logger logger, String messageFormat, Object... args) {
        error(e, null, logger, messageFormat, args);
    }

    public static void error(Throwable e, String logTag, Logger logger,
            String messageFormat,
            Object... args) {
        logger.error(
                CommonUtils.formatString(LOG_FORMAT,
                        Traces.getTraceId(),
                        StringUtils.defaultIfBlank(logTag, GLOBAL_LOG_TAG),
                        NetUtils.getLocalHost(),
                        CommonUtils.formatString(messageFormat, args),
                        Traces.getApi()), e);
    }

}
