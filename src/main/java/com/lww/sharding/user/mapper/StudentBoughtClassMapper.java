package com.lww.sharding.user.mapper;/**
 * @author wu
 * @date 2025/8/19
 */

import com.lww.sharding.user.annotation.MasterRoute;
import com.lww.sharding.user.dto.StudentBoughtClassDto;
import com.lww.sharding.user.entity.User;
import com.lww.sharding.user.entity.UserOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
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
    List<StudentBoughtClassDto> list();

}
