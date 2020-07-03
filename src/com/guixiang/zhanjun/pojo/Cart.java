package com.guixiang.zhanjun.pojo;


import com.guixiang.zhanjun.dao.BookDao;
import com.guixiang.zhanjun.dao.impl.BookDaoImpl;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车对象
 */
public class Cart {
    private Integer totalCount;
    private BigDecimal totalPrice;
    private Map<Integer, CartItem> items = new LinkedHashMap<>();

    private BookDao bookDao = new BookDaoImpl();


    public Cart() {
    }

    public Cart(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }


    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }
    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items + "," +
                "totalCount=" + getTotalCount() + "," +
                "totalPrice=" + getTotalPrice() +
                '}';
    }


    /**
     * 向购物车添加商品项
     *
     * @param cartItem
     */
    public void addItem(CartItem cartItem) {
        //首先判断购物车中是否存在该商品项，存在改数量，不存在添加
        CartItem item = items.get(cartItem.getId());
        if (item == null) {
            //不存在---添加操作
            items.put(cartItem.getId(), cartItem);
        } else {
            //存在，数量累加操作
            //获取该商品
            Book book = bookDao.queryBookById(cartItem.getId());
            //判断商品库存------如果库存不足就没有修改默默返回----------此处没有向前端返回信息~~~~~~~~~~~~~~~~~~~
            if (cartItem.getId() > book.getstock()) {
                return;
            }
            //数量累加
            item.setCount(item.getCount() + 1);
            //总价更新
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * 删除购物车中商品
     *
     * @param id
     */
    public void deleteItem(Integer id) {
        if (items.get(id) != null) {
            items.remove(id);
        }
    }

    /**
     * 清空购物车
     */
    public void clear() {
        items.clear();
    }

    /**
     * 修改商品数量
     */
    public void updateItem(Integer id, Integer count) {
        //首先判断购物车中是否存在该商品项
        CartItem item = items.get(id);
        if (item != null) {
            //获取该商品
            Book book = bookDao.queryBookById(id);
            //判断商品库存------如果库存不足就没有修改默默返回----------此处没有向前端返回信息~~~~~~~~~~~~~~~~~~~
            if (count > book.getstock()) {
                return;
            }
            //数量累加
            item.setCount(count);
            //总价更新
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * 获取购物车中总的商品数量
     *
     * @return
     */
    public Integer getTotalCount() {
        totalCount = 0;
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            //数量累加
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }


    /**
     * 获取总金额
     *
     * @return
     */
    public BigDecimal getTotalPrice() {
        totalPrice = BigDecimal.valueOf(0);

        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            //总价累加
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }

        return totalPrice;
    }

}
