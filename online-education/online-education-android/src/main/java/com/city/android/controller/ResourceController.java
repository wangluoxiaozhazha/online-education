package com.city.android.controller;

import com.city.base.entity.ClassIfication;
import com.city.base.entity.Resource;
import com.city.base.mapper.ResourceMapper;
import com.city.base.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;

@Controller
public class ResourceController {

    @Autowired
    ResourceMapper resourceMapper;
    @Autowired
    UserMapper userMapper;
    @ResponseBody
    @GetMapping("/resource/selectall")
    public Collection<Resource> selectAllResource(){
        return resourceMapper.selectAllResource();
    }

    @ResponseBody
    @GetMapping("/resource/select")
    public Collection<Resource> selectResource(@ModelAttribute ClassIfication classIfication){
        return resourceMapper.selectResourceByClassApp(classIfication.getClassName());
    }

    @ResponseBody
    @GetMapping("/resource/selectrecommend")
    public Collection<Resource> selectRecommend(){

        Integer number=resourceMapper.selectResourceNumber();

        return  resourceMapper.selectResourceByRecommend(number-4);
    }

    @ResponseBody
    @GetMapping("/resource/userlove")
    public Collection<Resource> selectUserLove(@RequestParam("id")Integer id){

        String history=userMapper.selectUserLikeById(id);
        String[] historys=history.split(",");
        Collection<Resource> resources=new ArrayList<>();
        for(int i=0;i<historys.length;i++){
            resources.add(resourceMapper.getResourceById(Integer.parseInt(historys[i])));
        }
        return resources;
    }
    @ResponseBody
    @RequestMapping(value = "/get/coverImage",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getCoverImage(@RequestParam("name") String name,@RequestParam("coverPath")String coverPath) throws IOException {

        File file=new File("./"+name+"/"+coverPath);
        InputStream inputStream=new FileInputStream(file);
        byte[] bytes=new byte[inputStream.available()];
        inputStream.read(bytes,0,inputStream.available());
        inputStream.close();
        return bytes;
    }

    @ResponseBody
    @GetMapping(value = "/get/classImage",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getClassImage(@RequestParam("className") String className) throws IOException {

        File file=null;
        File classPath=new File("./classicon");
        if (classPath.isDirectory()){
            String[] fileNames=classPath.list();
            for (int i = 0; i <fileNames.length ; i++) {
                if (fileNames[i].contains(className)){
                    file=new File("./classicon/"+fileNames[i]);
                    break;
                }
            }

            if (file!=null){
                InputStream inputStream=new FileInputStream(file);
                byte[]  bytes=new byte[inputStream.available()];
                inputStream.read(bytes,0,inputStream.available());
                inputStream.close();
                return bytes;
            }
        }
        return null;
    }
    @ResponseBody
    @RequestMapping(value = "/getVideo")
    public byte[] getVideo(@RequestParam("resourceName")String resourceName,@RequestParam("id")String id ,HttpServletResponse response)throws IOException {

        File file=null;
        String path="./"+resourceName;
        File resourcePath = new File(path);
        if (resourcePath.isDirectory()){
            String[] fileNames=resourcePath.list();
            for (int i = 0; i <fileNames.length ; i++) {
                if (fileNames[i].substring(0,fileNames[i].lastIndexOf(".")).equals(id)){
                    file=new File("./"+resourceName+"/"+fileNames[i]);
                    response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileNames[i], "UTF-8"));
                    break;
                }
            }
        }
        //response.setHeader("Content-Type", "video/mp4");
        response.setContentType("application/octet-stream");
        if (file!=null) {
            InputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            inputStream.close();
            return bytes;
        }
        return null;
    }

    @ResponseBody
    @GetMapping("/resource/reference")
    public Collection<Resource> getReference(){
       return resourceMapper.selectResourceByReference();
    }
}
