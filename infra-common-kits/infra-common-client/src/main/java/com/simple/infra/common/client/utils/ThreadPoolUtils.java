package com.simple.infra.common.client.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xulujun
 * @date 2018/8/23
 */
public class ThreadPoolUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolUtils.class);

    public static void shutdown(ExecutorService pool) {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow();
                if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                    LOGGER.error("pool do not terminate");
                }
            }
        } catch (InterruptedException ie) {
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

}