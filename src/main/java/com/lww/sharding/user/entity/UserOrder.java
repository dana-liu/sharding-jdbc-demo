package com.lww.sharding.user.entity;/**
 * @author wu
 * @date 2025/8/19
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-19  11:09
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOrder {
    private Long id;
    private String name;
    private Long orderNumber;

//    public UserOrder(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//    public UserOrder() {
//    }
//    public Long getId() {
//        return id;
//    }
//    public void setId(Long id) {
//        this.id = id;
//    }
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
}
