package com.lww.sharding.user.service;/**
 * @author wu
 * @date 2025/8/19
 */

import com.lww.sharding.user.entity.User;
import com.lww.sharding.user.entity.UserOrder;

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

    List<User> page(int pageNum, int pageSize);

    List<User> list2();

    List<UserOrder> join();

    List<UserOrder> joinUser();
}
