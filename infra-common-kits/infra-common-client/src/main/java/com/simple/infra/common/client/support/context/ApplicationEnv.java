package com.simple.infra.common.client.support.context;

/**
 * @author xulujun
 * @date 2018/09/06
 */
public class ApplicationEnv {

    private static volatile String applicationName = "<unset>";

    private static volatile EnvironmentEnum current = EnvironmentEnum.NONE;

    public static synchronized void setApplicationName(String appName) {
        if (applicationName == null) {
            applicationName = appName;
        }
    }

    public static synchronized void setCurrent(EnvironmentEnum environmentEnum) {
        if (current == EnvironmentEnum.NONE) {
            current = environmentEnum;
        }
    }

    public static EnvironmentEnum current() {
        return current;
    }

    public static String currentApplicationName() {
        return applicationName;
    }

}
