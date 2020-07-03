package com.guixiang.zhanjun.web;

import com.guixiang.zhanjun.pojo.Book;
import com.guixiang.zhanjun.pojo.Page;
import com.guixiang.zhanjun.service.BookService;
import com.guixiang.zhanjun.service.impl.BookServiceImpl;
import com.guixiang.zhanjun.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet {


    private BookService bookService = new BookServiceImpl();


    /**
     * 分页功能
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //1、获取请求的参数---pageNo,pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);

        //2、调用bookService.page(pageNo,pageSize)---page对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        //前台分页的url
        page.setUrl("client/clientBookServlet?action=page");

        //3、保存page对象到request域中
        request.setAttribute("page", page);
        //4、请求转发到pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);

    }

    /**
     * 按照价格排分页功能
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //1、获取请求的参数---min,max,pageNo,pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        int priceMin = WebUtils.parseInt(request.getParameter("min"), 0);
        int priceMax = WebUtils.parseInt(request.getParameter("max"), Integer.MAX_VALUE);

        //2、调用bookService.page(pageNo,pageSize)---page对象
        Page<Book> page = bookService.page(pageNo, pageSize, priceMin, priceMax);

        //前台筛选分页的url
        page.setUrl("client/clientBookServlet?action=pageByPrice&min=" + priceMin + "&max=" + priceMax);

        //3、保存page对象到request域中
        request.setAttribute("page", page);
        //4、请求转发到pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);

    }
}
