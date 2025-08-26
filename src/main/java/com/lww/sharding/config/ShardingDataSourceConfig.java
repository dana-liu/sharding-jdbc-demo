package com.lww.sharding.config;/**
 * @author wu
 * @date 2025/8/25
 */

import com.lww.sharding.sharding.MyPreciseTableShardingAlgorithm;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.readwritesplitting.api.ReadwriteSplittingRuleConfiguration;
import org.apache.shardingsphere.readwritesplitting.api.rule.ReadwriteSplittingDataSourceRuleConfiguration;
import org.apache.shardingsphere.readwritesplitting.api.strategy.StaticReadwriteSplittingStrategyConfiguration;
import org.apache.shardingsphere.underlying.common.config.RuleConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-25  19:12
 * @Version: 1.0
 */
@Configuration
public class ShardingDataSourceConfig {
    private Binder binder;


    // 主数据源
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    // 从数据源1
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave0")
    public DataSource slaveDataSource0() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }


    // 读写分离规则
    @Bean
    public ReadwriteSplittingRuleConfiguration createReadwriteSplittingRule() {
        ReadwriteSplittingDataSourceRuleConfiguration dataSourceConfiguration =
                new ReadwriteSplittingDataSourceRuleConfiguration(
                        "readwrite_ds",
                        new StaticReadwriteSplittingStrategyConfiguration(
                                "master-ds",
                                Arrays.asList("slave-ds-0", "slave-ds-1")
                        ),
                        "round_robin"
                );

        return new ReadwriteSplittingRuleConfiguration(
                Arrays.asList(dataSourceConfiguration),
                Collections.singletonMap("round_robin", new RoundRobinLoadBalanceAlgorithm())
        );
    }

    // 分片规则
    @Bean
    public ShardingRuleConfiguration createShardingRuleConfiguration() {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();

        // 配置分表规则
        TableRuleConfiguration userTableRuleConfig = new TableRuleConfiguration(
                "user",
                "ds.user_$->{0..1}"
        );
        // 设置分表策略
        StandardShardingStrategyConfiguration tableShardingStrategy =
                new StandardShardingStrategyConfiguration("id", new MyPreciseTableShardingAlgorithm());
        userTableRuleConfig.setTableShardingStrategyConfig(tableShardingStrategy);

        shardingRuleConfig.getTableRuleConfigs().add(userTableRuleConfig);

        return shardingRuleConfig;
    }

    // 创建数据源
    @Bean
    public DataSource dataSource(
            DataSource masterDataSource,
            DataSource slaveDataSource0,
            ReadwriteSplittingRuleConfiguration readwriteSplittingRuleConfig,
            ShardingRuleConfiguration shardingRuleConfig) throws SQLException {

        // 创建数据源映射
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("master", masterDataSource);
        dataSourceMap.put("slave0", slaveDataSource0);

        // 组合所有规则
        Collection<RuleConfiguration> rules = new ArrayList<>();
        rules.add(readwriteSplittingRuleConfig);
        rules.add(shardingRuleConfig);

        // 创建ShardingSphere数据源
        return ShardingSphereDataSourceFactory.createDataSource(
                dataSourceMap, rules, new Properties()
        );
    }


}
