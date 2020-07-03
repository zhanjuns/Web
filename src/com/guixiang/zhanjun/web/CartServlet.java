package com.guixiang.zhanjun.web;

import com.google.gson.Gson;
import com.guixiang.zhanjun.pojo.Book;
import com.guixiang.zhanjun.pojo.Cart;
import com.guixiang.zhanjun.pojo.CartItem;
import com.guixiang.zhanjun.service.BookService;
import com.guixiang.zhanjun.service.impl.BookServiceImpl;
import com.guixiang.zhanjun.utils.WebUtils;
import org.apache.commons.dbutils.DbUtils;

import javax.rmi.CORBA.Util;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {


    BookService bookService = new BookServiceImpl();

    /**
     * 添加购物车操作
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取加入到购物车的商品id
        int bookId = WebUtils.parseInt(request.getParameter("bookId"), 0);

        //获取当前的pageNo
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);

        //从bookService中查询该Id的商品
        Book book = bookService.queryBookById(bookId);

        //新建购物车商品
        CartItem cartItem;
        if (book != null) {
            cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        } else {
            cartItem = new CartItem(1, "不存在", 1, new BigDecimal(0), new BigDecimal(0));
        }

        //自Session域中得到cart
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //判断cart是否创建过
        if (cart == null) {
            //没有创建过就创建
            cart = new Cart();
            //添加到session域中
            request.getSession().setAttribute("cart", cart);
        }
        //将购物车商品加入到购物车中
        cart.addItem(cartItem);
//        Collection<CartItem> values = cart.getItems().values();

//        System.out.println(cart);
        //将购物车保存到session域当中
        request.getSession().setAttribute("cart", cart);
        //将刚加入的商品名保存到session域中，用来主页回显
        request.getSession().setAttribute("book", book.getName());

        //重定向到主页
//        response.sendRedirect(request.getContextPath() + "/client/clientBookServlet?action=page&pageNo=" + pageNo);
//        String referer = request.getHeader("Referer");
//        System.out.println(referer);
        response.sendRedirect(request.getHeader("Referer"));

    }

    /**
     * Ajax添加购物车操作
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void addItemForAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取加入到购物车的商品id
        int bookId = WebUtils.parseInt(request.getParameter("bookId"), 0);

        //获取当前的pageNo
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);

        //从bookService中查询该Id的商品
        Book book = bookService.queryBookById(bookId);

        //新建购物车商品
        CartItem cartItem;
        if (book != null) {
            cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        } else {
            cartItem = new CartItem(1, "不存在", 1, new BigDecimal(0), new BigDecimal(0));
        }

        //自Session域中得到cart
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //判断cart是否创建过
        if (cart == null) {
            //没有创建过就创建
            cart = new Cart();
            //添加到session域中
            request.getSession().setAttribute("cart", cart);
        }
        //将购物车商品加入到购物车中
        cart.addItem(cartItem);
//        Collection<CartItem> values = cart.getItems().values();

//        System.out.println(cart);
        //将购物车保存到session域当中
        request.getSession().setAttribute("cart", cart);
        //将刚加入的商品名保存到session域中，用来主页回显
        request.getSession().setAttribute("book", book.getName());

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalCount", cart.getTotalCount());
        resultMap.put("lastName", cartItem.getName());

        Gson gson = new Gson();
        String jsonString = gson.toJson(resultMap);

        response.setCharacterEncoding("utf-8");

        response.getWriter().write(jsonString);


    }




    /**
     * 清空购物车操作
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //自Session域中得到cart
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.clear();
//        req.getSession().setAttribute("cart", null);
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 删除选项操作
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //自Session域中得到cart
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //得到删除的ID
        int bookId = WebUtils.parseInt(req.getParameter("bookId"), 0);
        cart.deleteItem(bookId);
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 更新购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //自Session域中得到cart
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart.getItems()!=null){
            int id = WebUtils.parseInt(req.getParameter("id"), 0);
            int count = WebUtils.parseInt(req.getParameter("count"), 0);
            cart.updateItem(id, count);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}