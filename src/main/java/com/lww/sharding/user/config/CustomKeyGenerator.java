package com.lww.sharding.user.config;/**
 * @author wu
 * @date 2025/8/28
 */

import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;

import java.util.Properties;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-28  19:32
 * @Version: 1.0
 */
public class CustomKeyGenerator implements ShardingKeyGenerator {
    @Override
    public Comparable<?> generateKey() {
        return null;
    }

    @Override
    public String getType() {
        return "";
    }

    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
