package com.city.base.mapper;

import com.city.base.entity.ClassIfication;
import org.apache.ibatis.annotations.*;

import java.util.Collection;

/*
 *操作分类相关资源
 */
@Mapper
public interface ClassMapper {

    @Insert("insert into ClassIfication(ClassName) values(#{ClassName})")
    public int insertClass(ClassIfication classIfication);

    @Select("select * from classification limit #{page},10")
    public Collection<ClassIfication> selectPageClass(Integer page);

    @Select("select * from classification")
    public Collection<ClassIfication> selectAllClass();

    @Select("select count(*) from classification")
    public int selectClassNumber();

    @Delete("delete from classification where ClassID=#{id}")
    public int deleteClass(Integer id);

    @Select("select * from classification where ClassID=#{id}")
    public ClassIfication selectClassById(Integer id);

    @Update("update classification set ClassName=#{classIfication.ClassName} where ClassID=#{id}")
    public int updateClassById(ClassIfication classIfication, Integer id);

}
