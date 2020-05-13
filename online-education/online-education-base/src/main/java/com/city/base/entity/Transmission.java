package com.city.base.entity;


import com.city.base.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/*
 *传输数据类
 */
@Component
public class Transmission {
    final private Integer indexPage=1;//首页
    private Integer endPage;//尾页
    private Integer page;//当前页
    private Integer request;//对话框请求
    private Integer id;//请求操作的id
    private String property;//添加or修改
    private String classification;//当前分类

    @Autowired
    ClassMapper classMapper;
    @Bean
    public Transmission getTransmission(){
        return new Transmission();
    }
    public void boundaryJudgment(Integer number){
        if (page<1)//左边界
            page=indexPage;
//////////////////////////////////////////////////////////////////////////////////
        int EndPage=number;//尾页确定
        if (EndPage%10==0)
            endPage=EndPage/10;
        else
            endPage=EndPage/10+1;
//////////////////////////////////////////////////////////////////////////////////
        if (page>endPage)//右边界
            page=endPage;
    }
    public void initialization(Integer number){//初始化
        this.setRequest(0);
        this.setId(-1);
        this.setPage(this.getIndexPage());
        this.boundaryJudgment(number);
    }
    public Integer getIndexPage() {
        return indexPage;
    }

    public Integer getEndPage() {
        return endPage;
    }

    public void setEndPage(Integer endPage) {
        this.endPage = endPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRequest() {
        return request;
    }

    public void setRequest(Integer request) {
        this.request = request;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }
}
