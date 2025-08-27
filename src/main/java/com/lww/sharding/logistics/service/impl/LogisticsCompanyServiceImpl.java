package com.lww.sharding.logistics.service.impl;/**
 * @author wu
 * @date 2025/8/27
 */

import com.lww.sharding.logistics.entity.LogisticsCompany;
import com.lww.sharding.logistics.mapper.LogisticsCompanyMapper;
import com.lww.sharding.logistics.service.LogisticsCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-27  17:59
 * @Version: 1.0
 */
@Service
public class LogisticsCompanyServiceImpl implements LogisticsCompanyService {

    @Autowired
    private LogisticsCompanyMapper logisticsCompanyMapper;


    public List<LogisticsCompany> list() {
        LogisticsCompany logisticsCompany = new LogisticsCompany();
        return logisticsCompanyMapper.list();
    }

    @Override
    public void save(LogisticsCompany logisticsCompany) {
        logisticsCompanyMapper.save(logisticsCompany);
    }
}
