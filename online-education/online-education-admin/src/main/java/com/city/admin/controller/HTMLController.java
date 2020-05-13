package com.city.admin.controller;

import com.city.base.adminservice.ClassIficationService;
import com.city.base.adminservice.ResourceService;
import com.city.base.adminservice.UserService;
import com.city.base.entity.ClassIfication;
import com.city.base.entity.Resource;
import com.city.base.entity.Transmission;
import com.city.base.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@Controller
public class HTMLController {

    @Autowired
    ClassIficationService classService;
    @Autowired
    UserService userService;
    @Autowired
    ResourceService resourceService;
    @Autowired
    Transmission transmission;
    final Integer noOperation=0;//无操作
    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @RequestMapping("/index.html")
    public String index(Model model) {
        Collection<ClassIfication> classIfications = classService.getIndex();
        model.addAttribute("classIfications", classIfications);
        return "index";
    }
    @GetMapping("/course-manage.html")
    public String courseManage(Model model){
        Collection<Resource> resources=resourceService.getResourceManage(transmission);
        model.addAttribute("transmission",transmission);
        model.addAttribute("resources",resources);
        Collection<ClassIfication> classIfications=classService.getAllClass();
        model.addAttribute("classIfications",classIfications);
        return "course-manage";
    }

    @GetMapping("/class-add.html")
    public String classAdd(Model model){
        model.addAttribute("transmission",transmission);
        return "class-add";
    }

    @GetMapping("/class-manage.html")
    public String classManage(Model model){
        Collection<ClassIfication> classIfications=classService.getClassManage(transmission);
        model.addAttribute("classIfications",classIfications);
        model.addAttribute("transmission",transmission);
        return "class-manage";
    }
    @GetMapping("/course-add.html")
    public String courseAdd(Model model){
        Collection<ClassIfication> classIfications=classService.getAllClass();
        model.addAttribute("classIfications",classIfications);
        transmission.setRequest(noOperation);
        model.addAttribute("transmission",transmission);
        return "course-add";
    }
    @GetMapping("/user-manage.html")
    public String userManage(Model model){
        Collection<User> users=userService.selectUserByPage(0,transmission);
        model.addAttribute("users",users);
        transmission.initialization(userService.getUserNumber());
        model.addAttribute("transmission",transmission);
        return "user-manage";
    }
}
