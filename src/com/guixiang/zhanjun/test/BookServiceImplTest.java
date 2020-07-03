package com.guixiang.zhanjun.test;

import com.guixiang.zhanjun.pojo.Book;
import com.guixiang.zhanjun.service.BookService;
import com.guixiang.zhanjun.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookServiceImplTest {

    BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "中国最帅！", new BigDecimal(12142134), "占俊", 1212, 123, null));
    }

    @Test
    public void deleteBook() {
        bookService.deleteBook(22);

    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22, "中国最帅ya", new BigDecimal(124), "占俊", 1212, 123, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(22));
    }

    @Test
    public void queryBooks() {
        for (Book book : bookService.queryBooks()) {
            System.out.println(book);
        }
    }
}