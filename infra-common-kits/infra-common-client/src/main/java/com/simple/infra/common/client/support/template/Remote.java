package com.simple.infra.common.client.support.template;

import org.slf4j.Logger;

/**
 * @author xulujun
 * @date 2018/08/23
 */
public interface Remote {

  static <R> RemoteService<R> of(R result, Logger logger, RemoteExecutor<R> executor) {
    return new RemoteService<>(result, logger, executor);
  }

}
