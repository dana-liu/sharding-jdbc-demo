package com.lww.sharding.jta;

import com.atomikos.icatch.config.UserTransactionService;
import com.atomikos.icatch.config.UserTransactionServiceImp;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.UserTransaction;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class JtaTransactionConfig {
    
    @Bean(initMethod = "init")
    public UserTransactionService userTransactionService() {
        Properties properties = new Properties();
        properties.setProperty("com.atomikos.icatch.service", "com.atomikos.icatch.standalone.UserTransactionServiceFactory");
        properties.setProperty("com.atomikos.icatch.log_base_dir", "logs/atomikos");
        properties.setProperty("com.atomikos.icatch.output_dir", "logs/atomikos");
        properties.setProperty("com.atomikos.icatch.log_base_name", "transaction-logs");
        properties.setProperty("com.atomikos.icatch.tm_unique_name", "multi-datasource-tm");
        properties.setProperty("com.atomikos.icatch.console_file_name", "tm.out");
        properties.setProperty("com.atomikos.icatch.console_log_level", "INFO");
        properties.setProperty("com.atomikos.icatch.threaded_2pc", "true");
        properties.setProperty("com.atomikos.icatch.serial_jta_transactions", "false");
        properties.setProperty("com.atomikos.icatch.max_timeout", "300000"); // 5分钟
        properties.setProperty("com.atomikos.icatch.default_jta_timeout", "10000"); // 10秒
        return new UserTransactionServiceImp(properties);
    }


    @Bean(initMethod = "init", destroyMethod = "close")
    public UserTransactionManager atomikosTransactionManager() {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setStartupTransactionService(false);
        userTransactionManager.setForceShutdown(false);
        return userTransactionManager;
    }
    
    @Bean
    public UserTransaction userTransaction() throws Exception {
        UserTransactionImp userTransaction = new UserTransactionImp();
        userTransaction.setTransactionTimeout(300);
        return userTransaction;
    }
    
    @Bean
    public PlatformTransactionManager jtaTransactionManager(
            UserTransaction userTransaction,
            UserTransactionManager atomikosTransactionManager) {
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setUserTransaction(userTransaction);
        jtaTransactionManager.setTransactionManager(atomikosTransactionManager);
        jtaTransactionManager.setAllowCustomIsolationLevels(true);
        jtaTransactionManager.setDefaultTimeout(30); // 30秒超时
        return jtaTransactionManager;
    }
}