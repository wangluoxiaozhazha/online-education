package com.city.admin.controller;

import com.city.base.adminservice.ClassIficationService;
import com.city.base.entity.ClassIfication;
import com.city.base.entity.Transmission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Map;

@Controller
public class ClassController {

    final Integer noOperation=0;//无操作
    final Integer delete=1;//删除
    final Integer operation=2;
    final Integer noId=-1;
    @Autowired
    ClassIficationService classService;
    @Autowired
    Transmission transmission;

    /**
     * 插入分类
     * @param classIfication
     * @param map
     * @return
     */
    @PostMapping("/class")
    public String insertClass(@ModelAttribute ClassIfication classIfication, @RequestParam("file") MultipartFile file, Map<String,Object> map){
        classService.insertClass(classIfication,file,transmission);
        map.put("transmission",transmission);
        return "class-add";
    }

    /**
     * 根据分页查询分类
     * @param page
     * @param model
     * @return
     */
    @GetMapping("/class-manage")
    public String selectPageClass(@RequestParam("page") Integer page, Model model){
        Collection<ClassIfication> classIfications = classService.getPageClass(page,transmission);
        model.addAttribute("classIfications", classIfications);
        model.addAttribute("transmission",transmission);
        return "class-manage";
    }

    /**
     * 删除分类
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/class-manage/delete")
    public String deleteClass(@RequestParam("id") Integer id,Model model){
        Collection<ClassIfication> classIfications=classService.deleteClassById(id,transmission);
        model.addAttribute("transmission",transmission);
        model.addAttribute("classIfications", classIfications);
        return "class-manage";
    }

    /**
     * 请求删除
     * @param page
     * @param classID
     * @param map
     * @return
     */
    @GetMapping("/class-manage/requestdelete")
    public String requestDelete(@RequestParam("page") Integer page,@RequestParam("id") Integer classID,Map<String,Object> map){
        Collection<ClassIfication> classIfications =classService.deleteRequest(page,classID,transmission);
        map.put("classIfications", classIfications);
        map.put("transmission",transmission);
        return "class-manage";
    }

    /**
     * 请求修改
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/class-manage/requestupdate")
    public String requestUpdate(@RequestParam("id") Integer id,Model model){
        ClassIfication classIfication=classService.updateRequest(id);
        model.addAttribute("classIfication",classIfication);
        return "class-update";
    }

    /**
     * 修改分类
     * @param classIfication
     * @param id
     * @param model
     * @return
     */
    @PostMapping("/updateclass")
    public String updateClass(@ModelAttribute ClassIfication classIfication,@RequestParam("id") Integer id,Model model){
        Collection<ClassIfication> classIfications=classService.updateClass(classIfication,id,transmission);
        model.addAttribute("transmission",transmission);
        model.addAttribute("classIfications", classIfications);
        return "class-manage";
    }
}
