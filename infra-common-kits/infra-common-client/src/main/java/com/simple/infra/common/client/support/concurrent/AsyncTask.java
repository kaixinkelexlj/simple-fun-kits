package com.simple.infra.common.client.support.concurrent;


import com.simple.infra.common.client.support.trace.TraceContext;
import com.simple.infra.common.client.support.trace.Traces;

/**
 * @author xulujun
 * @date 2018/08/23
 */
public class AsyncTask implements Runnable {

    private final TraceContext traceContext;
    private final Runnable task;

    public AsyncTask(Runnable task) {
        this.traceContext = TraceContext.create();
        this.task = task;
    }

    @Override
    public void run() {
        execute();
    }

    public void execute() {
        Traces.copy(this.traceContext);
        try {
            task.run();
        } finally {
            Traces.reset();
        }
    }

}
