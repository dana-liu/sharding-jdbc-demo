package com.lww.sharding.jta;/**
 * @author wu
 * @date 2025/8/28
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-28  14:30
 * @Version: 1.0
 */
@Configuration
@MapperScan(basePackages = "com.lww.sharding.logistics.mapper.xa", sqlSessionFactoryRef = "logisticsXaSqlSessionFactory")
@MapperScan(basePackages = "com.lww.sharding.order.mapper.xa", sqlSessionFactoryRef = "orderXaSqlSessionFactory")
public class MapperScanConfig {
}
