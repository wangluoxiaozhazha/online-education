package com.city.base.entity;

import java.io.File;

public class FileInfo {
    //分段的文件
    File[] file_data;
    //文件名称
    String file_name;
    //文件的总片数
    Integer file_total;
    //当前片数
    Integer file_index;
    //文件的md5
    String file_md5;
    //文件的总大小
    String file_size;
    //当前切片的文件大小
    String file_chunksize;
    //文件的后缀名
    String file_suffix;

    public File[] getFile_data() {
        return file_data;
    }

    public void setFile_data(File[] file_data) {
        this.file_data = file_data;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public Integer getFile_total() {
        return file_total;
    }

    public void setFile_total(Integer file_total) {
        this.file_total = file_total;
    }

    public Integer getFile_index() {
        return file_index;
    }

    public void setFile_index(Integer file_index) {
        this.file_index = file_index;
    }

    public String getFile_md5() {
        return file_md5;
    }

    public void setFile_md5(String file_md5) {
        this.file_md5 = file_md5;
    }

    public String getFile_size() {
        return file_size;
    }

    public void setFile_size(String file_size) {
        this.file_size = file_size;
    }

    public String getFile_chunksize() {
        return file_chunksize;
    }

    public void setFile_chunksize(String file_chunksize) {
        this.file_chunksize = file_chunksize;
    }

    public String getFile_suffix() {
        return file_suffix;
    }

    public void setFile_suffix(String file_suffix) {
        this.file_suffix = file_suffix;
    }
}
