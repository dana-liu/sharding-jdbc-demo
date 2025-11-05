package com.lww.sharding.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.cglib.proxy.Proxy;

import java.sql.Statement;
import java.util.Properties;

/**
 * 慢日志拦截器
 */
@Slf4j
@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
    @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
    @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})})
public class SlowSqlInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        Object returnValue = invocation.proceed();
        long time = System.currentTimeMillis() - start;
        if (time > 0) {
            // 控制台打印日志
            StatementHandler statementHandler = realTarget(invocation.getTarget());
            BoundSql boundSql = statementHandler.getBoundSql();
            String sql = boundSql.getSql();
            StringBuilder str = new StringBuilder(256);
            str.append("[sql:");
            str.append(beautifySql(sql));
            str.append("]");
            str.append("[Cost:");
            str.append(time);
            str.append("ms]");
            log.info(str.toString());
        }
        return returnValue;
    }

    @Override
    public Object plugin(Object arg) {
        return Plugin.wrap(arg, this);
    }

    @Override
    public void setProperties(Properties properties) {}

    /**
     * 获得真正的处理对象,可能多层代理
     *
     * @param target
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    private <T> T realTarget(Object target) {
        if (Proxy.isProxyClass(target.getClass())) {
            MetaObject metaObject = SystemMetaObject.forObject(target);
            return realTarget(metaObject.getValue("h.target"));
        }
        return (T)target;
    }

    /**
     * 美化Sql
     */
    private String beautifySql(String sql) {
        return sql.replaceAll("[\\s\n ]+", " ");
    }
}
