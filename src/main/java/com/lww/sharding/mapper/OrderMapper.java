package com.lww.sharding.mapper;/**
 * @author wu
 * @date 2025/8/19
 */

import com.lww.sharding.entity.Order;
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
public interface OrderMapper {


    @Select("select id,order_number as orderNumber from `order`")
    List<Order> list();

    @Insert("insert into `order`(order_number) values(#{orderNumber})")
    void save(Order order);
}
