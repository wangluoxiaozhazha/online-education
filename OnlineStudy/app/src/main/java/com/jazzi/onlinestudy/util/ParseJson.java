package com.jazzi.onlinestudy.util;

import android.os.Handler;
import android.os.Message;

import com.jazzi.onlinestudy.entity.Classification;
import com.jazzi.onlinestudy.entity.Resources;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParseJson {
    public static List parseResourceJson(String jsonData){
        List<Resources> resourcesList=new ArrayList<>();
        try{
            JSONArray jsonArray =new JSONArray(jsonData);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                int resourceID=jsonObject.getInt("resourceID");
                String courseName=jsonObject.getString("courseName");
                String coverPath=jsonObject.getString("coverPath");
                String courseIntroduction=jsonObject.getString("courseIntroduction");
                String teacher=jsonObject.getString("lecturer");
                int numberEpisodes=Integer.parseInt(jsonObject.getString("numberEpisodes"));
                String courseInformation=jsonObject.getString("courseInformation");

                Resources tmp=new Resources();
                tmp.setResourceId(resourceID);
                tmp.setCourseName(courseName);
                tmp.setCoverPath(coverPath);
                tmp.setCourseIntroduction(courseIntroduction);
                tmp.setLecturer(teacher);
                tmp.setNumberEpisodes(numberEpisodes);
                tmp.setCourseInformation(courseInformation);
                resourcesList.add(tmp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resourcesList;
    }

    public static List parseClassJson(String jsonData){
        List<Classification> classificationList=new ArrayList<>();
        try {
            JSONArray jsonArray =new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                Classification classification=new Classification();
                classification.setClassName(jsonObject.getString("className"));
                classificationList.add(classification);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return classificationList;
    }
}
