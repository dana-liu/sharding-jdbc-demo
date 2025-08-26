package com.lww.sharding.order.service;/**
 * @author wu
 * @date 2025/8/19
 */


import com.lww.sharding.order.entity.Order;

import java.util.List;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-19  11:27
 * @Version: 1.0
 */
public interface OrderService {
    void save(Order order);

    List<Order> list();

    List<Order> page(int pageNum, int pageSize);
}
