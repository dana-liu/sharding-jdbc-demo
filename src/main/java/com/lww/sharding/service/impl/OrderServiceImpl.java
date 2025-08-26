package com.lww.sharding.service.impl;/**
 * @author wu
 * @date 2025/8/19
 */

import com.lww.sharding.entity.Order;
import com.lww.sharding.entity.User;
import com.lww.sharding.mapper.OrderMapper;
import com.lww.sharding.mapper.UserMapper;
import com.lww.sharding.service.OrderService;
import com.lww.sharding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-19  11:28
 * @Version: 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void save(Order order) {
        orderMapper.save( order);
    }

    @Override
    public List<Order> list() {
        return orderMapper.list();
    }
}
