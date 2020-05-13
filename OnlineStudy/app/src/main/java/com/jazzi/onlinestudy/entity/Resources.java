package com.jazzi.onlinestudy.entity;

/*这个类代表滑动窗口中每一张图片的属性以及方法*/
public class Resources {

    private int resourceId;
    private  int numberEpisodes;
    private String courseName;
    private String coverPath;
    private String courseIntroduction;
    private String lecturer;
    private String CoursePath;
    private String courseInformation;

    public int getNumberEpisodes() {
        return numberEpisodes;
    }

    public void setNumberEpisodes(int numberEpisodes) {
        this.numberEpisodes = numberEpisodes;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public String getCourseIntroduction() {
        return courseIntroduction;
    }

    public void setCourseIntroduction(String courseIntroduction) {
        this.courseIntroduction = courseIntroduction;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getCoursePath() {
        return CoursePath;
    }

    public void setCoursePath(String coursePath) {
        CoursePath = coursePath;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getCourseInformation() {
        return courseInformation;
    }

    public void setCourseInformation(String courseInformation) {
        this.courseInformation = courseInformation;
    }
}
