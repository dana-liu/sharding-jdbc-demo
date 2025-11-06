package com.lww.sharding.user.service.impl;/**
 * @author wu
 * @date 2025/8/19
 */

import com.github.pagehelper.PageHelper;
import com.lww.sharding.user.dto.StudentBoughtClassDto;
import com.lww.sharding.user.entity.StudentBoughtClass;
import com.lww.sharding.user.entity.User;
import com.lww.sharding.user.entity.UserOrder;
import com.lww.sharding.user.mapper.StudentBoughtClassMapper;
import com.lww.sharding.user.mapper.UserMapper;
import com.lww.sharding.user.service.StudentBoughtService;
import com.lww.sharding.user.service.UserService;
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
public class StudentBoughtClassServiceImpl implements StudentBoughtService {

    @Autowired
    private StudentBoughtClassMapper studentBoughtClassMapper;

    @Override
    public List<StudentBoughtClass> list() {
        return studentBoughtClassMapper.list();
    }

    @Override
    public void flush(long minId, long maxId) {
        while(true){
            List<StudentBoughtClass> list = studentBoughtClassMapper.listById(minId,maxId);
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
                studentBoughtClassMapper.insertBatch(list0,0);
            }
            if (!list1.isEmpty()){
                studentBoughtClassMapper.insertBatch(list1,1);
            }
            if (!list2.isEmpty()){
                studentBoughtClassMapper.insertBatch(list2,2);
            }
            if (!list3.isEmpty()){
                studentBoughtClassMapper.insertBatch(list3,3);
            }
            if (!list4.isEmpty()){
                studentBoughtClassMapper.insertBatch(list4,4);
            }
            if (!list5.isEmpty()){
                studentBoughtClassMapper.insertBatch(list5,5);
            }
            if (!list6.isEmpty()){
                studentBoughtClassMapper.insertBatch(list6,6);
            }
            if (!list7.isEmpty()){
                studentBoughtClassMapper.insertBatch(list7,7);
            }
            if (!list8.isEmpty()){
                studentBoughtClassMapper.insertBatch(list8,8);
            }
            if (!list9.isEmpty()){
                studentBoughtClassMapper.insertBatch(list9,9);
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
    public StudentBoughtClass getLast() {
        return studentBoughtClassMapper.selectLast();
    }

    @Override
    public List<StudentBoughtClass> getByUserNumber(List<Long>  userNumber) {
        if (userNumber.isEmpty()){
            return Collections.emptyList();
        }
        return studentBoughtClassMapper.getByUserNumber(userNumber);
    }

    @Override
    public void save(StudentBoughtClass studentBoughtClass) {
        studentBoughtClassMapper.save(studentBoughtClass);
    }

    @Override
    public List<StudentBoughtClass> join(Long userNumber) {
        return studentBoughtClassMapper.join(userNumber);
    }

    @Override
    public List<StudentBoughtClass> joinList(List<Long> userNumber) {
        return studentBoughtClassMapper.joinList(userNumber);
    }

    @Override
    public List<StudentBoughtClass> execSql(String sql) {
        return studentBoughtClassMapper.execSql(sql);
    }
}
