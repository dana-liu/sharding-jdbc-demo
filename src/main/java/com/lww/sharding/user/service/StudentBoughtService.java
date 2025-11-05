package com.lww.sharding.user.service;/**
 * @author wu
 * @date 2025/8/19
 */

import com.lww.sharding.user.dto.StudentBoughtClassDto;
import com.lww.sharding.user.entity.StudentBoughtClass;
import com.lww.sharding.user.entity.User;
import com.lww.sharding.user.entity.UserOrder;

import java.util.List;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-19  11:27
 * @Version: 1.0
 */
public interface StudentBoughtService {

    List<StudentBoughtClass> list();

    void flush(long minId, long maxId);

    StudentBoughtClass getLast();

    List<StudentBoughtClass> getByUserNumber(long userNumber);

    void save(StudentBoughtClass studentBoughtClass);
}
