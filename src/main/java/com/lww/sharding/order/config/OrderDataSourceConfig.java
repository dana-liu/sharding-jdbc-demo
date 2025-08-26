package com.lww.sharding.order.config;/**
 * @author wu
 * @date 2025/8/25
 */

import com.lww.sharding.user.sharding.MyPreciseTableShardingAlgorithm;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.api.config.masterslave.LoadBalanceStrategyConfiguration;
import org.apache.shardingsphere.api.config.masterslave.MasterSlaveRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-25  19:12
 * @Version: 1.0
 */
@Configuration
public class OrderDataSourceConfig {

    @Autowired
    private Environment env;

    // 主数据源
    @Bean
    public DataSource orderDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(env.getProperty("spring.datasource.order.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.order.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.order.password"));
        dataSource.setDriverClassName(env.getProperty("spring.datasource.order.driver-class-name"));
        dataSource.setMaximumPoolSize(env.getProperty("spring.datasource.order.maximum-pool-size", Integer.class, 15));
        return dataSource;
    }

}
