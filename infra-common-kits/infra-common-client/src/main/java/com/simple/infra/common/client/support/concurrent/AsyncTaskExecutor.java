package com.simple.infra.common.client.support.concurrent;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.infra.common.client.lang.Assert;
import com.simple.infra.common.client.utils.ThreadPoolUtils;

/**
 * @author xulujun
 * @date 2018/08/23
 */
public class AsyncTaskExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncTaskExecutor.class);

    private ExecutorService pool;

    // CHECKSTYLE:OFF
    public AsyncTaskExecutor() {
        this.pool = new ThreadPoolExecutor(20, 200, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50),
                new NamedThreadFactory("AsyncTaskExecutor"),
                new CallerRunsPolicy());
    }

    public AsyncTaskExecutor(ExecutorService pool) {
        this.pool = pool;
    }

    public void execute(Runnable task) {
        this.pool.submit(new AsyncTask(task));
    }

    public void execute(ExecutorService pool, Runnable task) {
        Assert.notNull(pool);
        pool.submit(new AsyncTask(task));
    }

    public void destroy() {
        ThreadPoolUtils.shutdown(pool);
    }


}
