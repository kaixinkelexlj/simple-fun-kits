package com.simple.infra.common.client.support.trace;

import java.util.Optional;

/**
 * @author xulujun
 */
public interface TraceProvider {

    Optional<String> traceId();

    Optional<String> spanId();

    Optional<String> newTraceId();

    Optional<String> newSpanId();
}
