package com.guixiang.zhanjun.test;

import com.guixiang.zhanjun.dao.BookDao;
import com.guixiang.zhanjun.dao.impl.BookDaoImpl;
import com.guixiang.zhanjun.pojo.Book;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookDaoImplTest {

    BookDao bookDao = new BookDaoImpl();

    @Test
    public void queryForTotalCountByPrice() {
        System.out.println(bookDao.queryForTotalCountByPrice(0, 100));
    }

    @Test
    public void queryForPageItemsByPrice() {
        List<Book> books = bookDao.queryForPageItemsByPrice(1, 4, 0, 100);
        for (Book book:books) {
            System.out.println(book);
        }
    }
}