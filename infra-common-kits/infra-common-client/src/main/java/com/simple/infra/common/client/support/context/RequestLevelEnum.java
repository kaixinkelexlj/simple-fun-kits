package com.simple.infra.common.client.support.context;


import org.apache.commons.lang3.StringUtils;

public enum RequestLevelEnum {

    Default,
    P0,
    P1,
    P2,
    P3,
    P4,
    P5,
    ;

    public static RequestLevelEnum of(String level) {
        if (StringUtils.isBlank(level)) {
            return Default;
        }
        for (RequestLevelEnum e : values()) {
            if (level.equalsIgnoreCase(e.name())) {
                return e;
            }
        }
        return Default;
    }
}