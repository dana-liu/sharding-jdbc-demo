# sharding-jdbc-demo
# 使用配置文件配置读写分离+分表
1. 引入maven依赖
        <dependency>
            <groupId>org.apache.shardingsphere</groupId>
            <artifactId>sharding-jdbc-spring-boot-starter</artifactId>
            <version>4.1.1</version>
        </dependency>
2. 使用[application-sharding-master-slave-tables.properties](src/main/resources/application-sharding-master-slave-tables-test.properties.bak)配置文件