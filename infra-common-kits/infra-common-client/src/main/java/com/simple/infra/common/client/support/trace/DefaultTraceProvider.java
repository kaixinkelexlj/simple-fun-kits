package com.simple.infra.common.client.support.trace;

import com.simple.infra.common.client.utils.CommonUtils;
import java.util.Optional;

/**
 * @author xulujun
 */
public class DefaultTraceProvider implements TraceProvider {

  public Optional<String> traceId() {
    return Optional.of(CommonUtils.getUUID());
  }

  public Optional<String> spanId() {
    return Optional.empty();
  }

  @Override
  public Optional<String> newTraceId() {
    return Optional.empty();
  }

  @Override
  public Optional<String> newSpanId() {
    return Optional.empty();
  }


}
