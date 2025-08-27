package com.lww.sharding.logistics.entity;/**
 * @author wu
 * @date 2025/8/27
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-27  17:58
 * @Version: 1.0
 */
public class LogisticsCompany {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
