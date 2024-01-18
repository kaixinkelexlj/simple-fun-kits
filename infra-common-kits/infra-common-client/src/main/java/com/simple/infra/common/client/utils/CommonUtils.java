package com.simple.infra.common.client.utils;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

// CHECKSTYLE:OFF
public class CommonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static final Date MIN_DATE;
    public static final Date MAX_DATE;


    public static String CODEC_DEFAULT_CHARSET = "UTF-8";
    static {
        Calendar cal = Calendar.getInstance();
        cal.set(1970, 0, 1, 0, 0, 0);
        MIN_DATE = cal.getTime();
        cal.set(9999, 0, 1, 0, 0, 0);
        MAX_DATE = cal.getTime();
    }

    /**
     * 获取本机IP
     */
    public static String getLocalHostIp() {
        return Optional.ofNullable(NetUtils.getLocalAddress()).map(InetAddress::getHostAddress)
                .orElse(null);
    }

    /**
     * 字符串替换， %s或{}占位
     *
     * @param format %s或{}占位
     */
    public static String formatString(String format, Object... args) {
        if (args == null || args.length == 0) {
            return format;
        }

        try {
            format = format.replaceAll("\\{}", "%s");
            return String.format(format, args);
        } catch (Exception ex) {
            // ignore
        }
        try {
            format = format.replaceAll("%s", "{}").replaceAll("%", "%%").replaceAll("\\{}", "%s");
            return String.format(format, args);
        } catch (Exception ignore) {

        }
        return format;
    }

    public static String md5(String source) {
        return DigestUtils.md5Hex(source);
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String bytesToHexString(byte[] bArray) {

        StringBuilder sb = new StringBuilder(bArray.length);
        String sTemp;
        for (byte aBArray : bArray) {
            sTemp = Integer.toHexString(0xFF & aBArray);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    public static String encodeURIComponent(String source) throws UnsupportedEncodingException {
        if (StringUtils.isBlank(source)) {
            return source;
        }
        String result = URLEncoder.encode(source, CODEC_DEFAULT_CHARSET);
        result = result.replaceAll("\\+", "%20").replaceAll("%21", "!").replaceAll("%27", "'")
                .replaceAll("%28", "(").replaceAll("%29", ")").replaceAll("%7E", "~");
        return result;
    }

    public static <T> List<T> subList(List<T> sourceList, int size) {
        return subList(sourceList, 0, size);
    }

    public static <T> List<T> subList(List<T> sourceList, int startIndex, int endIndex) {
        if (sourceList == null) {
            return null;
        }
        if (sourceList.size() == 0) {
            return new ArrayList<T>(0);
        }
        if (startIndex < 0) {
            return new ArrayList<T>(0);
        }
        if (startIndex > endIndex) {
            return new ArrayList<T>(0);
        }
        if (startIndex > sourceList.size()) {
            return new ArrayList<T>(0);
        }
        if (endIndex > sourceList.size()) {
            endIndex = sourceList.size();
        }
        return new ArrayList<>(sourceList.subList(startIndex, endIndex));
    }

    /**
     * 字符串截取
     */
    public static String shorten(String source, int size) {
        if (StringUtils.isBlank(source)) {
            return source;
        }
        if (source.length() <= size) {
            return source;
        }
        try {
            int endIndex = Math.max(size, source.length() - 200);
            return source.substring(0, size) + "......" + source.substring(endIndex, source.length());
        } catch (Exception ex) {
            return source;
        }
    }

    public static String toJSONString(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}