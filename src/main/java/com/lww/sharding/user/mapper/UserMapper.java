package com.lww.sharding.user.mapper;/**
 * @author wu
 * @date 2025/8/19
 */

import com.lww.sharding.user.annotation.MasterRoute;
import com.lww.sharding.user.entity.User;
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
public interface UserMapper {

    @Select("select id,name as name from user where id = #{id}")
    @MasterRoute
    User getUserById(Long id);

    @Select("select * from user order by id asc")
    List<User> list();

    @Insert("insert into user(name) values(#{name})")
    void save(User user);

    @Select("select id,uname as name from t_user")
    List<User> list2();

}
