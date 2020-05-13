package com.city.android.controller;

import com.city.base.entity.User;
import com.city.base.mapper.UserMapper;
import com.city.base.util.Encryption;
import com.city.base.util.FileWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Collection;

@Controller
public class UserController {

    final String UIMAGE="/uimage/";
    @Autowired
    UserMapper userMapper;
    @Autowired
    Encryption encryption;
    @Autowired
    FileWrite fileWrite;

    @ResponseBody
    @PostMapping("/user/add")
    public String addUser(@ModelAttribute User user){

        user.setPassword(encryption.encryptionMD5(user.getPassword()));
        userMapper.insertUser(user);
        Collection<User> users=userMapper.selectUsersByPhone(user);
        userMapper.insertUserLike(users.iterator().next().getUserID());
        return "成功";
    }

    @ResponseBody
    @GetMapping("/user/select")
    public Collection<User> selectUser(@ModelAttribute User user){
        Collection<User> users=userMapper.selectUsersByPhone(user);
        users.iterator().next().setPassword("");
        return users;
    }

    @ResponseBody
    @PostMapping("/user/login")
    public Collection<User> loginApp(@ModelAttribute User user){
        Collection<User> users=userMapper.selectUsersByPhone(user);
        user.setPassword(encryption.encryptionMD5(user.getPassword()));
        if (users.iterator().next().getPassword().equals(user.getPassword())){
            users.iterator().next().setPassword("");
            return users;
        }else
            return null;
    }
    @ResponseBody
    @PostMapping("/user/updatepassword")
    public String updatePassword(@ModelAttribute User user){
        user.setPassword(encryption.encryptionMD5(user.getPassword()));
        userMapper.updatePasswordById(user,user.getUserID());
        return "成功";
    }

    @ResponseBody
    @GetMapping("/user/updateuimage")
    public String updateUimage(@ModelAttribute User user,@RequestParam("file") MultipartFile file){

        if (!file.isEmpty()){//有新上传的封面
            File deleteFile=new File(user.getUimagePath());
            if (deleteFile.exists()){
                deleteFile.delete();//删除老封面
            }
            user.setUimagePath(UIMAGE+file.getOriginalFilename());
            fileWrite.fileWritePath(file,user.getUimagePath());//写入新封面
            userMapper.updateUimageById(user,user.getUserID());

        }
        return "成功";
    }

    @ResponseBody
    @PostMapping("/user/updateuser")
    public String updateUser(@ModelAttribute User user){

        userMapper.updateUserById(user,user.getUserID());
        return "成功";
    }

    @ResponseBody
    @GetMapping("/user/like")
    public String selectUserLike(@ModelAttribute User user){

        return userMapper.selectUserLikeById(user.getUserID());
    }

    @ResponseBody
    @PostMapping("/user/updatelike")
    public String updateUserLike(@RequestParam("id") Integer id,@RequestParam("like") String like){

        String history=userMapper.selectUserLikeById(id);
        if (history!=null) {
            history=history+like+",";
        } else {
            history=like+",";
        }
        userMapper.updateUserLike(id,history);
        return "成功";
    }


}
