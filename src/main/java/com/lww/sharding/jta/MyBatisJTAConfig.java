package com.lww.sharding.jta;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class MyBatisJTAConfig {
    
    // ==================== ORDER MyBatis 配置 (XA) ====================
    @Bean("orderXaSqlSessionFactory")
    public SqlSessionFactory orderXASqlSessionFactory(
            @Qualifier("orderXaDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTypeAliasesPackage("com.lww.sharding.order.entity");
        
        // 配置事务管理器
        factory.setTransactionFactory(new SpringManagedTransactionFactory());
        return factory.getObject();
    }
    
    // ==================== LOGISTICS MyBatis 配置 (XA) ====================
    
    @Bean("logisticsXaSqlSessionFactory")
    public SqlSessionFactory tradeXASqlSessionFactory(
            @Qualifier("logisticsXaDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTypeAliasesPackage("com.lww.sharding.logistics.entity");
        
        // 配置事务管理器
        factory.setTransactionFactory(new SpringManagedTransactionFactory());
        
        return factory.getObject();
    }


}