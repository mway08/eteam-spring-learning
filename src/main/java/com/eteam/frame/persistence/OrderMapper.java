package com.eteam.frame.persistence;

import com.eteam.frame.domain.Order;

import java.util.List;

public interface OrderMapper {

    List<Order> getOrdersByUsername(String username);

    Order getOrder(int orderId);

    int insertOrder(Order order);

    int insertOrderStatus(Order order);

}
