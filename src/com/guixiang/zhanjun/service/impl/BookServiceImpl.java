package com.guixiang.zhanjun.service.impl;

import com.guixiang.zhanjun.dao.BookDao;
import com.guixiang.zhanjun.dao.impl.BookDaoImpl;
import com.guixiang.zhanjun.pojo.Book;
import com.guixiang.zhanjun.pojo.Page;
import com.guixiang.zhanjun.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBook(Integer id) {

        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {

        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {

        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    /**
     * 沒有篩選功能的分页
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();


        //设置每页显示的数量
        page.setPageSize(pageSize);

        //总记录数
        Integer pageTotalCount = bookDao.queryForTotalCount();
        page.setPageTotalCount(pageTotalCount);

        //总页码
        Integer pageTotal = (pageTotalCount / pageSize) + (pageTotalCount % pageSize > 0 ? 1 : 0);
        page.setPageTotal(pageTotal);

        //当前页码
        page.setPageNo(pageNo);

        //当前页面上显示的数据
        int begin = pageSize * (pageNo - 1);
        List<Book> items = bookDao.queryForPageItems(begin, pageSize);
        page.setItems(items);

        //当前页面

        return page;
    }

    /**
     * 有价格区间的分页
     * @param pageNo
     * @param pageSize
     * @param priceMin
     * @param priceMax
     * @return
     */
    @Override
    public Page<Book> page(int pageNo, int pageSize, int priceMin, int priceMax) {
        Page<Book> page = new Page<>();


        //设置每页显示的数量
        page.setPageSize(pageSize);

        //总记录数
        Integer pageTotalCount = bookDao.queryForTotalCountByPrice(priceMin, priceMax);
        page.setPageTotalCount(pageTotalCount);

        //总页码
        Integer pageTotal = (pageTotalCount / pageSize) + (pageTotalCount % pageSize > 0 ? 1 : 0);
        page.setPageTotal(pageTotal);

        //当前页码
        page.setPageNo(pageNo);

        //当前页面上显示的数据
        int begin = pageSize * (pageNo - 1);
        List<Book> items = bookDao.queryForPageItemsByPrice(begin, pageSize,priceMin,priceMax);
        page.setItems(items);

        //当前页面

        return page;
    }

}
