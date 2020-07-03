package com.guixiang.zhanjun.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    //未发货
    public static final int NOT_SEND = 0;
    //已发货
    public static final int SEND = 1;
    //已签收
    public static final int RECEIVE = 2;

    private String orderId;
    private Date createTime;
    private BigDecimal price;
    private Integer status = NOT_SEND;
    private Integer userId;

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", createTime=" + createTime +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Order(String orderId, Date createTime, BigDecimal price, Integer status, Integer userId) {

        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    public Order() {

    }
}
