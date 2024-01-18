package com.simple.infra.common.client.support.concurrent;

import java.util.concurrent.Callable;

import com.simple.infra.common.client.support.trace.TraceContext;
import com.simple.infra.common.client.support.trace.Traces;

/**
 * @author xulujun
 * @date 2018/08/23
 */
public abstract class AsyncCallableTask<V> implements Callable<V> {

  private final TraceContext traceContext;

  public AsyncCallableTask() {
    this.traceContext = TraceContext.create();
  }

  @Override
  public V call() {
    Traces.copy(this.traceContext);
    try {
      return doRun();
    } finally {
      Traces.reset();
    }
  }

  public abstract V doRun();

}
