/*
 * Copyright 2017 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.fun.service.dao.ifun;

import com.alibaba.fastjson.JSON;
import com.fun.service.dao.domain.TbUserDO;
import com.fun.test.TestBase;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author lujun.xlj
 * @date 2017/4/28
 */
public class TbUserDAOTest extends TestBase {

  @Resource
  private TbUserDAO tbUserDAO;

  @Test
  public void testgetByNick() throws Exception {

    TbUserDO data = tbUserDAO.getByNick("ifun_dev");
    System.out.println(JSON.toJSONString(data));
    Assert.assertEquals("行默DEV", data.getUname());

  }
}
