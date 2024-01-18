package com.simple.infra.common.client.support.log;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.simple.infra.common.client.utils.CommonUtils;
import com.simple.infra.common.client.utils.Pager;

/**
 * @author xulujun
 * @date 2018/09/05
 */
// CHECKSTYLE:OFF
public class MessageSplitter {

    public static int DEFAULT_SPLIT_SIZE = 1000;

    private String message;
    private int splitSize;

    public MessageSplitter(String message) {
        this.message = message;
    }

    public static MessageSplitter of(String messageFormat,
            Object... args) {
        return new MessageSplitter(CommonUtils.formatString(messageFormat, args));
    }

    public static MessageSplitter of(String message) {
        return new MessageSplitter(message);
    }

    public MessageSplitter splitSize(int splitSize) {
        setSplitSize(Math.max(splitSize, 0));
        return this;
    }

    public List<String> split() {
        if (StringUtils.isBlank(message)) {
            return new ArrayList<>(0);
        }
        List<String> list = new ArrayList<>();
        String part = null;
        Pager pager = new Pager(1, splitSize <= 0 ? DEFAULT_SPLIT_SIZE : splitSize);
        do {
            if (pager.getStart() > message.length()) {
                break;
            }
            part = message.substring(pager.getStart(), Math.min(pager.getEnd(), message.length()));
            if (StringUtils.isNotBlank(part)) {
                list.add(part);
            }
            pager.next();
        } while (StringUtils.isNotBlank(part));
        return list;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSplitSize(int splitSize) {
        this.splitSize = splitSize;
    }

    public int getSplitSize() {
        return splitSize;
    }


}
