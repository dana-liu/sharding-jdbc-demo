package com.lww.sharding.service.impl;/**
 * @author wu
 * @date 2025/8/19
 */

import com.lww.sharding.entity.User;
import com.lww.sharding.mapper.UserMapper;
import com.lww.sharding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-19  11:28
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public void save(User user) {
        userMapper.save(user);
    }
}
