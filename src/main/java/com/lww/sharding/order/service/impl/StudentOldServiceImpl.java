package com.lww.sharding.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lww.sharding.order.entity.Order;
import com.lww.sharding.order.mapper.OrderMapper;
import com.lww.sharding.order.mapper.StudentOldMapper;
import com.lww.sharding.order.service.OrderService;
import com.lww.sharding.order.service.StudentOldService;
import com.lww.sharding.user.entity.StudentBoughtClass;
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
public class StudentOldServiceImpl implements StudentOldService {

    @Autowired
    private StudentOldMapper studentOldMapper;


    @Override
    public List<StudentBoughtClass> list() {
        return studentOldMapper.list();
    }

    @Override
    public void save(StudentBoughtClass studentBoughtClass) {
        studentOldMapper.save(studentBoughtClass);
    }

    @Override
    public List<StudentBoughtClass> getByUserNumber(long userNumber) {
        return studentOldMapper.queryByUserNumber(userNumber);
    }
}
