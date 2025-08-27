package com.lww.sharding.logistics.controller;/**
 * @author wu
 * @date 2025/8/27
 */

import com.lww.sharding.logistics.entity.LogisticsCompany;
import com.lww.sharding.logistics.service.LogisticsCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-27  18:02
 * @Version: 1.0
 */
@RestController
@RequestMapping("/logistics")
public class LogisticsCompanyController {
    @Autowired
    private LogisticsCompanyService logisticsCompanyService;

    @RequestMapping("/list")
    public List<LogisticsCompany> list() {
        return logisticsCompanyService.list();
    }
}
