package com.city.base.adminservice.impl;

import com.city.base.adminservice.UserService;
import com.city.base.entity.Transmission;
import com.city.base.entity.User;
import com.city.base.mapper.UserMapper;
import com.city.base.util.Encryption;
import com.city.base.util.FileWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class UserServiceImpl implements UserService {
    final Integer noOperation=0;
    final Integer delete=1;
    final Integer operation=2;
    final String UIMAGE="/uimage/";
    @Autowired
    UserMapper userMapper;
    @Autowired
    Encryption encryption;
    @Autowired
    FileWrite fileWrite;
    @Override
    public Collection<User> selectUserByPage(Integer page, Transmission transmission) {
        transmission.setPage(page);
        transmission.boundaryJudgment(userMapper.selectUserNumber());
        Collection<User> users=userMapper.selectUsers((transmission.getPage()-1)*10);
        return users;
    }

    @Override
    public Collection<User> selectUserBySearch(User user, Transmission transmission) {
        Collection<User> users=userMapper.selectUsersByPhone(user);
        transmission.initialization(userMapper.selectUserNumber());
        return users;
    }

    @Override
    public Collection<User> deleteRequest(Integer page, Integer id, Transmission transmission) {
        transmission.setPage(page);
        transmission.boundaryJudgment(userMapper.selectUserNumber());
        transmission.setId(id);
        transmission.setRequest(delete);
        Collection<User> users=userMapper.selectUsers((transmission.getPage()-1)*10);
        return users;
    }

    @Override
    public Collection<User> deleteUser(Integer id, Transmission transmission) {
        userMapper.deleteUser(id);
        transmission.setRequest(noOperation);
        Collection<User> users=userMapper.selectUsers((transmission.getPage()-1)*10);
        return users;
    }

    @Override
    public User requestPassword(Integer id, Transmission transmission) {
        User user=userMapper.selectUsersById(id);
        return user;
    }

    @Override
    public Collection<User> resetPassword(User user, Integer id, Transmission transmission) {
        user.setPassword(encryption.encryptionMD5(user.getPassword()));
        userMapper.updatePasswordById(user,id);
        transmission.setRequest(operation);
        transmission.setProperty("修改");
        Collection<User> users = userMapper.selectUsers((transmission.getPage() - 1) * 10);
        return users;
    }

    @Override
    public int getUserNumber() {
        int number=userMapper.selectUserNumber();
        return number;
    }
}
