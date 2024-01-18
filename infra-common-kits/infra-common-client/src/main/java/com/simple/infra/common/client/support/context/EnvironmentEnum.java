package com.simple.infra.common.client.support.context;


import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author xulujun
 * @date 2018/08/30
 */
public enum EnvironmentEnum {

    TEST("test"),
    DEV("dev", "local"),

    PRE("pre"),
    PROD("prod", "prod"),

    NONE("unknown");

    private String[] value;

    EnvironmentEnum(String... env) {
        this.value = env;
    }

    public String[] getValue() {
        return value;
    }

    public void setValue(String[] value) {
        this.value = value;
    }

    public static EnvironmentEnum of(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        for (EnvironmentEnum e : EnvironmentEnum.values()) {
            if (ArrayUtils.contains(e.value, value)) {
                return e;
            }
        }
        return NONE;
    }
}
