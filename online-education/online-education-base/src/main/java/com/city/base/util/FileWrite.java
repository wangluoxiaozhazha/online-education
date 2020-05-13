package com.city.base.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 *Paths：通过get()方法返回一个Path对象，Path用于表示文件路径和文件。
 *Files：提供了大量处理文件的方法，例如文件复制、读取、写入，获取文件属性、快捷遍历文件目录等.....
 *Files 和 Paths是Java8新增的工具类，在处理文件方面功能非常强大。
 */
@Service
public class FileWrite {

    /**
     * 文件写入
     * @param file 要写入的文件
     * @param path 要写入的路径
     */
    public void fileWritePath(MultipartFile file,String path){

        try {
            byte[] bytes = file.getBytes();
            Files.write(Paths.get(path),bytes);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * 文件追加
     * @param path 追加文件的路径
     * @param file 追加的文件
     */
    public void fileAppends(String path,MultipartFile file){

        File file1=new File(path);
        if (file1.exists()){
            try {
                file1.createNewFile();
            }catch (IOException e){
               e.printStackTrace();
            }

        }
        try {
            RandomAccessFile rFile=new RandomAccessFile(path,"rw");
            rFile.seek(rFile.length());
            rFile.write(file.getBytes());

            rFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }

    }

    /**
     * 删除文件夹下所有文件
     * @param path 要删除的文件夹路径
     */
    public void fileDelete(String path){
        File f = new File(path);
        if(f.exists() && f.isDirectory()){
            if(f.listFiles().length==0){
                f.delete();
        }else{
          //若有则把文件放进数组，并判断是否有下级目录
        File delFile[]=f.listFiles();
        int i =f.listFiles().length;
        for(int j=0;j<i;j++){
            if(delFile[j].isDirectory()){
                //递归调用del方法并取得子目录路径
                fileDelete(delFile[j].getAbsolutePath());
            }
            delFile[j].delete();
        }
        f.delete();
    }
}
    }
}
