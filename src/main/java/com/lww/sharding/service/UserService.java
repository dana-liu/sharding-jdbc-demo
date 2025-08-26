package com.lww.sharding.service;/**
 * @author wu
 * @date 2025/8/19
 */

import com.lww.sharding.entity.User;

import java.util.List;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-19  11:27
 * @Version: 1.0
 */
public interface UserService {
    User getUserById(Long id);

    List<User> list();

    void save(User user);
}
