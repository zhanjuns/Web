package com.guixiang.zhanjun.web;

import com.guixiang.zhanjun.pojo.Cart;
import com.guixiang.zhanjun.pojo.Order;
import com.guixiang.zhanjun.service.BookService;
import com.guixiang.zhanjun.service.OrderService;
import com.guixiang.zhanjun.service.impl.OrderServiceImpl;
import com.guixiang.zhanjun.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet {

    OrderService orderService = new OrderServiceImpl();


    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //从session域中获取购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //从parameter中获取用户ID
        Integer userId = WebUtils.parseInt(request.getParameter("userId"), 0);

        //创建订单并且返回订单号
        String orderId = "";
        if (cart.getItems()!=null){
            orderId = orderService.createOrder(cart, userId);
        }
        //将订单号存放在session域中
        request.getSession().setAttribute("orderId", orderId);
        //清空购物车
        cart.clear();
        //重定向回checkout.jsp
        response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");
    }

    protected void myOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //从parameter中获取用户ID
        Integer userId = WebUtils.parseInt(req.getParameter("userId"), 0);

        //从orderService中获取我的订单信息
        List<Order> orders = orderService.showMyOrders(userId);

        //将我的订单信息保存到request域中
        req.setAttribute("myOrders", orders);
        //请求转发到order.jsp中
        req.getRequestDispatcher( "/pages/order/order.jsp").forward(req, resp);

    }
}
