package com.guixiang.zhanjun.test;

import com.guixiang.zhanjun.dao.OrderItemDao;
import com.guixiang.zhanjun.dao.impl.OrderItemDaoImpl;
import com.guixiang.zhanjun.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;


public class OrderItemDaoImplTest {

    OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {
        int i = orderItemDao.saveOrderItem(new OrderItem(null, "测试", 2, new BigDecimal(20), new BigDecimal(40), "1213141415"));
        System.out.println(i);
    }

    @Test
    public void queryOrderItemByOrderId() {
        List<OrderItem> orderItems = orderItemDao.queryOrderItemByOrderId("1213141415");
        for (OrderItem orderItem : orderItems) {
            System.out.println(orderItem);
        }
    }
}