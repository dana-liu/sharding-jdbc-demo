package com.lww.sharding.entity;/**
 * @author wu
 * @date 2025/8/19
 */

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-19  16:01
 * @Version: 1.0
 */
public class Order {
    private int id;
    private Long orderNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }
}
