package com.lww.sharding.user.sharding;

import org.apache.shardingsphere.spi.masterslave.MasterSlaveLoadBalanceAlgorithm;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinMasterSlaveLoadBalanceAlgorithm implements MasterSlaveLoadBalanceAlgorithm {
    
    private static AtomicInteger count = new AtomicInteger(0);
    
    @Override
    public String getDataSource(String name, String masterDataSourceName, List<String> slaveDataSourceNames) {
        if (slaveDataSourceNames.isEmpty()) {
            return masterDataSourceName;
        }
        int index = count.getAndIncrement() % slaveDataSourceNames.size();
        if (count.get() > 9999) {
            count.set(0);
        }
        return slaveDataSourceNames.get(index);
    }
    
    @Override
    public String getType() {
        return "ROUND_ROBIN";
    }
    
    @Override
    public Properties getProperties() {
        return new Properties();
    }
    
    @Override
    public void setProperties(Properties properties) {
        // 无需实现
    }
}