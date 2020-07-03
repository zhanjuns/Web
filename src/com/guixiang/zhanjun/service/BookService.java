package com.guixiang.zhanjun.service;

import com.guixiang.zhanjun.pojo.Book;
import com.guixiang.zhanjun.pojo.Page;

import java.util.List;

public interface BookService {

    void addBook(Book book);

    void deleteBook(Integer id);

    void updateBook(Book book);

    Book queryBookById(Integer id);

    List<Book> queryBooks();

    Page<Book> page(int pageNo, int pageSize);

    Page<Book> page(int pageNo, int pageSize, int priceMin, int priceMax);
}
