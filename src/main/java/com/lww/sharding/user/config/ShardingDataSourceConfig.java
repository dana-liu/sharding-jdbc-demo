package com.lww.sharding.user.config;/**
 * @author wu
 * @date 2025/8/25
 */

import com.lww.sharding.user.sharding.MyPreciseTableShardingAlgorithm;
import com.mysql.cj.jdbc.MysqlXADataSource;
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
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
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
public class ShardingDataSourceConfig {

    @Autowired
    private Environment env;

    // 主数据源
    @Bean
    public DataSource masterDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(env.getProperty("spring.datasource.user.master.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.user.master.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.user.master.password"));
        dataSource.setDriverClassName(env.getProperty("spring.datasource.user.master.driver-class-name"));
        dataSource.setMaximumPoolSize(env.getProperty("spring.datasource.user.master.maximum-pool-size", Integer.class, 15));

//        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
//        mysqlXADataSource.setUrl(env.getProperty("spring.datasource.user.master.url"));
//        mysqlXADataSource.setUser(env.getProperty("spring.datasource.user.master.username"));
//        mysqlXADataSource.setPassword(env.getProperty("spring.datasource.user.master.password"));
//
//        AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
//        dataSource.setXaDataSource(mysqlXADataSource);
//        dataSource.setUniqueResourceName("masterDataSource");

        return dataSource;
    }

    // 从数据源1
    @Bean
    public DataSource slaveDataSource0() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(env.getProperty("spring.datasource.user.slave0.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.user.slave0.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.user.slave0.password"));
        dataSource.setDriverClassName(env.getProperty("spring.datasource.user.slave0.driver-class-name"));
        dataSource.setMaximumPoolSize(env.getProperty("spring.datasource.user.slave0.maximum-pool-size", Integer.class, 15));


//        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
//        mysqlXADataSource.setUrl(env.getProperty("spring.datasource.user.slave0.url"));
//        mysqlXADataSource.setUser(env.getProperty("spring.datasource.user.slave0.username"));
//        mysqlXADataSource.setPassword(env.getProperty("spring.datasource.user.slave0.password"));
//        AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
//        dataSource.setXaDataSource(mysqlXADataSource);
//        dataSource.setUniqueResourceName("slaveDataSource0");
        return dataSource;
    }


    @Bean("userShardingDataSource")
    public DataSource userShardingDataSource(
            @Qualifier("masterDataSource") DataSource masterDataSource,
            @Qualifier("slaveDataSource0") DataSource slaveDataSource0) throws SQLException {

        // 1. 配置数据源映射
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("master", masterDataSource);
        dataSourceMap.put("slave0", slaveDataSource0);

        // 2. 配置读写分离规则
        MasterSlaveRuleConfiguration masterSlaveRule = new MasterSlaveRuleConfiguration(
                "ds", "master", Arrays.asList("slave0"), new LoadBalanceStrategyConfiguration("ROUND_ROBIN")
        );

        // 3. 配置分表规则
        ShardingRuleConfiguration shardingRule = new ShardingRuleConfiguration();

        // 用户表分片配置
        TableRuleConfiguration userTableRule = new TableRuleConfiguration(
                "user", "ds.user_${0..1}"
        );

        // 分片策略
        userTableRule.setTableShardingStrategyConfig(
                new StandardShardingStrategyConfiguration("id", new MyPreciseTableShardingAlgorithm())
        );

        // 分布式主键策略  会根据指定算法类型 为分片主键生成值，注释掉就不会了，可以自己生成
        userTableRule.setKeyGeneratorConfig(new KeyGeneratorConfiguration("SNOWFLAKE","id"));

        shardingRule.getTableRuleConfigs().add(userTableRule);


        TableRuleConfiguration studentBoughtClassTableRule = new TableRuleConfiguration(
                "student_bought_class", "ds.student_bought_class_${0..9}"
        );
        studentBoughtClassTableRule.setTableShardingStrategyConfig(
                new StandardShardingStrategyConfiguration("user_number", new MyPreciseTableShardingAlgorithm())
        );
//        studentBoughtClassTableRule.setKeyGeneratorConfig(new KeyGeneratorConfiguration("SNOWFLAKE","id"));

        shardingRule.getTableRuleConfigs().add(studentBoughtClassTableRule);



        // 4. 添加读写分离规则到分片规则
        shardingRule.getMasterSlaveRuleConfigs().add(masterSlaveRule);
        Properties properties = new Properties();
        // 添加SQL打印
        properties.setProperty("sql.show", "true");
        // 简化SQL打印
//        properties.setProperty("sql.simple", "true");
        // 6. 启用 XA 事务
        properties.setProperty("xa-transaction-manager-type", "Atomikos");
        // 5. 创建数据源
        return ShardingDataSourceFactory.createDataSource(
                dataSourceMap,
                shardingRule,
                properties
        );
    }


}
