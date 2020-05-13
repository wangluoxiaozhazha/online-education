package com.city.base.adminservice;

import com.city.base.entity.ClassIfication;
import com.city.base.entity.Transmission;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

/**
 * 分类相关业务逻辑
 */
public interface ClassIficationService {
    /**
     * 插入一个分类
     * @param classIfication
     * @param transmission
     */
    public void insertClass(ClassIfication classIfication, MultipartFile file,Transmission transmission);

    /**
     * 根据页数获得分类
     * @param page
     * @param transmission
     * @return
     */
    public Collection<ClassIfication> getPageClass(Integer page,Transmission transmission);

    /**
     * 根据id删除分类
     * @param id
     * @param transmission
     * @return
     */
    public Collection<ClassIfication> deleteClassById(Integer id,Transmission transmission);

    /**
     * 获得请求删除的页面
     * @param page
     * @param id
     * @param transmission
     * @return
     */
    public Collection<ClassIfication> deleteRequest(Integer page,Integer id,Transmission transmission);

    /**
     * 修改分类
     * @param classIfication
     * @param id
     * @param transmission
     * @return
     */
    public Collection<ClassIfication> updateClass(ClassIfication classIfication, Integer id, Transmission transmission);

    /**
     * 获得所有分类
     * @return
     */
    public Collection<ClassIfication> getAllClass();

    /**
     * 修改请求
     * @param id
     * @return
     */
    public ClassIfication updateRequest(int id);

    /**
     * 获得主页数据
     * @return
     */
    public Collection<ClassIfication> getIndex();

    /**
     * 获得分类管理数据
     * @param transmission
     * @return
     */
    public Collection<ClassIfication> getClassManage(Transmission transmission);
}
