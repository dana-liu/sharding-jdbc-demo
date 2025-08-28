package com.lww.sharding.user.service.impl;/**
 * @author wu
 * @date 2025/8/19
 */

import com.github.pagehelper.PageHelper;
import com.lww.sharding.user.entity.User;
import com.lww.sharding.user.entity.UserOrder;
import com.lww.sharding.user.mapper.UserMapper;
import com.lww.sharding.user.service.UserService;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    @Override
    public List<User> page(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.list();
        return list;
    }

    @Override
//    @ShardingTransactionType(value = TransactionType.BASE)
    public List<User> list2() {
        return userMapper.list2();
    }

    @Override
    public List<UserOrder> join() {
        return userMapper.join();
    }

    @Override
    public List<UserOrder> joinUser() {
        return userMapper.joinUser();
    }
}
