package com.simple.infra.common.client.utils;


import com.simple.infra.common.client.lang.ToString;

/**
 * @author xulujun
 * @date 2018/7/7
 */
// CHECKSTYLE:OFF
public class Pager extends ToString {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 4874060246810541805L;

  public static Integer DEFAULT_PAGE_NO = 1;
  public static Integer DEFAULT_PAGE_SIZE = 0;

  private int pageNo = DEFAULT_PAGE_NO;               //1开始
  private int pageSize = DEFAULT_PAGE_SIZE;              //默认20
  private long totalCount = 0;
  private long totalPage = 0;

  public static final Pager ALL = new Pager(1, Integer.MAX_VALUE);

  public Pager() {

  }

  public Pager(int pageNo, int pageSize) {
    if (pageNo <= 0) {
      pageNo = DEFAULT_PAGE_NO;
    }
    this.pageNo = pageNo;
    this.pageSize = pageSize;
  }

  public Integer getPageNo() {
    return pageNo < 1 ? DEFAULT_PAGE_NO : pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  public void setTotalCount(long total) {
    this.totalCount = total;
    this.totalPage = totalCount % getPageSize() == 0 ? totalCount / getPageSize()
        : (totalCount / getPageSize() + 1);
  }

  public long getTotalCount() {
    return totalCount;
  }

  public long getTotalPage() {
    return totalPage;
  }

  public int getPageSize() {
    return pageSize < 1 ? DEFAULT_PAGE_SIZE : pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getStart() {
    return (getPageNo() - 1) * getPageSize();
  }

  public int getEnd() {
    return getPageNo() * getPageSize();
  }

  public void next() {
    pageNo += 1;
  }

  public boolean hasNext() {
    if (totalCount == 0) {
      return false;
    }
    return pageNo < totalPage;
  }

  public boolean validate() {
    return this.getPageNo() != null && this.getPageNo() >= 0 && this.getPageSize() > 0;
  }
}