package com.simple.infra.common.client.result;


import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author xulujun
 * @date 2018/07/06
 */
public interface ResultCode {

    /**
     * 结果码
     */
    String getCode();

    /**
     * code name
     */
    String getName();

    /**
     * 结果描述
     */
    String getMessage();

    /**
     * 用于显示的消息
     */
    String getDisplayMessage();

    default int getIntCode() {
        return NumberUtils.toInt(getCode(), ResultCodes.INVALID_CODE_NUMER);
    }

}
