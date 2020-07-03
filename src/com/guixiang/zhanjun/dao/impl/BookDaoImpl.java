package com.guixiang.zhanjun.dao.impl;

import com.guixiang.zhanjun.dao.BookDao;
import com.guixiang.zhanjun.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {

    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(`name` , `author` , `price` , `sales` , `stock` , `img_path`) values(?,?,?,?,?,?)";
        return update(sql, book.getName(), book.getauthor(), book.getPrice(), book.getSales(), book.getstock(), book.getImgPath());
    }

    @Override
    public int deleteBookById(int id) {
        String sql = "delete from t_book where id = ?";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name`=?, `author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id = ?";
        return update(sql, book.getName(), book.getauthor(), book.getPrice(), book.getSales(), book.getstock(), book.getImgPath(), book.getId());
    }

    @Override
    public Book queryBookById(int id) {
        String sql = "select `id`,`name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book where id = ?";
        return queryForOne(Book.class, sql, id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select `id`,`name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book";
        return queryForList(Book.class, sql);
    }

    @Override
    public Integer queryForTotalCount() {
        String sql = "select count(*) from t_book where stock > 0";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select `id`,`name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book where stock > 0 limit ? , ?";
        return queryForList(Book.class, sql, begin, pageSize);
    }

    @Override
    public Integer queryForTotalCountByPrice(int priceMin, int priceMax) {
        String sql = "select count(*) from t_book where price > ? and price < ? and stock > 0";
        Number countByPrice = (Number) queryForSingleValue(sql, priceMin, priceMax);
        return countByPrice.intValue();
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int priceMin, int priceMax) {
        String sql = "select `id`,`name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book where price > ? and price < ? and stock>0 order by price limit ? , ?";
        return queryForList(Book.class, sql, priceMin, priceMax, begin, pageSize);
    }
}
