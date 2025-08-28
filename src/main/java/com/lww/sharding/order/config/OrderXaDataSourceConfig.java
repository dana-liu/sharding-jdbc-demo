package com.lww.sharding.order.config;
/**
 * @author wu
 * @date 2025/8/25
 */

import com.mysql.cj.jdbc.MysqlXADataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-25  19:12
 * @Version: 1.0
 */
@Configuration
// 在这个地方添加MapperScan  获取不到orderXaSqlSessionFactory会使用默认的sqlSessionFactory，导致XA不生效
//@MapperScan(basePackages = "com.lww.sharding.order.mapper.xa", sqlSessionFactoryRef = "orderXaSqlSessionFactory")
public class OrderXaDataSourceConfig {

    @Autowired
    private Environment env;

    // 主数据源
    @Bean(name = "orderXaDataSource")
    public DataSource orderDataSource() {

        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setURL(env.getProperty("spring.datasource.order.url"));
        mysqlXADataSource.setUser(env.getProperty("spring.datasource.order.username"));
        mysqlXADataSource.setPassword(env.getProperty("spring.datasource.order.password"));

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("orderXaDataSource");
        atomikosDataSourceBean.setMaxPoolSize(10);
        atomikosDataSourceBean.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
        return atomikosDataSourceBean;
    }
}
