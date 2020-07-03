package com.guixiang.zhanjun.test;

import com.guixiang.zhanjun.dao.OrderDao;
import com.guixiang.zhanjun.dao.impl.OrderDaoImpl;
import com.guixiang.zhanjun.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoImplTest {

    private OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void saveOrder() {
        int i = orderDao.saveOrder(new Order("1241415", new Date(2521244121234L), new BigDecimal(2), 0, 1));
        System.out.println(i);
    }

    @Test
    public void queryOrders() {
        List<Order> orders = orderDao.queryOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void changeOrderStatus() {
        int i = orderDao.changeOrderStatus("1213141415", 1);
        System.out.println(i);
    }

    @Test
    public void queryOrdersByUserId() {
        List<Order> orders = orderDao.queryOrdersByUserId(1);
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}