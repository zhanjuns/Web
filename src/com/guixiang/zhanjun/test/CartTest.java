package com.guixiang.zhanjun.test;

import com.guixiang.zhanjun.pojo.Cart;
import com.guixiang.zhanjun.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

public class CartTest {
    private Cart cart = new Cart();
    @Test
    public void addItem() {


        cart.addItem(new CartItem(1, "python从入门到放弃", 1, new BigDecimal(100), new BigDecimal(100)));
        cart.addItem(new CartItem(1, "python从入门到放弃", 1, new BigDecimal(100), new BigDecimal(100)));
        cart.addItem(new CartItem(2, "数据结构", 1, new BigDecimal(1000), new BigDecimal(1000)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        addItem();
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        addItem();
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateItem() {
        addItem();
        cart.updateItem(2, 3);
        System.out.println(cart);
    }

}