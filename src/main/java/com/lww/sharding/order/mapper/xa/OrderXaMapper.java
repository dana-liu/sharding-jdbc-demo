package com.lww.sharding.order.mapper.xa;/**
 * @author wu
 * @date 2025/8/19
 */

import com.lww.sharding.order.entity.Order;
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
public interface OrderXaMapper {


    @Select("select * from t_order")
    List<Order> list();

    @Insert("insert into `t_order`(number,name) values(#{number},#{name})")
    void save(Order order);

    @Select("select * from t_order")
    List<Order> page();
}
