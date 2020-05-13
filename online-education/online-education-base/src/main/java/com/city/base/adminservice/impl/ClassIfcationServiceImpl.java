package com.city.base.adminservice.impl;

import com.city.base.entity.ClassIfication;
import com.city.base.entity.Transmission;
import com.city.base.mapper.ClassMapper;
import com.city.base.adminservice.ClassIficationService;
import com.city.base.mapper.ResourceMapper;
import com.city.base.util.FileWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Collection;
@Service
public class ClassIfcationServiceImpl implements ClassIficationService {
    final Integer noOperation=0;//无操作
    final Integer delete=1;//删除
    final Integer operation=2;
    final Integer noId=-1;
    @Autowired
    ClassMapper classMapper;
    @Autowired
    ResourceMapper resourceMapper;
    @Autowired
    FileWrite fileWrite;
    @Override
    public void insertClass(ClassIfication classIfication, MultipartFile file, Transmission transmission) {
        File classPath=new File("./classicon");
        if (!classPath.exists()){
            classPath.mkdir();
        }
        if (!file.isEmpty()) {
            String fileName=file.getOriginalFilename();
            String prefix=fileName.substring(fileName.lastIndexOf("."));
            fileWrite.fileWritePath(file,"./classicon/"+classIfication.getClassName()+prefix);
        }
        if(classMapper.insertClass(classIfication)==1) {
            transmission.setRequest(operation);
        } else {
            transmission.setRequest(noOperation);
        }
        transmission.setProperty("添加");
    }

    @Override
    public Collection<ClassIfication> getPageClass(Integer page, Transmission transmission) {
        transmission.setPage(page);
        transmission.boundaryJudgment(classMapper.selectClassNumber());
        transmission.setRequest(noOperation);
        transmission.setId(noId);
        Collection<ClassIfication> classIfications = classMapper.selectPageClass((transmission.getPage() - 1) * 10);
        return classIfications;
    }

    @Override
    public Collection<ClassIfication> deleteClassById(Integer id, Transmission transmission) {
        ClassIfication classIfication=classMapper.selectClassById(id);
        File classPath=new File("./classicon");
        if (classPath.isDirectory()){
         String[] fileNames=classPath.list();
            for (int i = 0; i <fileNames.length ; i++) {
                if (fileNames[i].contains(classIfication.getClassName())){
                    File file=new File("./classicon/"+fileNames[i]);
                    file.delete();
                    break;
                }
            }
        }
        classMapper.deleteClass(id);
        transmission.setRequest(noOperation);
        Collection<ClassIfication> classIfications = classMapper.selectPageClass((transmission.getPage() - 1) * 10);
        return classIfications;
    }

    @Override
    public Collection<ClassIfication> deleteRequest(Integer page, Integer id, Transmission transmission) {
        transmission.setPage(page);
        transmission.boundaryJudgment(classMapper.selectClassNumber());
        transmission.setRequest(delete);
        transmission.setId(id);
        Collection<ClassIfication> classIfications = classMapper.selectPageClass((page - 1) * 10);
        return classIfications;
    }

    @Override
    public Collection<ClassIfication> updateClass(ClassIfication classIfication, Integer id, Transmission transmission) {
        classMapper.updateClassById(classIfication,id);
        transmission.setRequest(operation);
        transmission.setProperty("修改");
        Collection<ClassIfication> classIfications = classMapper.selectPageClass((transmission.getPage() - 1) * 10);
        return classIfications;
    }

    @Override
    public Collection<ClassIfication> getAllClass() {
        Collection<ClassIfication> classIfications=classMapper.selectAllClass();
        return classIfications;
    }

    @Override
    public ClassIfication updateRequest(int id) {
        ClassIfication classIfication=classMapper.selectClassById(id);
        return classIfication;
    }

    @Override
    public Collection<ClassIfication> getIndex() {
        Collection<ClassIfication> classIfications = classMapper.selectAllClass();
        for (ClassIfication classi : classIfications) {
            classi.setNumber(resourceMapper.selectResourceNumberByClass(classi.getClassName()));
            classi.calculatePercentage(resourceMapper.selectResourceNumber());
        }
        return classIfications;
    }

    @Override
    public Collection<ClassIfication> getClassManage(Transmission transmission) {
        Collection<ClassIfication> classIfications=classMapper.selectPageClass(0);
        transmission.initialization(classMapper.selectClassNumber());
        transmission.setRequest(noOperation);
        return classIfications;
    }
}
