package com.lww.sharding.user.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class ShardingConfig {
    
    // ShardingSphere会自动创建DataSource，通常不需要手动创建
    // 只有在特殊情况下才需要自定义配置
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//
//        // 设置MyBatis配置
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        sessionFactory.setMapperLocations(resolver.getResources("classpath*:mapper/*.xml"));
//
//        // 如果有XML映射文件，取消下面的注释并根据实际路径修改
//        // sessionFactory.setMapperLocations(
//        //     new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
//        return sessionFactory.getObject();
//    }

}