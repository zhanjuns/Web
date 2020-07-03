package com.guixiang.zhanjun.service.impl;

import com.guixiang.zhanjun.dao.BookDao;
import com.guixiang.zhanjun.dao.OrderDao;
import com.guixiang.zhanjun.dao.OrderItemDao;
import com.guixiang.zhanjun.dao.impl.BookDaoImpl;
import com.guixiang.zhanjun.dao.impl.OrderDaoImpl;
import com.guixiang.zhanjun.dao.impl.OrderItemDaoImpl;
import com.guixiang.zhanjun.pojo.*;
import com.guixiang.zhanjun.service.OrderService;

import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    OrderDao orderDao = new OrderDaoImpl();
    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    BookDao bookDao = new BookDaoImpl();

    /**
     * 创建订单
     *
     * @param cart
     * @param userId
     */
    @Override
    public String createOrder(Cart cart, Integer userId) {
        //这个想法需要借鉴
        String orderId = System.currentTimeMillis() + "" + userId;
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), Order.NOT_SEND, userId);
        orderDao.saveOrder(order);

        //回滚事务测试
//        int a = 10 / 0;

        for (CartItem cartItem : cart.getItems().values()) {
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            orderItemDao.saveOrderItem(orderItem);
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setstock(book.getstock() - cartItem.getCount());
            bookDao.updateBook(book);
        }
        return orderId;
    }

    /**
     * 管理员查看所有订单信息
     *
     * @return
     */
    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryOrders();
    }

    /**
     * 发货
     *
     * @param orderId
     */
    @Override
    public void sendOrder(String orderId) {
        orderDao.changeOrderStatus(orderId, Order.SEND);

    }

    /**
     * 用户查看我的订单
     *
     * @param userId
     * @return
     */
    @Override
    public List<Order> showMyOrders(Integer userId) {
        return orderDao.queryOrdersByUserId(userId);
    }

    /**
     * 收货
     *
     * @param orderId
     */
    @Override
    public void receiveOrder(String orderId) {
        orderDao.changeOrderStatus(orderId, Order.RECEIVE);
    }

    /**
     * 通过订单ID查看详情
     *
     * @param orderId
     * @return
     */
    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemDao.queryOrderItemByOrderId(orderId);
    }

}
