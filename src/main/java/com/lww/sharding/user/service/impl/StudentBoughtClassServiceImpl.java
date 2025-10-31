package com.lww.sharding.user.service.impl;/**
 * @author wu
 * @date 2025/8/19
 */

import com.github.pagehelper.PageHelper;
import com.lww.sharding.user.dto.StudentBoughtClassDto;
import com.lww.sharding.user.entity.User;
import com.lww.sharding.user.entity.UserOrder;
import com.lww.sharding.user.mapper.StudentBoughtClassMapper;
import com.lww.sharding.user.mapper.UserMapper;
import com.lww.sharding.user.service.StudentBoughtService;
import com.lww.sharding.user.service.UserService;
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
public class StudentBoughtClassServiceImpl implements StudentBoughtService {

    @Autowired
    private StudentBoughtClassMapper studentBoughtClassMapper;

    @Override
    public List<StudentBoughtClassDto> list() {
        return studentBoughtClassMapper.list();
    }
}
