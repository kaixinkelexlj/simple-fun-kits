package com.simple.infra.common.client.support.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.simple.infra.common.client.model.BaseDO;
import com.simple.infra.common.client.support.dao.OrderBy.OrderByDirection;
import com.simple.infra.common.client.utils.Pager;
import org.apache.commons.lang3.StringUtils;

/**
 * @author xulujun
 * @date 2018/7/16
 */
public abstract class BaseQuery extends BaseDO {

  private static final long serialVersionUID = 8173981514337501662L;

  private List<OrderBy> sortList;

  private Pager pager;

  public List<OrderBy> getSortList() {
    return sortList;
  }

  public void setSortList(List<OrderBy> sortList) {
    this.sortList = sortList;
  }

  public Pager getPager() {
    return pager;
  }

  public void setPager(Pager pager) {
    this.pager = pager;
  }

  public static PagerBuilder newPagerBuilder() {
    return new PagerBuilder();
  }

  public static SortBuilder newSortBuilder() {
    return new SortBuilder();
  }

  public int getPageStart() {
    return Optional.ofNullable(pager).map(Pager::getStart).orElse(0);
  }

  public int getPageSize() {
    return Optional.ofNullable(pager).map(Pager::getPageSize).orElse(Pager.DEFAULT_PAGE_SIZE);
  }

  /**
   * @author xulujun
   * @date 2018/7/16
   */
  public static class SortBuilder {

    private List<OrderBy> sortList = new ArrayList<>();

    public SortBuilder() {

    }

    public SortBuilder(OrderBy... orderByList) {
      if (orderByList != null && orderByList.length > 0) {
        this.sortList = new ArrayList<>(Arrays.asList(orderByList));
      }
    }

    public static SortBuilder start() {
      return new SortBuilder();
    }

    public static List<OrderBy> build(OrderBy... orderByList) {
      return new SortBuilder(orderByList).build();
    }

    public SortBuilder append(OrderBy orderBy) {
      if (orderBy == null) {
        return this;
      }
      this.sortList.add(orderBy);
      return this;
    }

    public SortBuilder append(String fieldName, OrderByDirection direction) {
      if (StringUtils.isBlank(fieldName) || direction == null) {
        return this;
      }
      return append(OrderBy.create(fieldName, direction));
    }

    public List<OrderBy> build() {
      return sortList;
    }

  }

  public static class PagerBuilder {

    private Pager pager;

    public PagerBuilder() {
    }

    public static PagerBuilder start() {
      return new PagerBuilder();
    }

    public PagerBuilder ofPageNo(Integer pageNo) {
      pager.setPageNo(Optional.ofNullable(pageNo).orElse(Pager.DEFAULT_PAGE_NO));
      return this;
    }

    public PagerBuilder ofPageSize(Integer pageSize) {
      pager.setPageSize(Optional.ofNullable(pageSize).orElse(Pager.DEFAULT_PAGE_SIZE));
      return this;
    }

    public static Pager of(Integer pageNo, Integer pageSize) {
      return new Pager(pageNo, pageSize);
    }

    public Pager build() {
      return pager;
    }

    public Pager getPager() {
      return pager;
    }

    public void setPager(Pager pager) {
      this.pager = pager;
    }

  }
}