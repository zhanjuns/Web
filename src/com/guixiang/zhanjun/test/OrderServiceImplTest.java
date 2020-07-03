package com.guixiang.zhanjun.test;

import com.guixiang.zhanjun.pojo.Cart;
import com.guixiang.zhanjun.pojo.CartItem;
import com.guixiang.zhanjun.pojo.Order;
import com.guixiang.zhanjun.pojo.OrderItem;
import com.guixiang.zhanjun.service.OrderService;
import com.guixiang.zhanjun.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class OrderServiceImplTest {

    OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder() {
        Map<Integer, CartItem> items = new LinkedHashMap<>();
        items.put(1, new CartItem(2, "javaScript从入门到放弃", 1, new BigDecimal(100), new BigDecimal(100)));
        orderService.createOrder(new Cart(items), 1);

    }

    @Test
    public void showAllOrders() {
        List<Order> orders = orderService.showAllOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void sendOrder() {
        orderService.sendOrder("15936699032641");
    }

    @Test
    public void showMyOrders() {
        List<Order> orders = orderService.showMyOrders(1);
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void receiveOrder() {
        orderService.receiveOrder("15936699032641");
    }

    @Test
    public void showOrderDetail() {
        List<OrderItem> orderItems = orderService.showOrderDetail("15936699032641");
        for (OrderItem orderItem : orderItems) {
            System.out.println(orderItem);
        }
    }
}