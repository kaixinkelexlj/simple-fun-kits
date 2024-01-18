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
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author lujun.xlj
 * @date 2017/4/28
 */
@RunWith(MockitoJUnitRunner.class)
public class TbUserDAOMockTest {

  @Mock
  private TbUserDAO tbUserDAO;

  @Test
  public void testgetByNick() throws Exception {

    TbUserDO mockData = new TbUserDO();
    mockData.setNick("ifun");
    mockData.setUname("行默");

    Mockito.when(tbUserDAO.getByNick(Mockito.anyString())).thenReturn(mockData);

    TbUserDO data = tbUserDAO.getByNick("ifun");
    System.out.println(JSON.toJSONString(data));
    Assert.assertEquals("行默", data.getUname());

  }
}
