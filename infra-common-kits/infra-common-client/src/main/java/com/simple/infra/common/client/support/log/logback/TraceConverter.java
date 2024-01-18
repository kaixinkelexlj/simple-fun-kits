package com.simple.infra.common.client.support.log.logback;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.simple.infra.common.client.support.trace.Traces;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * @author xulujun
 * @date 2018/08/09
 */
public class TraceConverter extends ClassicConverter {

    private static final String TRACEID_PATTERN = "traceid=";

    @Override
    public String convert(ILoggingEvent event) {
        if (event != null && StringUtils.isNotBlank(event.getMessage())
                && event.getMessage().contains(TRACEID_PATTERN)) {
            return "";
        }
        return TRACEID_PATTERN + Optional.ofNullable(Traces.getTraceId()).orElse("");
    }

}
