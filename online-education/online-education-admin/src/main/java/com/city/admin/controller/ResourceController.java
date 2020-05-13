package com.city.admin.controller;

import com.city.base.adminservice.ClassIficationService;
import com.city.base.adminservice.ResourceService;
import com.city.base.entity.ClassIfication;
import com.city.base.entity.Resource;
import com.city.base.entity.Transmission;
import com.city.base.util.FileWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Collection;

@Controller
public class ResourceController {

    @Autowired
    ResourceService resourceService;
    @Autowired
    ClassIficationService classService;
    @Autowired
    Transmission transmission;
    /**
     * 添加课程
     * @param resource
     * @param file
     * @param model
     * @return
     */
    @PostMapping("/addresource")
    public String addResoure(@ModelAttribute Resource resource, @RequestParam("file") MultipartFile file, Model model){
        resourceService.addResoure(resource,file,transmission);
        model.addAttribute("transmission",transmission);
        Collection<ClassIfication> classIfications=classService.getAllClass();
        model.addAttribute("classIfications",classIfications);
        return "course-add";
    }

    /**
     * 根据页数返回资源
     * @param ClassIfication
     * @param page
     * @param model
     * @return
     */
    @GetMapping("/course-manage")
    public String selectResourcePage(@RequestParam("classification") String ClassIfication,@RequestParam("page") Integer page, Model model){
        Collection<Resource> resources=resourceService.selectResourcePage(ClassIfication,page,transmission);
        model.addAttribute("transmission",transmission);
        Collection<ClassIfication> classIfications=classService.getAllClass();
        model.addAttribute("classIfications",classIfications);
        model.addAttribute("resources",resources);
        return "course-manage";
    }

    /**
     * 修改推荐
     * @param id
     * @param reference
     * @param ClassIfication
     * @param page
     * @param model
     * @return
     */
    @GetMapping("/resource/reference")
    public String updateReference(@RequestParam("id") Integer id,
                                  @RequestParam("re") Integer reference,
                                  @RequestParam("classification") String ClassIfication,
                                  @RequestParam("page") Integer page,
                                  Model model){

        Collection<Resource> resources=resourceService.updateReference(id,reference,ClassIfication,page,transmission);
        model.addAttribute("transmission",transmission);
        Collection<ClassIfication> classIfications=classService.getAllClass();
        model.addAttribute("classIfications",classIfications);
        model.addAttribute("resources",resources);
        return "course-manage";
    }
    /**
     * 搜索查询
     * @param resource
     * @param model
     * @return
     */
    @PostMapping("/course/select")
    public String selectResource(@ModelAttribute Resource resource,Model model){

        Collection<Resource> resources=resourceService.selectResource(resource,transmission);
        model.addAttribute("resources",resources);
        transmission.initialization(resourceService.selectResourceNumber());
        model.addAttribute("transmission",transmission);
        return "course-manage";
    }

    /**
     * 请求删除
     * @param ClassIfication
     * @param page
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/course-manage/requestdelete")
    public String requestDelete(@RequestParam("classification") String ClassIfication,@RequestParam("page") Integer page,@RequestParam("id") Integer id,Model model){
        Collection<Resource> resources=resourceService.deleteRequest(ClassIfication,page,id,transmission);
        Collection<ClassIfication> classIfications=classService.getAllClass();
        model.addAttribute("classIfications",classIfications);
        model.addAttribute("transmission",transmission);
        model.addAttribute("resources",resources);
        return "course-manage";
    }

    /**
     * 删除信息
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/course-manage/delete")
    public String deleteResource(@RequestParam("id") Integer id,Model model){
        Collection<Resource> resources=resourceService.deleteResource(id,transmission);
        model.addAttribute("transmission",transmission);
        model.addAttribute("resources",resources);
        return "course-manage";
    }

    /**
     * 请求修改
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/course-manage/requestupdate")
    public String requestUpdate(@RequestParam("id") Integer id,Model model){
        Resource resource=resourceService.updateRequest(id,transmission);
        model.addAttribute("resource",resource);
        Collection<ClassIfication> classIfications=classService.getAllClass();
        model.addAttribute("classIfications",classIfications);
        model.addAttribute("transmission",transmission);
        return "course-update";
    }

    /**
     * 修改资源
     * @param resource
     * @param id
     * @param file
     * @param model
     * @return
     */
    @PostMapping("/course-manage/update")
    public String updateResource(@ModelAttribute Resource resource,@RequestParam("id") Integer id,@RequestParam("file") MultipartFile file,Model model){
        Collection<Resource> resources=resourceService.updateResource(resource,id,file,transmission);
        model.addAttribute("transmission",transmission);
        model.addAttribute("resources",resources);
        return "course-manage";
    }

    /**
     * 根据分类查询资源
     * @param ClassIfication
     * @param model
     * @return
     */
    @GetMapping("/course-manage/classification")
    public String classification(@RequestParam("classification") String ClassIfication,Model model){
        Collection<Resource> resources=resourceService.selectResourceByClass(ClassIfication,transmission);
        model.addAttribute("resources",resources);
        Collection<ClassIfication> classIfications=classService.getAllClass();
        model.addAttribute("classIfications",classIfications);
        model.addAttribute("transmission",transmission);
        return "course-manage";
    }
    /**
     * 读取视频资源内容
     * @param fileData  分段的文件
     * @param fileName  文件名称
     * @param fileTotal 文件的总片数
     * @param fileIndex  当前片数
     * @param fileMd5    文件的md5
     * @param fileSize   文件的总大小
     * @param fileChunksize 当前切片的文件大小
     * @param fileSuffix  文件的后缀名
     * @return
     */
    @ResponseBody
    @PostMapping("/resource/put")
    public String putFile(@RequestParam("file_data") MultipartFile fileData,
                          @RequestParam("file_name") String fileName,
                          @RequestParam("file_total") Integer fileTotal,
                          @RequestParam("file_index") Integer fileIndex,
                          @RequestParam("file_md5") String fileMd5,
                          @RequestParam("file_size") String fileSize,
                          @RequestParam("file_chunksize") String fileChunksize,
                          @RequestParam("file_suffix") String fileSuffix,
                          @RequestParam("apped_data") String courseName){

        courseName=courseName.substring(15,courseName.lastIndexOf('"'));
        FileWrite fileWrite = new FileWrite();
        fileWrite.fileAppends("./"+courseName+"/"+fileName,fileData);
        return "true";
    }

    /**
     * 读取资源文件
     * @param name
     * @param numberEpisodes
     * @param model
     * @return
     */
    @GetMapping("/resource/manage")
    public String resourceManagement(@RequestParam("name") String name,
                                     @RequestParam("numberEpisodes") Integer numberEpisodes,
                                     Model model){

        model.addAttribute("name",name);
        model.addAttribute("numberEpisodes",numberEpisodes);

        //读取文件夹下所有资源
        File file=new File("./"+name);
        if (file.isDirectory()){
            String[] fileNames=file.list();
            model.addAttribute("fileNames",fileNames);
        }

        return "resource-management";
    }

    /**
     * 删除选定资源
     * @param name
     * @param numberEpisodes
     * @param filename
     * @param model
     * @return
     */
    @GetMapping("/resource/delete")
    public String deleteResource(@RequestParam("name") String name,
                                 @RequestParam("numberEpisodes") Integer numberEpisodes,
                                 @RequestParam("fileName") String filename,
                                 Model model){

        //删除选定资源
        File file=new File("./"+name+"/"+filename);
        if (file.exists()){
            file.delete();
            if (file.exists()){
                System.gc();
                file.delete();
            }
        }

        //读取文件夹下所有资源
        File file1=new File("./"+name);
        if (file1.isDirectory()){
            String[] fileNames=file1.list();
            model.addAttribute("fileNames",fileNames);
        }

        model.addAttribute("name",name);
        model.addAttribute("numberEpisodes",numberEpisodes);
        return "resource-management";
    }

}
