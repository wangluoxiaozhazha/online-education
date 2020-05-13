package com.city.base.mapper;

import com.city.base.entity.Resource;
import org.apache.ibatis.annotations.*;

import java.util.Collection;


/**
 *操作资源数据的mapper
 */
@Mapper
public interface ResourceMapper {

    @Select("select * from resourceinformationobtain where CourseName=#{CourseName}")
    public Collection<Resource> getResourceByName(Resource resource);

    @Select("select * from resourceinformationobtain where ResourceID=#{id}")
    public Resource getResourceById(Integer id);

    @Select("select * from resourceinformationobtain limit #{page},10")
    public Collection<Resource> selectResourceByPage(Integer page);

    @Select("select * from resourceinformationobtain limit #{number},4")
    public Collection<Resource> selectResourceByRecommend(Integer number);

    @Select("select * from resourceinformationobtain")
    public Collection<Resource> selectAllResource();

    @Select("select * from resourceinformationobtain where ClassIfication=#{classIfication} limit #{page},10")
    public Collection<Resource> selectResourceByClass(String classIfication, Integer page);

    @Select("select * from resourceinformationobtain where ClassIfication=#{classIfication}")
    public Collection<Resource> selectResourceByClassApp(String classIfication);

    @Select("select count(*) from resourceinformationobtain")
    public int selectResourceNumber();

    @Select("select count(*) from resourceinformationobtain where ClassIfication=#{classIfication}")
    public int selectResourceNumberByClass(String classIfication);

    @Delete("delete from resourceinformationobtain where ResourceID=#{id}")
    public int deleteResourceById(Integer id);

    @Insert("insert into resourceinformationobtain(CourseName,Lecturer,ClassIfication,NumberEpisodes,CourseInformation,CourseIntroduction,Reference,CoverPath) values(#{CourseName},#{Lecturer},#{ClassIfication},#{NumberEpisodes},#{CourseInformation},#{CourseIntroduction},#{Reference},#{CoverPath})")
    public int insertResource(Resource resource);

    @Update("update resourceinformationobtain set CourseName=#{CourseName},Lecturer=#{Lecturer},ClassIfication=#{ClassIfication},NumberEpisodes=#{NumberEpisodes},CourseInformation=#{CourseInformation},CourseIntroduction=#{CourseIntroduction},CoverPath=#{CoverPath} where ResourceID=#{ResourceID}")
    public int updateResource(Resource resource);

    @Select("select CoverPath from resourceinformationobtain where ResourceID=#{id}")
    public String getImageUrl(Integer id);

    @Update("update resourceinformationobtain set reference=#{value} where ResourceID=#{id}")
    public int updateReference(Integer id,Integer value);

    @Select("select * from resourceinformationobtain where reference=1")
    public Collection<Resource> selectResourceByReference();
}
