package com.guixiang.zhanjun.service;

import com.guixiang.zhanjun.pojo.Cart;
import com.guixiang.zhanjun.pojo.Order;
import com.guixiang.zhanjun.pojo.OrderItem;

import java.util.List;

public interface OrderService {


    String createOrder(Cart cart, Integer userId);

    List<Order> showAllOrders();

    void sendOrder(String orderId);

    List<Order> showMyOrders(Integer userId);

    void receiveOrder(String orderId);

    List<OrderItem> showOrderDetail(String orderId);

}
