package com.lww.sharding.order.mapper;/**
 * @author wu
 * @date 2025/8/19
 */

import com.lww.sharding.order.entity.Order;
import com.lww.sharding.user.entity.StudentBoughtClass;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-19  11:26
 * @Version: 1.0
 */
@Mapper
public interface StudentOldMapper {


    @Select("select * from student_bought_class")
    List<StudentBoughtClass> list();

    @Insert("insert into student_bought_class(user_number) values(#{req.userNumber})")
    int save(@Param("req") StudentBoughtClass studentBoughtClass);

    @Select("select * from student_bought_class where user_number = #{userNumber}")
    List<StudentBoughtClass> queryByUserNumber(long userNumber);

    List<StudentBoughtClass> listById(@Param("minId") long minId, @Param("maxId") long maxId);

    void insertBatch(@Param("list")List list, @Param("suffix")int suffix);
}
