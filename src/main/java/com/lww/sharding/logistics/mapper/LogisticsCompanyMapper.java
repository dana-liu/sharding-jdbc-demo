package com.lww.sharding.logistics.mapper;/**
 * @author wu
 * @date 2025/8/27
 */

import com.lww.sharding.logistics.entity.LogisticsCompany;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: liuwenwu
 * @CreateTime: 2025-08-27  17:58
 * @Version: 1.0
 */
@Mapper
public interface LogisticsCompanyMapper {

    @Select("select * from logistics_company")
    List<LogisticsCompany> list();

    @Insert("insert into logistics_company(name) values(#{name})")
    void save(LogisticsCompany logisticsCompany);
}
