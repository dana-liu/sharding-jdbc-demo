package com.lww.sharding.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lww.sharding.order.entity.Order;
import com.lww.sharding.order.mapper.OrderMapper;
import com.lww.sharding.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-19  11:28
 * @Version: 1.0
 */
@Service("orderService")
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

    @Override
    public PageInfo<Order> page(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Order> page = orderMapper.page();
        return new PageInfo(page);
    }
}
