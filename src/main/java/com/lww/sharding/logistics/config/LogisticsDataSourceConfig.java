package com.lww.sharding.logistics.config;/**
 * @author wu
 * @date 2025/8/27
 */

import com.github.pagehelper.PageInterceptor;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-27  17:51
 * @Version: 1.0
 */
@Configuration
@MapperScan(basePackages = "com.lww.sharding.logistics.mapper", sqlSessionFactoryRef = "logisticsSqlSessionFactory")
public class LogisticsDataSourceConfig {
    @Autowired
    private Environment env;

    @Bean(name = "logisticsDataSource")
    public DataSource logisticsDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.logistics.driver-class-name"));
        dataSource.setJdbcUrl(env.getProperty("spring.datasource.logistics.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.logistics.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.logistics.password"));
        dataSource.setMaximumPoolSize(env.getProperty("spring.datasource.logistics.maximum-pool-size", Integer.class, 15));
        return dataSource;
    }


    @Bean(name = "logisticsSqlSessionFactory")
    public SqlSessionFactoryBean logisticsSqlSessionFactory(@Qualifier("logisticsDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTypeAliasesPackage("com.lww.sharding.logistics.entity");

        // 配置分页插件
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        // 设置数据库方言
        properties.setProperty("helperDialect", "mysql");
        // 分页参数合理化
        properties.setProperty("reasonable", "true");
        // 支持通过 Mapper 接口参数来传递分页参数
        properties.setProperty("supportMethodsArguments", "true");
        // 默认值为 false，当设置为 true 时，如果 pageSize=0 或 RowBounds.limit = 0 就会查询出全部的结果
        properties.setProperty("pageSizeZero", "true");
        // 分页参数映射
        properties.setProperty("params", "count=countSql");
        pageInterceptor.setProperties(properties);
        factory.setPlugins(new Interceptor[]{pageInterceptor});
        // 配置驼峰命名
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        // 打印SQL日志
        configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
        factory.setConfiguration(configuration);
        return factory;
    }

    @Bean(name = "logisticsTransactionManager")
    public DataSourceTransactionManager logisticsTransactionManager(@Qualifier("logisticsDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
