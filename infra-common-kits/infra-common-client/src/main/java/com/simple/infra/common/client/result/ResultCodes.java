package com.simple.infra.common.client.result;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.simple.infra.common.client.utils.CommonUtils;

/**
 * @author xulujun
 * @date 2018/07/13
 */
public abstract class ResultCodes {

  private static final Map<String, ResultCode> CODE_MAP = new ConcurrentHashMap<>();

  //*********************
  /**
   * 全局错误码小于1000
   **/
  //*********************

  //**************************************************************
  public static final int INVALID_CODE_NUMER = -99999999;
  public static final ResultCode INVALID = create(String.valueOf(INVALID_CODE_NUMER), "INVALID",
      "invalid result code");
  public static final ResultCode SUCCESS = create("200", "SUCCESS", "SUCCESS", "请求成功");
  public static final ResultCode EXCEPTION = create("-100", "EXCEPTION",
      "Server Error", "请求异常");
  public static final ResultCode ERROR = create("-101", "ERROR", "Server Error",
      "服务内部错误");
  public static final ResultCode ERROR_DB = create("-102", "ERROR_DB", "DB Error",
      "请求数据库错误");
  public static final ResultCode REMOTE_SERVICE_ERROR = create("-103",
      "REMOTE_SERVICE_ERROR",
      "Remote Service Error", "请求远程服务错误");
  public static final ResultCode ILLEGAL_PARAM = create("-110", "ILLEGAL_PARAM",
      "ILLEGAL_PARAM", "参数非法");
  public static final ResultCode DATA_NOT_EXIST = create("-111", "DATA_NOT_EXIST",
      "data not exist", "对象不存在");
  //**************************************************************


  /**
   * 自定义结果码
   * <li>全局错误码小于1000</li>
   * <li>自定义错误码大于1000</li>
   *
   * @param code 错误码
   * @param name 错误码name
   * @param message 错误消息，用于日志，如果写null，等于name
   * @param displayMessage 错误消息-提示用户
   */
  public static ResultCode registerCustom(String code, String name, String message,
      String displayMessage) {
    return create(code, name, message, displayMessage);
  }

  public static ResultCode registerCustom(Number code, String name, String message,
      String displayMessage) {
    return create(code.toString(), name, message, displayMessage);
  }

  /**
   * @see ResultCodes#registerCustom(String, String, String, String)
   */
  public static ResultCode registerCustom(String code, String name, String message) {
    return registerCustom(code, name, message, null);
  }

  /**
   * @see ResultCodes#registerCustom(String, String, String, String)
   */
  public static ResultCode registerCustom(String code, String name) {
    return registerCustom(code, name, name, null);
  }

  public static ResultCode codeOf(String code) {
    return CODE_MAP.getOrDefault(code, INVALID);
  }

  public static ResultCode nameOf(String name) {
    return CODE_MAP.values().stream()
        .filter(code -> code.getName().equals(name))
        .findFirst().orElse(INVALID);
  }

  private static ResultCode create(String code, String name, String message,
      String displayMessage) {
    if (CODE_MAP.get(code) != null) {
      throw new IllegalStateException(CommonUtils.formatString("code[{}]exists already"));
    }
    ResultCode resultCode = new ResultCodeModel(code, name,
        Optional.ofNullable(message).orElse(name), displayMessage);
    CODE_MAP.put(code, resultCode);
    return resultCode;
  }

  private static ResultCode create(String code, String name, String message) {
    return create(code, name, message, null);
  }

  /**
   * 可被继承扩展
   */
  public static class ResultCodeModel implements ResultCode {

    private String code;
    private String name;
    private String message;
    private String displayMessage;

    public ResultCodeModel(String code, String name, String message) {
      this(code, name, message, null);
    }

    public ResultCodeModel(String code, String name, String message, String displayMessage) {
      this.name = name;
      this.code = code;
      this.message = message;
      this.displayMessage = displayMessage;
    }

    @Override
    public String getCode() {
      return code;
    }

    @Override
    public String getName() {
      return name;
    }

    @Override
    public String getMessage() {
      return message;
    }

    @Override
    public String getDisplayMessage() {
      return displayMessage;
    }

    @Override
    public String toString() {
      return this.name;
    }
  }

}
