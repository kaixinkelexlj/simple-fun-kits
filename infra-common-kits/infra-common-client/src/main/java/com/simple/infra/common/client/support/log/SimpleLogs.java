package com.simple.infra.common.client.support.log;

import org.slf4j.Logger;

import com.simple.infra.common.client.utils.CommonUtils;

/**
 * @author xulujun
 * @date 2018/07/17
 */
public class SimpleLogs {

    private static final String LOG_FORMAT = "{}";


    public static void info(Logger logger, String messageFormat,
            Object... args) {
        logger.info(CommonUtils.formatString(LOG_FORMAT,
                CommonUtils.formatString(messageFormat, args)
        ));
    }

    public static void warn(Logger logger, String messageFormat,
            Object... args) {
        logger.warn(CommonUtils.formatString(LOG_FORMAT,
                CommonUtils.formatString(messageFormat, args)
        ));
    }

    public static void error(Logger logger, String messageFormat,
            Object... args) {
        logger.error(CommonUtils.formatString(LOG_FORMAT, CommonUtils.formatString(messageFormat, args)));
    }

    public static void error(Throwable e, Logger logger, String messageFormat,
            Object... args) {
        logger.error(CommonUtils.formatString(LOG_FORMAT, CommonUtils.formatString(messageFormat, args)), e);
    }


}
