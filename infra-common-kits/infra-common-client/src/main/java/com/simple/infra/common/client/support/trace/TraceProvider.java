package com.simple.infra.common.client.support.trace;

import java.util.Optional;

/**
 * @author yourname xulujun@kuaishou.com
 * Created on 2021-07-28
 */
public interface TraceProvider {

    Optional<String> traceId();

    Optional<String> spanId();

    Optional<String> newTraceId();

    Optional<String> newSpanId();
}
