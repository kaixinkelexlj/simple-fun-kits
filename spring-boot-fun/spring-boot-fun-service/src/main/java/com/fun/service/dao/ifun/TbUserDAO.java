/*
 * Copyright 2017 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.fun.service.dao.ifun;

import com.fun.service.dao.domain.TbUserDO;
import org.apache.ibatis.annotations.Param;

/**
 * @author lujun.xlj
 * @date 2017/4/28
 */
public interface TbUserDAO {

    TbUserDO getByNick(@Param("nick") String nick);

}
