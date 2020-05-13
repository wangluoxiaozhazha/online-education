package com.city.android.controller;


import com.city.base.adminservice.ClassIficationService;
import com.city.base.entity.ClassIfication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@Controller
public class ClassController {
    @Autowired
    ClassIficationService classService;

    /**
     * 返回所有分类
     * @return
     */
    @ResponseBody
    @GetMapping("/class/selectclass")
    public Collection<ClassIfication> selectClass(){
        Collection<ClassIfication> classIfications=classService.getAllClass();
        return classIfications;
    }
}
