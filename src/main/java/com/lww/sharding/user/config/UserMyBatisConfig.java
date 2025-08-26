package com.lww.sharding.user.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(
    basePackages = "com.lww.sharding.user.mapper",
    sqlSessionFactoryRef = "userSqlSessionFactory"
)
public class UserMyBatisConfig {
    
    @Bean("userSqlSessionFactory")
    public SqlSessionFactory userSqlSessionFactory(
            @Qualifier("userShardingDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTypeAliasesPackage("com.lww.sharding.user.entity");

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
        factory.setConfiguration(configuration);

        return factory.getObject();
    }

    @Bean("userTransactionManager")
    public PlatformTransactionManager userTransactionManager(
            @Qualifier("userShardingDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}