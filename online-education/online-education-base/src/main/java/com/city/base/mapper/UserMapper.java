package com.city.base.mapper;


import com.city.base.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.Collection;

@Mapper
public interface UserMapper {

    @Insert("insert into userinformation(UserName,Telephone,Password,UimagePath) values(#{UserName},#{Telephone},#{Password},#{UimagePath})")
    public int insertUser(User user);

    @Select("select * from userinformation limit #{page},10")
    public Collection<User> selectUsers(Integer page);

    @Select("select * from userinformation where Telephone=#{Telephone}")
    public Collection<User> selectUsersByPhone(User user);

    @Select("select * from userinformation where UserID=#{id}")
    public User selectUsersById(Integer id);


    @Select("select count(*) from userinformation")
    public int selectUserNumber();

    @Delete("delete from userinformation where UserID=#{id}")
    public int deleteUser(Integer id);

    @Update("update userinformation set Password=#{user.Password} where UserID=#{id}")
    public int updatePasswordById(User user, Integer id);

    @Update("update userinformation set UserName =#{user.UserName},Telephone=#{user.Telephone} where UserID=#{id}")
    public int updateUserById(User user, Integer id);

    @Update("update userinformation set UimagePath=#{user.UimagePath}")
    public int  updateUimageById(User user, Integer id);

    @Select("select History from userlikeresource where UserID=#{id}")
    public String selectUserLikeById(Integer id);

    @Insert("insert into userlikeresource(UserID) values(#{id})")
    public int insertUserLike(Integer id);

    @Update("update userlikeresource set History=#{history} where UserID=#{id}")
    public int updateUserLike(Integer id, String history);
}
