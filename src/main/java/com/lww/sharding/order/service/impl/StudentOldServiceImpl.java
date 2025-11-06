package com.lww.sharding.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lww.sharding.order.entity.Order;
import com.lww.sharding.order.mapper.OrderMapper;
import com.lww.sharding.order.mapper.StudentOldMapper;
import com.lww.sharding.order.service.OrderService;
import com.lww.sharding.order.service.StudentOldService;
import com.lww.sharding.user.entity.StudentBoughtClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-19  11:28
 * @Version: 1.0
 */
@Slf4j
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

    @Override
    public void flush(long minId, long maxId) {
        while(true){
            List<StudentBoughtClass> list = studentOldMapper.listById(minId,maxId);
            if (minId > 245914622){
                break;
            }
            List list0 = new ArrayList();
            List list1 = new ArrayList();
            List list2 = new ArrayList();
            List list3 = new ArrayList();
            List list4 = new ArrayList();
            List list5 = new ArrayList();
            List list6 = new ArrayList();
            List list7 = new ArrayList();
            List list8 = new ArrayList();
            List list9 = new ArrayList();
            for (StudentBoughtClass studentBoughtClass : list) {
                long suffix = studentBoughtClass.getUserNumber() % 10;
                if (suffix == 0){
                    list0.add(studentBoughtClass);
                } else if (suffix == 1) {
                    list1.add(studentBoughtClass);
                } else if (suffix == 2) {
                    list2.add(studentBoughtClass);
                } else if (suffix == 3) {
                    list3.add(studentBoughtClass);
                } else if (suffix == 4) {
                    list4.add(studentBoughtClass);
                } else if (suffix == 5) {
                    list5.add(studentBoughtClass);
                } else if (suffix == 6) {
                    list6.add(studentBoughtClass);
                } else if (suffix == 7) {
                    list7.add(studentBoughtClass);
                } else if (suffix == 8) {
                    list8.add(studentBoughtClass);
                } else if (suffix == 9) {
                    list9.add(studentBoughtClass);
                }
            }
            if (!list0.isEmpty()){
                studentOldMapper.insertBatch(list0,0);
            }
            if (!list1.isEmpty()){
                studentOldMapper.insertBatch(list1,1);
            }
            if (!list2.isEmpty()){
                studentOldMapper.insertBatch(list2,2);
            }
            if (!list3.isEmpty()){
                studentOldMapper.insertBatch(list3,3);
            }
            if (!list4.isEmpty()){
                studentOldMapper.insertBatch(list4,4);
            }
            if (!list5.isEmpty()){
                studentOldMapper.insertBatch(list5,5);
            }
            if (!list6.isEmpty()){
                studentOldMapper.insertBatch(list6,6);
            }
            if (!list7.isEmpty()){
                studentOldMapper.insertBatch(list7,7);
            }
            if (!list8.isEmpty()){
                studentOldMapper.insertBatch(list8,8);
            }
            if (!list9.isEmpty()){
                studentOldMapper.insertBatch(list9,9);
            }
            list0.clear();
            list1.clear();
            list2.clear();
            list3.clear();
            list4.clear();
            list5.clear();
            list6.clear();
            list7.clear();
            list8.clear();
            list9.clear();
            log.info("flush minId: {}, maxId: {}", minId, maxId);
            minId = maxId;
            maxId = maxId + 1000;
        }
    }

    @Override
    public List<StudentBoughtClass> execSql(String sql) {
        return studentOldMapper.execSql(sql);
    }
}
