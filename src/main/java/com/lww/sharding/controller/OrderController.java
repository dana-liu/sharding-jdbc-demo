package com.lww.sharding.controller;/**
 * @author wu
 * @date 2025/8/19
 */

import cn.hutool.core.util.RandomUtil;
import com.lww.sharding.entity.Order;
import com.lww.sharding.entity.User;
import com.lww.sharding.service.OrderService;
import com.lww.sharding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-19  11:08
 * @Version: 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public List<Order> list() {
        return orderService.list();
    }

    @GetMapping("/save")
    public void save() {
        Order order = new Order();
        order.setOrderNumber(RandomUtil.randomLong());
        orderService.save(order);
    }

}
