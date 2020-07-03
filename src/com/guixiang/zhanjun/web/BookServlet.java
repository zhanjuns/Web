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
import java.math.BigDecimal;
import java.util.List;

public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 添加book
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求的参数----封装成为book对象

//        String name = request.getParameter("name");
//        String price = request.getParameter("price");
//        String author = request.getParameter("author");
//        String sales = request.getParameter("sales");
//        String stock = request.getParameter("stock");


        Book book = WebUtils.copyPararmToBean(request.getParameterMap(), new Book());
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);

        //调用bookService。addBook()保存图书
        bookService.addBook(book);

        //跳到图书列表页面
        //   ---/manager/bookServlet?action=list
        //request.getRequestDispatcher("/manager/bookServlet?action=list").forward(request, response);
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo + 1);

    }

    /**
     * 删除book
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取请求的参数id
        int id = WebUtils.parseInt(request.getParameter("id"), 0);

        //调用bookService，deleteBookById()删除
        bookService.deleteBook(id);
        //重定向~到列表页
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo="+request.getParameter("pageNo"));


    }

    /**
     * 修改book
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = WebUtils.copyPararmToBean(request.getParameterMap(), new Book());
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        book.setId(id);
        bookService.updateBook(book);
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo="+request.getParameter("pageNo"));
    }

    /**
     * 分页功能出来，不用该功能了
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.通过bookService 查询所有数据
        List<Book> books = bookService.queryBooks();

        //2.将数据保存到request域中
        request.setAttribute("books", books);
        //3.请求转发到pages/manager/book_manager下
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }


    /**
     * 由id获取图书内容
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取图书id
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        //由bookService查询信息
        Book book = bookService.queryBookById(id);
        //将查询到的信息保存到request域当中
        request.setAttribute("book", book);
        //请求转发
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
    }

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

        //后台分页的url
        page.setUrl("manager/bookServlet?action=page");

        //3、保存page对象到request域中
        request.setAttribute("page", page);
        //4、请求转发到pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);

    }


}
