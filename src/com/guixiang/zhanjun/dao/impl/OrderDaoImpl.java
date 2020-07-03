package com.guixiang.zhanjun.dao.impl;

import com.guixiang.zhanjun.dao.OrderDao;
import com.guixiang.zhanjun.pojo.Order;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {


    /**
     * 保存订单
     *
     * @param order
     */
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id` , `create_time` , `price` , `status` , `user_id` ) values(?,?,?,?,?)";
        return update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
    }

    /**
     * 管理员查询所有订单
     *
     * @return
     */
    @Override
    public List<Order> queryOrders() {
        String sql = "select `order_id`orderId , `create_time`createTime , `price` , `status` , `user_id`userId from t_order";
        return queryForList(Order.class, sql);
    }

    /**
     * 管理员或用户修改订单状态
     *
     * @param orderId
     * @param status
     */
    @Override
    public int changeOrderStatus(String orderId, int status) {
        String sql = "update t_order set `status` = ? where `order_id` = ?";
        return update(sql, status, orderId);
    }

    /**
     * 用户通过ID查找我的订单
     *
     * @param userId
     * @return
     */
    @Override
    public List<Order> queryOrdersByUserId(int userId) {
        String sql = "select `order_id`orderId , `create_time`createTime , `price` , `status` , `user_id`userId from t_order where `user_id` = ? ";
        return queryForList(Order.class, sql, userId);
    }
}
