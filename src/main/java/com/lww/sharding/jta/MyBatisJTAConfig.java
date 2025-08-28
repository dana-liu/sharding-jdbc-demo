package com.lww.sharding.jta;

import com.github.pagehelper.PageInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Slf4j
public class MyBatisJTAConfig {
    
    // ==================== ORDER MyBatis 配置 (XA) ====================
    @Bean("orderXaSqlSessionFactory")
    public SqlSessionFactory orderXASqlSessionFactory(
            @Qualifier("orderXaDataSource") DataSource dataSource,@Qualifier("myPageInterceptor") PageInterceptor pageInterceptor) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTypeAliasesPackage("com.lww.sharding.order.entity");
        // 配置分页插件
        factory.setPlugins(new Interceptor[]{pageInterceptor});
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        // 打印SQL日志
        configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
        // 配置驼峰命名
        factory.setConfiguration(configuration);

        // 配置事务管理器
        factory.setTransactionFactory(new SpringManagedTransactionFactory());
        return factory.getObject();
    }
    
    // ==================== LOGISTICS MyBatis 配置 (XA) ====================
    
    @Bean("logisticsXaSqlSessionFactory")
    public SqlSessionFactory tradeXASqlSessionFactory(
            @Qualifier("logisticsXaDataSource") DataSource dataSource,@Qualifier("myPageInterceptor") PageInterceptor pageInterceptor) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTypeAliasesPackage("com.lww.sharding.logistics.entity");
        factory.setPlugins(new Interceptor[]{pageInterceptor});

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        // 打印SQL日志
        configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
        factory.setConfiguration(configuration);
        // 配置事务管理器
        factory.setTransactionFactory(new SpringManagedTransactionFactory());
        
        return factory.getObject();
    }

    @Bean(name = "myPageInterceptor")
    public PageInterceptor pageInterceptor(){
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
        return pageInterceptor;
    }

    /**
     * 不能单独提出来，否则分页会报空指针异常，因为PageInterceptor没有和Configuration关联到一起
     * 只能放到sqlSessionFactory中
     * @return
     */
    @Bean(name = "mybatisConfiguration")
    public org.apache.ibatis.session.Configuration mybatisConfiguration(){
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        // 打印SQL日志
        configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
        // 出发初始化 确保配置被初始化
        configuration.getMappedStatementNames();
        return configuration;
    }


}