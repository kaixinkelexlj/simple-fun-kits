package com.simple.infra.common.client.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.apache.commons.lang3.StringUtils;

import com.simple.infra.common.client.support.dao.OrderBy;
import com.simple.infra.common.client.support.dao.OrderBy.OrderByDirection;

/**
 * @author xulujun
 * @date 2018/08/16
 */
public class DBUtils {

    public static List<OrderBy> parseOrderBy(String orderBy) throws SQLException {
        List<OrderBy> orderList = new ArrayList<>();
        if (StringUtils.isBlank(orderBy)) {
            return orderList;
        }
        try {

            List<String> orderParts = Stream.of(orderBy.split(","))
                    .filter(part -> StringUtils.isNotBlank(part.trim()))
                    .collect(Collectors.toList());

            for (String part : orderParts) {
                if (StringUtils.isBlank(part)) {
                    continue;
                }
                String[] arr = part.split("\\s");
                if (arr.length == 2) {
                    OrderByDirection direction = OrderByDirection.parse(arr[1].trim(), null);
                    if (direction == null) {
                        throw new SQLException("invalid direction:" + part);
                    }
                    orderList.add(new OrderBy(arr[0].trim(), direction));
                } else if (arr.length == 1) {
                    orderList.add(new OrderBy(arr[0].trim(), OrderByDirection.ASC));
                }
            }
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new SQLException("invalid order by:" + orderBy, ex);
        }

        return orderList;
    }

}
