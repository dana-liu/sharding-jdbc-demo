package com.lww.sharding.order.controller;/**
 * @author wu
 * @date 2025/8/19
 */

import cn.hutool.core.util.RandomUtil;
import com.lww.sharding.order.entity.Order;
import com.lww.sharding.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-19  11:08
 * @Version: 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    @Qualifier("orderService")
    private OrderService orderService;

    @GetMapping("/list")
    public List<Order> list() {
        return orderService.list();
    }

    @GetMapping("/page")
    public List<Order> page(@RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", required = false,defaultValue = "10") int pageSize) {
        return orderService.page(pageNum,pageSize);
    }

    @GetMapping("/save")
    public String save() {
        Order order = new Order();
        order.setNumber(RandomUtil.randomLong());
        order.setName("订单-"+RandomUtil.randomInt());
        orderService.save(order);
        return "保存成功";
    }

}
