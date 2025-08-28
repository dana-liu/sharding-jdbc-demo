package com.lww.sharding.logistics.config;/**
 * @author wu
 * @date 2025/8/27
 */

import com.github.pagehelper.PageInterceptor;
import com.mysql.cj.jdbc.MysqlXADataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-27  17:51
 * @Version: 1.0
 */
@Configuration
// 这个地方配置 会导致XA不生效 因为找不到logisticsXaSqlSessionFactory
//@MapperScan(basePackages = "com.lww.sharding.logistics.mapper.xa", sqlSessionFactoryRef = "logisticsXaSqlSessionFactory")
public class LogisticsXaDataSourceConfig {
    @Autowired
    private Environment env;

    @Bean(name = "logisticsXaDataSource")
    public DataSource logisticsDataSource() {

        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUrl(env.getProperty("spring.datasource.logistics.url"));
        mysqlXADataSource.setUser(env.getProperty("spring.datasource.logistics.username"));
        mysqlXADataSource.setPassword(env.getProperty("spring.datasource.logistics.password"));

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("logisticsXaDataSource");
        atomikosDataSourceBean.setMaxPoolSize(10);
        atomikosDataSourceBean.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");

        return atomikosDataSourceBean;
    }

}
