package com.lww.sharding.user.mapper;/**
 * @author wu
 * @date 2025/8/19
 */

import com.lww.sharding.user.annotation.MasterRoute;
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
public interface UserMapper {

    @Select("select id,name as name from user where id = #{id}")
    @MasterRoute
    User getUserById(Long id);

    @Select("select * from user order by id asc")
    List<User> list();

    @Insert("insert into user(id,name) values(#{id},#{name})")
    void save(User user);

    @Select("select id,uname as name from t_user")
    List<User> list2();

    @Select("SELECT u.id,u.name,o.order_number from user u left join `order` o on u.id = o.user_number")
    List<UserOrder> join();
    @Select("SELECT u.id,u.name,o.order_number from user u left join `order` o on u.id = o.user_number where u.id in ( 1167892268229591040,1167902547399147520,1167902581276540929)")
    List<UserOrder> joinUser();
}
