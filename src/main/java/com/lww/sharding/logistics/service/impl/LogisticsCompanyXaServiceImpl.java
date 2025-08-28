package com.lww.sharding.logistics.service.impl;/**
 * @author wu
 * @date 2025/8/27
 */

import com.lww.sharding.logistics.entity.LogisticsCompany;
import com.lww.sharding.logistics.mapper.LogisticsCompanyMapper;
import com.lww.sharding.logistics.mapper.xa.LogisticsCompanyXaMapper;
import com.lww.sharding.logistics.service.LogisticsCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-27  17:59
 * @Version: 1.0
 */
@Service(value = "logisticsCompanyXaService")
public class LogisticsCompanyXaServiceImpl implements LogisticsCompanyService {

    @Autowired
    private LogisticsCompanyXaMapper logisticsCompanyXaMapper;


    public List<LogisticsCompany> list() {
        LogisticsCompany logisticsCompany = new LogisticsCompany();
        return logisticsCompanyXaMapper.list();
    }

    @Override
    public void save(LogisticsCompany logisticsCompany) {
        logisticsCompanyXaMapper.save(logisticsCompany);
    }
}
