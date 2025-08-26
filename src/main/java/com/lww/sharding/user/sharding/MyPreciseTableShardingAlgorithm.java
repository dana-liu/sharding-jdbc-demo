package com.lww.sharding.user.sharding;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * 自定义精确分片算法：根据 id 取模分表
 */
public class MyPreciseTableShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        // 获取逻辑表名
        String logicTableName = shardingValue.getLogicTableName();
        // 获取分片值
        Long userId = shardingValue.getValue();

        // 计算分片：id % 表数量
        long tableSuffix = userId % availableTargetNames.size();

        // 拼接实际表名
        String actualTableName = logicTableName + "_" + tableSuffix;

        // 检查表是否存在
        if (availableTargetNames.contains(actualTableName)) {
            return actualTableName;
        }

        throw new UnsupportedOperationException("无法路由到表: " + actualTableName);
    }
}