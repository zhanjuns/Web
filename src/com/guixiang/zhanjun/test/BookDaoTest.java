package com.guixiang.zhanjun.test;

import com.guixiang.zhanjun.dao.impl.BookDaoImpl;
import com.guixiang.zhanjun.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {

    private BookDaoImpl bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "中国最帅！", new BigDecimal(12142134), "占俊", 1212, 123, null));

    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(21);

    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21, "中国最帅呀！", new BigDecimal(121), "中国", 1212, null, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(21));

    }

    @Test
    public void queryBooks() {
        for (Book book : bookDao.queryBooks()) {
            System.out.println(book);
        }

    }

    @Test
    public void queryForTotalCount() {
        System.out.println(bookDao.queryForTotalCount());
    }

    @Test
    public void queryForPageItems() {
        List<Book> books = bookDao.queryForPageItems(4, 4);
        for (Book book:books){
            System.out.println(book);
        }
    }

}