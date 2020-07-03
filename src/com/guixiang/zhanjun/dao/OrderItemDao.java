package com.guixiang.zhanjun.dao;

import com.guixiang.zhanjun.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {

    int saveOrderItem(OrderItem orderItem);

    List<OrderItem> queryOrderItemByOrderId(String orderId);

}
