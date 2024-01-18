package com.simple.infra.common.client.result;

import java.util.List;

/**
 * @author xulujun
 * @date 2018/07/06
 */
public class ListResult<T> extends SimpleResult<List<T>> {

  private static final long serialVersionUID = -5882467973865235396L;

  private Integer count;

  public ListResult() {
  }

  public ListResult(List<T> data) {
    this(data, null);
  }

  public ListResult(List<T> data, Integer count) {
    super(data);
    this.count = count != null ? count : data == null ? 0 : data.size();
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

}
