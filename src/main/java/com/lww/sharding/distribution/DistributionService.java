package com.lww.sharding.distribution;
/**
 * @author wu
 * @date 2025/8/27
 */

import cn.hutool.core.util.RandomUtil;
import com.lww.sharding.logistics.entity.LogisticsCompany;
import com.lww.sharding.logistics.mapper.LogisticsCompanyMapper;
import com.lww.sharding.logistics.service.LogisticsCompanyService;
import com.lww.sharding.order.entity.Order;
import com.lww.sharding.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-27  17:59
 * @Version: 1.0
 */
@Service
@Slf4j
public class DistributionService {

    @Autowired
    @Qualifier("logisticsCompanyXaService")
    private LogisticsCompanyService logisticsCompanyXaService;
    @Autowired
    @Qualifier("orderXaService")
    private OrderService orderXaService;



    @Transactional(transactionManager = "jtaTransactionManager",rollbackFor = Exception.class)
    public void save(){
        System.out.println("事务激活状态: " + TransactionSynchronizationManager.isActualTransactionActive());
        System.out.println("当前事务名称: " + TransactionSynchronizationManager.getCurrentTransactionName());

        LogisticsCompany logisticsCompany = new LogisticsCompany();
        logisticsCompany.setName(RandomUtil.randomString(5));
        logisticsCompanyXaService.save(logisticsCompany);

//        int i = 10 / 0;

        Order order = new Order();
        order.setNumber(RandomUtil.randomLong());
        order.setName(RandomUtil.randomString(5));
        orderXaService.save(order);

        throw new RuntimeException("保存失败");
    }
}
