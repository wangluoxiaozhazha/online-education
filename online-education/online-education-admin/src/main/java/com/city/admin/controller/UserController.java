package com.city.admin.controller;

import com.city.base.adminservice.UserService;
import com.city.base.entity.Transmission;
import com.city.base.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class UserController {
    @Autowired
    Transmission transmission;
    @Autowired
    UserService userService;
    /**
     * 用户数据分页
     * @param page
     * @param model
     * @return
     */
    @GetMapping("/user-manage")
    public String selectUsers(@RequestParam("page") Integer page, Model model){
        Collection<User> users=userService.selectUserByPage(page,transmission);
        model.addAttribute("transmission",transmission);
        model.addAttribute("users",users);
        return "user-manage";
    }

    @PostMapping("/user/select")//搜索栏查询
    public String selectUser(@ModelAttribute User user,Model model){
        Collection<User> users=userService.selectUserBySearch(user,transmission);
        model.addAttribute("users",users);
        model.addAttribute("transmission",transmission);
        return "user-manage";
    }

    @GetMapping("/user-manage/requestdelete")//请求删除
    public String requestDelete(@RequestParam("page") Integer page,@RequestParam("id") Integer id,Model model){
        Collection<User> users=userService.deleteRequest(page,id,transmission);
        model.addAttribute("transmission",transmission);
        model.addAttribute("users",users);
        return "user-manage";
    }

    @GetMapping("/user-manage/delete")//执行删除
    public String deleteUser(@RequestParam("id") Integer id,Model model){
        Collection<User> users=userService.deleteUser(id,transmission);
        model.addAttribute("transmission",transmission);
        model.addAttribute("users",users);
        return "user-manage";
    }

    @GetMapping("/user-manage/requestpassword")
    public String requestPassword(@RequestParam("id") Integer id,Model model){
        User user=userService.requestPassword(id,transmission);
        model.addAttribute("user",user);
        return "password-reset";
    }

    @PostMapping("/user-manage/updatepassword")
    public String resetPassword(@ModelAttribute User user,@RequestParam("id") Integer id,Model model){
        Collection<User> users=userService.resetPassword(user,id,transmission);
        model.addAttribute("transmission",transmission);
        model.addAttribute("users", users);
        return "user-manage";
    }
}
