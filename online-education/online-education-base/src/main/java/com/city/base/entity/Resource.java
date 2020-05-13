package com.city.base.entity;

/*
 *资源类
 */
public class Resource {

    private Integer ResourceID;//课程id
    private String CourseName;//课程名称
    private String Lecturer;//作者
    private String ClassIfication;//分类
    private String NumberEpisodes;//集数
    private String CourseInformation;//信息
    private String CourseIntroduction;//简介
    private String CoverPath;//封面路径
    private String Reference;//推荐

    public Integer getResourceID() {
        return ResourceID;
    }

    public void setResourceID(Integer resourceID) {
        ResourceID = resourceID;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getLecturer() {
        return Lecturer;
    }

    public void setLecturer(String lecturer) {
        Lecturer = lecturer;
    }

    public String getClassIfication() {
        return ClassIfication;
    }

    public void setClassIfication(String classIfication) {
        ClassIfication = classIfication;
    }

    public String getNumberEpisodes() {
        return NumberEpisodes;
    }

    public void setNumberEpisodes(String numberEpisodes) {
        NumberEpisodes = numberEpisodes;
    }

    public String getCourseInformation() {
        return CourseInformation;
    }

    public void setCourseInformation(String courseInformation) {
        CourseInformation = courseInformation;
    }

    public String getCourseIntroduction() {
        return CourseIntroduction;
    }

    public void setCourseIntroduction(String courseIntroduction) {
        CourseIntroduction = courseIntroduction;
    }

    public String getCoverPath() {
        return CoverPath;
    }

    public void setCoverPath(String coverPath) {
        CoverPath = coverPath;
    }

    public String getReference() {
        return Reference;
    }

    public void setReference(String reference) {
        Reference = reference;
    }
}
