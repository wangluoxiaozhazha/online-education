package com.city.base.mapper;

import com.city.base.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {

    @Select("select * from admin where AdminName=#{adminName}")
    public Admin selectAdmin(String adminName);
}
