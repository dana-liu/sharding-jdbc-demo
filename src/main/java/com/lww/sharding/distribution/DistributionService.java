package com.lww.sharding.distribution;/**
 * @author wu
 * @date 2025/8/27
 */

import cn.hutool.core.util.RandomUtil;
import com.lww.sharding.logistics.entity.LogisticsCompany;
import com.lww.sharding.logistics.mapper.LogisticsCompanyMapper;
import com.lww.sharding.logistics.service.LogisticsCompanyService;
import com.lww.sharding.order.entity.Order;
import com.lww.sharding.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-27  17:59
 * @Version: 1.0
 */
@Service
public class DistributionService {

    @Autowired
    private LogisticsCompanyService logisticsCompanyService;
    @Autowired
    private OrderService orderService;



    public void save(){
        LogisticsCompany logisticsCompany = new LogisticsCompany();
        logisticsCompany.setName(RandomUtil.randomString(5));
        logisticsCompanyService.save(logisticsCompany);

        int i = 10 / 0;

        Order order = new Order();
        order.setNumber(RandomUtil.randomLong());
        order.setName(RandomUtil.randomString(5));
        orderService.save(order);
    }
}
