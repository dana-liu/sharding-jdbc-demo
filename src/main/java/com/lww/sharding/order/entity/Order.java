package com.lww.sharding.order.entity;/**
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
    private Long number;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
