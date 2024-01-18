package com.simple.infra.common.client.support.dao;

import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;

/**
 * @author xulujun
 * @date 2018/7/16
 */
public class OrderBy implements Serializable {

  private static final long serialVersionUID = 6264591769492929501L;
  private String column;
  private OrderByDirection direction;

  public OrderBy(String column, OrderByDirection direction) {
    this.column = column;
    this.direction = direction;
  }

  public static OrderBy create(String column, OrderByDirection direction) {
    return new OrderBy(column, direction);
  }

  public String getColumn() {
    return column;
  }

  public void setColumn(String column) {
    this.column = column;
  }

  public OrderByDirection getDirection() {
    return direction;
  }

  public void setDirection(OrderByDirection direction) {
    this.direction = direction;
  }

  public static enum OrderByDirection {

    ASC, DESC;

    public static OrderByDirection parse(String direction) {
      return parse(direction, ASC);
    }

    public static OrderByDirection parse(String direction, OrderByDirection defaultValue) {
      if (direction == null || StringUtils.isBlank(direction)) {
        return defaultValue;
      }
      for (OrderByDirection val : OrderByDirection.values()) {
        if (val.name().equalsIgnoreCase(direction)) {
          return val;
        }
      }
      return defaultValue;
    }
  }


}