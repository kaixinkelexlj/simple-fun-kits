/*
 * Copyright 2017 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.fun.service.dao.domain;


import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author lujun.xlj
 * @date 2017/4/28
 */
public abstract class BaseDO implements Serializable {

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
  }
}
