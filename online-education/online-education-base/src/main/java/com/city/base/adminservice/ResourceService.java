package com.city.base.adminservice;

import com.city.base.entity.ClassIfication;
import com.city.base.entity.Resource;
import com.city.base.entity.Transmission;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public interface ResourceService {
    /**
     * 添加一个资源
     * @param resource
     * @param file
     * @param transmission
     * @return
     */
    public Collection<ClassIfication> addResoure(Resource resource, MultipartFile file, Transmission transmission);

    /**
     * 查询资源并分页
     * @param classIfication
     * @param page
     * @param transmission
     * @return
     */
    public Collection<Resource> selectResourcePage(String classIfication,Integer page,Transmission transmission);

    /**
     * 查询资源
     * @param resource
     * @param transmission
     * @return
     */
    public Collection<Resource> selectResource(Resource resource,Transmission transmission);

    /**
     * 删除请求
     * @param classIfication
     * @param page
     * @param transmission
     * @return
     */
    public Collection<Resource> deleteRequest(String classIfication,Integer page,Integer id,Transmission transmission);

    /**
     * 删除资源
     * @param id
     * @param transmission
     * @return
     */
    public Collection<Resource> deleteResource(Integer id,Transmission transmission);

    /**
     * 修改请求
     * @param id
     * @param transmission
     * @return
     */
    public Resource updateRequest(Integer id,Transmission transmission);

    /**
     * 修改资源
     * @param resource
     * @param id
     * @param file
     * @param transmission
     * @return
     */
    public Collection<Resource> updateResource(Resource resource,Integer id,MultipartFile file,Transmission transmission);

    /**
     * 根据分类查资源
     * @param ClassIfication
     * @return
     */
    public Collection<Resource> selectResourceByClass(String ClassIfication,Transmission transmission);

    /**
     * 查询资源数
     * @return
     */
    public int selectResourceNumber();

    /**
     * 获得资源管理
     * @param transmission
     * @return
     */
    public Collection<Resource> getResourceManage(Transmission transmission);

    /**
     * 修改推荐
     * @param id
     * @param reference
     * @param ClassIfication
     * @param page
     * @param transmission
     * @return
     */
    public Collection<Resource> updateReference(Integer id,Integer reference,String ClassIfication,Integer page,Transmission transmission);
}
