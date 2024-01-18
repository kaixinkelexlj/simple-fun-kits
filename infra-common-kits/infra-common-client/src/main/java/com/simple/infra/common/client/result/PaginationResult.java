package com.simple.infra.common.client.result;

import java.util.List;

/**
 * @author xulujun
 * @date 2018/07/07
 */
public class PaginationResult<T> extends ListResult<T> {

  // 当前页
  private int pageNo;

  // 页面记录数
  private int pageSize;

  public PaginationResult() {
  }

  public PaginationResult(List<T> data, int pageNo, int pageSize, Integer count) {
    super(data, count);
    this.pageNo = pageNo;
    this.pageSize = pageSize;
  }

  public int getPageNo() {
    return pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
}
