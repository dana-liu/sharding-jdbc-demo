package com.lww.sharding.distribution;/**
 * @author wu
 * @date 2025/8/28
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-28  11:53
 * @Version: 1.0
 */
@Controller
@RequestMapping("/test")
public class CheckJtaController {
    @Autowired
    DataSource orderXaDataSource;
    @Autowired
    DataSource logisticsXaDataSource;
    @Autowired
    PlatformTransactionManager jtaTransactionManager;
    @Autowired
    DistributionService distributionService;


    @PostConstruct
    public void check() {
        System.out.println("orderXaDataSource: " + orderXaDataSource);
        System.out.println("logisticsXaDataSource: " + logisticsXaDataSource);
        System.out.println("jtaTransactionManager: " + jtaTransactionManager);
    }


    @GetMapping("/save")
    public void test() throws Exception {
        TransactionTemplate transactionTemplate = new TransactionTemplate(jtaTransactionManager);
        transactionTemplate.execute(status -> {
            System.out.println("手动事务执行中...");
            distributionService.save();
            return null;
        });
    }

}
