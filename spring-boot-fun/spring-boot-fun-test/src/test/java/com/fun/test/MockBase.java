/*
 * Copyright 2017 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.fun.test;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

/**
 * @author lujun.xlj
 * @date 2017/5/9
 */
public abstract class MockBase {

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

}
