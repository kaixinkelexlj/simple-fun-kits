package com.simple.infra.common.client.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import com.simple.infra.common.client.lang.Assert;

/**
 * @author xulujun
 * @date 2018/08/28
 */
public interface DateTimeUtils {

  String SIMPLE_DATE_PATTERN = "yyyyMMdd";
  String SHORT_DATE_PATTERN = "yyyy-MM-dd";
  String CHINESE_DATE_PATTERN = "yyyy年MM月dd日";

  String SHORT_DATE_TIME_PATTERN = SHORT_DATE_PATTERN + " HH:mm:ss";
  String SHORT_DATE_TIME_WITH_MS_PATTERN = SHORT_DATE_PATTERN + " HH:mm:ss.SSS";
  String CHINESE_DATE_TIME_PATTERN = CHINESE_DATE_PATTERN + "HH时mm分ss秒";

  /**
   * yyyyMMdd
   *
   * @return dateString
   */
  static String formatDate(Date date) {
    return format(date, SIMPLE_DATE_PATTERN);
  }

  /**
   * yyyyMMdd
   *
   * @return date
   */
  static Date parseDate(String dateString) {
    return parse(dateString, SIMPLE_DATE_PATTERN);
  }

  /**
   * yyyy-MM-dd HH:mm:ss
   *
   * @return dateString
   */
  static String formatDateTime(Date date) {
    return format(date, SHORT_DATE_TIME_PATTERN);
  }

  /**
   * yyyy-MM-dd HH:mm:ss
   *
   * @return date
   */
  static Date parseDateTime(String dateString) {
    return parse(dateString, SHORT_DATE_TIME_PATTERN);
  }

  static String format(Date date, String pattern) {
    Assert.notNull(pattern);
    Assert.notNull(date);
    return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault())
        .format(DateTimeFormatter.ofPattern(pattern));
  }

  static Date parse(String dateString, String pattern) {
    Assert.notNull(pattern);
    Assert.notNull(dateString);
    try {
      return new SimpleDateFormat(pattern).parse(dateString);
    } catch (ParseException e) {
      throw new RuntimeException("invalid pattern:" + pattern, e);
    }
  }

  static Date calDate(Date source, int field, int amount) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(source);
    cal.add(field, amount);
    return cal.getTime();
  }

  static Date setTime(Date source, int hour, int minute, int second) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(source);
    cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), hour, minute,
        second);
    return cal.getTime();
  }

}
