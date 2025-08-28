package com.lww.sharding.distribution;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.Transaction;

@Component
@Aspect
@Slf4j
public class TransactionMonitorAspect {
    
    @Autowired
    private JtaTransactionManager jtaTransactionManager;
    
    /**
     * 监控分布式事务的开始
     */
    @Before("@within(org.springframework.transaction.annotation.Transactional) || " +
            "@annotation(org.springframework.transaction.annotation.Transactional)")
    public void monitorTransactionStart(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        log.info("Starting distributed transaction for method: {}", methodName);
        
        try {
            Transaction transaction = jtaTransactionManager.getTransactionManager().getTransaction();
            log.info("Transaction ID: {}", transaction.toString());
        } catch (Exception e) {
            log.warn("Failed to get transaction information", e);
        }
    }
    
    /**
     * 监控分布式事务的提交
     */
    @AfterReturning("@within(org.springframework.transaction.annotation.Transactional) || " +
                   "@annotation(org.springframework.transaction.annotation.Transactional)")
    public void monitorTransactionCommit(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        log.info("Distributed transaction committed for method: {}", methodName);
    }
    
    /**
     * 监控分布式事务的回滚
     */
    @AfterThrowing(
        pointcut = "@within(org.springframework.transaction.annotation.Transactional) || " +
                  "@annotation(org.springframework.transaction.annotation.Transactional)",
        throwing = "ex"
    )
    public void monitorTransactionRollback(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().toShortString();
        log.warn("Distributed transaction rolled back for method: {} due to: {}", 
                 methodName, ex.getMessage());
    }
}