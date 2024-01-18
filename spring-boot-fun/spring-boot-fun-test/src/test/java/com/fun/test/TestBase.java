/*
 * Copyright 2017 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.fun.test;

import java.util.Arrays;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lujun.xlj
 * @date 2017/3/31
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
public abstract class TestBase{

    @Autowired
    protected Environment environment;

    @Before
    public void setUp() throws Exception{
        if(environment != null){
            System.out.println(Arrays.toString(environment.getActiveProfiles()));
        }else{
            System.out.println("env:<no set>");
        }
    }

}
