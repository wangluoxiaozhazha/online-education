package com.city.base.adminservice.impl;

import com.city.base.entity.ClassIfication;
import com.city.base.entity.Resource;
import com.city.base.entity.Transmission;
import com.city.base.mapper.ClassMapper;
import com.city.base.mapper.ResourceMapper;
import com.city.base.adminservice.ResourceService;
import com.city.base.util.FileWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Collection;
@Service
public class ResourceServiceImpl implements ResourceService {
    final Integer noOperation=0;//无操作
    final Integer delete=1;//删除
    final Integer operation=2;
    final Integer noId=-1;
    @Autowired
    FileWrite fileWrite;
    @Autowired
    ResourceMapper resourceMapper;
    @Autowired
    ClassMapper classMapper;
    @Override
    public Collection<ClassIfication> addResoure(Resource resource, MultipartFile file, Transmission transmission) {
        File coverPath=new File("./"+resource.getCourseName());
        if (!coverPath.exists()) {
            coverPath.mkdir();
        }
        if (!file.isEmpty()) {
            resource.setCoverPath(file.getOriginalFilename());
        }

        fileWrite.fileWritePath(file,"./"+resource.getCourseName()+"/"+resource.getCoverPath());
        resource.setReference("0");
        resourceMapper.insertResource(resource);
        transmission.setRequest(operation);
        transmission.setProperty("添加");
        Collection<ClassIfication> classIfications=classMapper.selectAllClass();
        return classIfications;
    }

    @Override
    public Collection<Resource> selectResourcePage(String classIfication, Integer page, Transmission transmission) {
        transmission.setPage(page);
        transmission.setRequest(noOperation);
        Collection<Resource> resources;
        if (classIfication.equals("全部")) {
            transmission.boundaryJudgment(resourceMapper.selectResourceNumber());
            resources=resourceMapper.selectResourceByPage((transmission.getPage()-1)*10);
        }else {
            transmission.boundaryJudgment(resourceMapper.selectResourceNumberByClass(classIfication));
            resources=resourceMapper.selectResourceByClass(classIfication,(transmission.getPage()-1)*10);
        }
        return resources;
    }

    @Override
    public Collection<Resource> selectResource(Resource resource, Transmission transmission) {
        Collection<Resource> resources=resourceMapper.getResourceByName(resource);
        transmission.initialization(resourceMapper.selectResourceNumber());
        return resources;
    }

    @Override
    public Collection<Resource> deleteRequest(String classIfication, Integer page,Integer id, Transmission transmission) {
        transmission.setPage(page);
        Collection<Resource> resources;
        if (classIfication.equals("全部")) {
            transmission.boundaryJudgment(resourceMapper.selectResourceNumber());
            resources=resourceMapper.selectResourceByPage((transmission.getPage()-1)*10);
        }else {
            transmission.boundaryJudgment(resourceMapper.selectResourceNumberByClass(classIfication));
            resources=resourceMapper.selectResourceByClass(classIfication,(transmission.getPage()-1)*10);
        }
        transmission.setId(id);
        transmission.setRequest(delete);
        return resources;
    }

    @Override
    public Collection<Resource> deleteResource(Integer id, Transmission transmission) {
        Resource resource=resourceMapper.getResourceById(id);
        String path="./"+resource.getCourseName();
        fileWrite.fileDelete(path);
        resourceMapper.deleteResourceById(id);
        transmission.setRequest(noOperation);
        Collection<Resource> resources=resourceMapper.selectResourceByPage((transmission.getPage()-1)*10);
        return resources;
    }

    @Override
    public Resource updateRequest(Integer id, Transmission transmission) {
        Resource resource=resourceMapper.getResourceById(id);
        transmission.setRequest(noOperation);
        return resource;
    }

    @Override
    public Collection<Resource> updateResource(Resource resource, Integer id, MultipartFile file, Transmission transmission) {
        //修改文件夹名
        Resource resource1=resourceMapper.getResourceById(id);
        File resourcePath=new File("./"+resource1.getCourseName());
        if (resourcePath.exists()){
            resourcePath.renameTo(new File("./"+resource.getCourseName()));
        }
        //有新上传的封面
        if (!file.isEmpty()){
            File deleteFile=new File("./"+resource.getCourseName()+"/"+resource1.getCoverPath());
            if (deleteFile.exists()){
                deleteFile.delete();//删除老封面
            }
            resource.setCoverPath(file.getOriginalFilename());
            //写入新封面
            fileWrite.fileWritePath(file,"./"+resource.getCourseName()+"/"+resource.getCoverPath());

        }else {
            resource.setCoverPath(resource1.getCoverPath());
        }
        resource.setResourceID(id);
        resourceMapper.updateResource(resource);
        transmission.setRequest(operation);
        transmission.setProperty("修改");

        Collection<Resource> resources=resourceMapper.selectResourceByPage((transmission.getPage()-1)*10);
        return resources;
    }

    @Override
    public Collection<Resource> selectResourceByClass(String ClassIfication,Transmission transmission) {
        Collection<Resource> resources;
        if (ClassIfication.equals("全部")) {
            resources = resourceMapper.selectResourceByPage(0);
            transmission.initialization(resourceMapper.selectResourceNumber());
        }
        else {
            resources = resourceMapper.selectResourceByClass(ClassIfication, 0);
            transmission.initialization(resourceMapper.selectResourceNumberByClass(ClassIfication));
        }
        transmission.setRequest(noOperation);
        transmission.setClassification(ClassIfication);
        return resources;
    }

    @Override
    public int selectResourceNumber() {
        int number=resourceMapper.selectResourceNumber();
        return number;
    }

    @Override
    public Collection<Resource> getResourceManage(Transmission transmission) {
        transmission.initialization(resourceMapper.selectResourceNumber());
        transmission.setClassification("全部");
        Collection<Resource> resources=resourceMapper.selectResourceByPage((transmission.getPage()-1)*10);
        return resources;
    }

    @Override
    public Collection<Resource> updateReference(Integer id, Integer reference, String ClassIfication, Integer page,Transmission transmission) {
        if (reference==1){
            reference=0;
        }else {
            reference=1;
        }
        resourceMapper.updateReference(id,reference);
        return selectResourcePage(ClassIfication,page,transmission);
    }
}
