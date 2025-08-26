package com.lww.sharding.user.config;

import org.apache.shardingsphere.api.hint.HintManager;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MasterRouteAspect {
    
    @Before("@annotation(com.lww.sharding.user.annotation.MasterRoute) || " +
            "@within(com.lww.sharding.user.annotation.MasterRoute)")
    public void setMasterRoute() {
        // 使用 HintManager 强制路由到主库
        HintManager hintManager = HintManager.getInstance();
        hintManager.setMasterRouteOnly();
    }
    
    @After("@annotation(com.lww.sharding.user.annotation.MasterRoute) || " +
           "@within(com.lww.sharding.user.annotation.MasterRoute)")
    public void clearMasterRoute() {
        HintManager.clear();
    }
}