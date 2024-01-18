package com.simple.infra.common.client.utils;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TestUtils {

    private static final String SYS_OUT_PREFIX = "#################";

    public static String stringIt(Object obj) {
        if (obj == null) {
            return "<null>";
        }
        return ToStringBuilder.reflectionToString(obj, ToStringStyle.DEFAULT_STYLE);
    }

    public static void json(Object obj) {
        String result = "";
        try {
            if (obj == null) {
                result = "<null>";
                System.out.println(SYS_OUT_PREFIX + "!!\ncontent:" + result);
                return;
            }
            if (obj instanceof Collection<?>) {
                Collection<?> list = (Collection<?>) obj;
                if (list.size() == 0) {
                    result = "<empty>";
                } else {
                    result = CommonUtils.toJSONString(obj);
                }
                System.out.println(SYS_OUT_PREFIX + "!!size:" + list.size()
                        + "!!\ncontent:" + result);
            } else {
                result = CommonUtils.toJSONString(obj);
                System.out.println(SYS_OUT_PREFIX + "!!\ncontent:" + result);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }


    }

    public static void p(Object o) {
        if (o instanceof String) {
            System.out.println(o);
            return;
        }
        System.out.println(SYS_OUT_PREFIX + (o == null ? "<null>" : CommonUtils.toJSONString(o)));
    }

    public static void plist(Collection<?> list) {
        String result = "";
        if (list == null) {
            result = "<null>";
        } else if (list.size() == 0) {
            result = "<empty>";
        } else {
            result = CommonUtils.toJSONString(list);
        }
        System.out.println(
                SYS_OUT_PREFIX + "!!size:" + ((null == list) ? 0 : list.size()) + "!!\ncontent:" + result);
    }

    public static void ps(Object o) {
        if (o instanceof String) {
            System.out.println(o);
            return;
        }
        System.out.println(SYS_OUT_PREFIX + (o == null ? "<null>" : stringIt(o)));
    }

    public static void pslist(Collection<?> list) {
        String result = "";
        if (list == null) {
            result = "<null>";
        } else if (list.size() == 0) {
            result = "<empty>";
        } else {
            StringBuilder buff = new StringBuilder();
            for (Object obj : list) {
                buff.append(stringIt(obj)).append("\r\n");
            }
            result = buff.toString();
        }
        System.out.println(
                SYS_OUT_PREFIX + "!!size:" + ((null == list) ? 0 : list.size()) + "!!\ncontent:" + result);
    }

    public static void test(Object o, boolean successExpr) {
        p(o);
        if (!successExpr) {
            throw new RuntimeException("assert fail");
        }
    }

    protected List<Long> createIdList(int size) {
        if (size <= 0) {
            return new ArrayList<Long>(0);
        }
        List<Long> resultList = new ArrayList<Long>(size);
        for (int i = 0; i < size; i++) {
            resultList.add(RandomUtils.nextLong());
        }
        return resultList;
    }

    protected <T> List<T> createObjectList(int size, Class<T> clazz) {
        if (size <= 0) {
            return new ArrayList<T>(0);
        }
        List<T> resultList = new ArrayList<T>(size);
        for (int i = 0; i < size; i++) {
            try {
                resultList.add(createObject(clazz));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return resultList;
    }

    protected <T> T createObject(Class<T> clazz) throws Exception {

        T bean = clazz.newInstance();

        for (PropertyDescriptor descriptor : PropertyUtils.getPropertyDescriptors(clazz)) {
            if (PropertyUtils.isWriteable(bean, descriptor.getName())) {
                PropertyUtils.setProperty(bean, descriptor.getName(),
                        getRandomValue(descriptor.getName(), descriptor.getPropertyType()));
            }
        }

        return bean;
    }

    private Object getRandomValue(String fieldName, Class<?> propertyType) {
        if (propertyType == null) {
            return null;
        }
        // CHECKSTYLE:OFF
        int maxMockValue = 20000;
        // CHECKSTYLE:ON
        if (StringUtils.endsWithIgnoreCase(fieldName, "id") && Number.class
                .isAssignableFrom(propertyType)) {
            return System.nanoTime() * 100 + RandomUtils.nextInt(0, 100);
        }
        if (Long.class.equals(propertyType) || Long.TYPE.equals(propertyType)) {
            return (long) RandomUtils.nextInt(0, maxMockValue);
        }
        if (Integer.class.equals(propertyType) || Integer.TYPE.equals(propertyType)) {
            return RandomUtils.nextInt(0, maxMockValue);
        }
        if (String.class.equals(propertyType)) {
            return fieldName + "::" + RandomUtils.nextInt();
        }
        return null;
    }

}
