package com.simple.infra.common.client.lang;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author xulujun
 * @date 2018/07/06
 */
public abstract class ToString implements Serializable {

    private static final long serialVersionUID = -1380134360485646867L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
