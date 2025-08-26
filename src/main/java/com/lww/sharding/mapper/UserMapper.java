package com.lww.sharding.mapper;/**
 * @author wu
 * @date 2025/8/19
 */

import com.lww.sharding.entity.User;
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

    @Select("select id,uname as name from t_user where id = #{id}")
    User getUserById(Long id);

    @Select("select * from user order by id asc limit 3,2")
    List<User> list();

    @Insert("insert into user(name) values(#{name})")
    void save(User user);
}
