package com.guixiang.zhanjun.dao;


import com.guixiang.zhanjun.pojo.Book;

import java.util.List;

public interface BookDao {

    int addBook(Book book);

    int deleteBookById(int id);

    int updateBook(Book book);

    Book queryBookById(int id);

    List<Book> queryBooks();

    Integer queryForTotalCount();

    List<Book> queryForPageItems(int begin, int pageSize);

    Integer queryForTotalCountByPrice(int priceMin, int priceMax);

    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int priceMin, int priceMax);
}
