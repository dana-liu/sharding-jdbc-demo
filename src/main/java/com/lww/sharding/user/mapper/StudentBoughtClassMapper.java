package com.lww.sharding.user.mapper;/**
 * @author wu
 * @date 2025/8/19
 */

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
public interface StudentBoughtClassMapper {


    @Select("select * from student_bought_class order by id asc limit 10")
    List<StudentBoughtClass> list();

    @Insert("insert into student_bought_class_0${suffix} " +
            "(user_number, course_number, buy_class_number, class_number, " +
            "pre_mini_class_number, mini_class_number, status, type, " +
            "source, product_type, is_renewals, order_number, renewal_date, " +
            "create_time, update_time, wechat_name, isgift) " +
            "values " +
            "(#{entity.userNumber}, #{entity.courseNumber}, #{entity.buyClassNumber}, " +
            "#{entity.classNumber}, #{entity.preMiniClassNumber}, #{entity.miniClassNumber}, " +
            "#{entity.status}, #{entity.type}, #{entity.source}, #{entity.productType}, " +
            "#{entity.isRenewals}, #{entity.orderNumber}, #{entity.renewalDate}, " +
            "#{entity.createTime}, #{entity.updateTime}, #{entity.wechatName}, #{entity.isgift})")
    void insert(@Param("entity") StudentBoughtClass studentBoughtClass);


    List<StudentBoughtClass> listById(@Param("minId") long minId, @Param("maxId") long maxId);

    void insertBatch(@Param("list")List list, @Param("suffix")int suffix);

    StudentBoughtClass selectLast();

    List<StudentBoughtClass> getByUserNumber(@Param("userNumber")List<Long> userNumber);

    @Insert("insert into student_bought_class(user_number) values(#{req.userNumber})")
    int save(@Param("req") StudentBoughtClass studentBoughtClass);

    List<StudentBoughtClass> join(@Param("userNumber") Long userNumber);

    List<StudentBoughtClass> joinList(@Param("userNumber")List<Long> userNumber);
}
