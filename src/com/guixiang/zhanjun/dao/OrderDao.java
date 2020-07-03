package com.guixiang.zhanjun.dao;

import com.guixiang.zhanjun.pojo.Order;

import java.util.List;

public interface OrderDao {

    int saveOrder(Order order);

    List<Order> queryOrders();

    int changeOrderStatus(String orderId, int status);

    List<Order> queryOrdersByUserId(int userId);
}
