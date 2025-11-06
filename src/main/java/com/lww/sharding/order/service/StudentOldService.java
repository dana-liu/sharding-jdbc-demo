package com.lww.sharding.order.service;/**
 * @author wu
 * @date 2025/8/19
 */


import com.github.pagehelper.PageInfo;
import com.lww.sharding.order.entity.Order;
import com.lww.sharding.user.entity.StudentBoughtClass;

import java.util.List;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-19  11:27
 * @Version: 1.0
 */
public interface StudentOldService {

    List<StudentBoughtClass> list();

    void save(StudentBoughtClass studentBoughtClass);

    List<StudentBoughtClass> getByUserNumber(long userNumber);

    void flush(long minId, long maxId);

    List<StudentBoughtClass> execSql(String sql);
}
