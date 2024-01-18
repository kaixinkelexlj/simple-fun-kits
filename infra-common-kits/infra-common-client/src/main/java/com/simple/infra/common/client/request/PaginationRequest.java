package com.simple.infra.common.client.request;


import com.simple.infra.common.client.utils.Pager;

/**
 * @author xulujun
 * @date 2018/7/7
 */
public class PaginationRequest extends BaseRequest {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 2720301906917093397L;

  private static final int DEFAULT_PAGE_NO = Pager.DEFAULT_PAGE_NO;
  private static final int DEFAULT_PAGE_SIZE = Pager.DEFAULT_PAGE_SIZE;

  // 当前页
  private int pageNo = DEFAULT_PAGE_NO;

  // 页面记录数
  private int pageSize = DEFAULT_PAGE_SIZE;

  public int getPageNo() {
    return pageNo <= 0 ? DEFAULT_PAGE_NO : pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  public int getPageSize() {
    return pageSize <= 0 ? DEFAULT_PAGE_SIZE : pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public Pager getPager() {
    Pager pager = new Pager();
    pager.setPageNo(pageNo);
    pager.setPageSize(pageSize);
    return pager;
  }
}