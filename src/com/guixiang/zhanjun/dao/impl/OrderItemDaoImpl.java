package com.guixiang.zhanjun.dao.impl;

import com.guixiang.zhanjun.dao.OrderItemDao;
import com.guixiang.zhanjun.pojo.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {

    /**
     * 用户保存订单项
     *
     * @param orderItem
     */
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`id` , `name` , `count` , `price` , `total_price` , `order_id`) values(?,?,?,?,?,?)";
        return update(sql, orderItem.getId(), orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
    }

    /**
     * 用户通过订单号查询订单项
     *
     * @param orderId
     * @return
     */
    @Override
    public List<OrderItem> queryOrderItemByOrderId(String orderId) {
        String sql = "select `id` , `name` , `count` , `price` , `total_price` totalPrice , `order_id`orderId from t_order_item where `order_id` = ? ";
        return queryForList(OrderItem.class, sql, orderId);
    }
}
