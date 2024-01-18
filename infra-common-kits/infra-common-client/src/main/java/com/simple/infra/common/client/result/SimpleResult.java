package com.simple.infra.common.client.result;

/**
 * @author xulujun
 * @date 2018/07/06
 */
public class SimpleResult<T> extends BaseResult {

  private static final long serialVersionUID = 5658582567040336325L;

  private T data;

  public SimpleResult() {

  }

  public SimpleResult(T data) {
    super();
    this.data = data;
  }

  public static <T> SimpleResult<T> success(T data) {
    return new SimpleResult<T>(data);
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public boolean isDataPresent() {
    return data != null;
  }

}
