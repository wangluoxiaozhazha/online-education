package com.city.base.adminservice;

import com.city.base.entity.Transmission;
import com.city.base.entity.User;

import java.util.Collection;

public interface UserService {
    /**
     * 根据页数获得用户
     * @param page
     * @param transmission
     * @return
     */
    public Collection<User> selectUserByPage(Integer page, Transmission transmission);

    /**
     * 搜索用户
     * @param user
     * @param transmission
     * @return
     */
    public Collection<User> selectUserBySearch(User user,Transmission transmission);

    /**
     * 请求删除
     * @param page
     * @param id
     * @param transmission
     * @return
     */
    public Collection<User> deleteRequest( Integer page,Integer id,Transmission transmission);

    /**
     * 删除用户
     * @param id
     * @param transmission
     * @return
     */
    public Collection<User> deleteUser(Integer id,Transmission transmission);

    /**
     * 请求密码
     * @param id
     * @param transmission
     * @return
     */
    public User requestPassword(Integer id,Transmission transmission);

    /**
     * 重置密码
     * @param user
     * @param id
     * @param transmission
     * @return
     */
    public Collection<User> resetPassword(User user,Integer id,Transmission transmission);

    /**
     * 获得用户人数
     * @return
     */
    public int getUserNumber();
}
