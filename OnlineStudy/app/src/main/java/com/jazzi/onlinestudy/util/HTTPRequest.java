package com.jazzi.onlinestudy.util;



import android.os.Handler;

import java.util.concurrent.CountDownLatch;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HTTPRequest {

    public static String  getRequest(final String url) throws InterruptedException {
        final String[] responseData = new String[1];
        final CountDownLatch downLatch=new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client=new OkHttpClient();
                    Request request =new Request.Builder()
                            .url(IpConfig.getIp()+url)
                            .build();
                    Response response=client.newCall(request).execute();
                     responseData[0] =response.body().string();
                     downLatch.countDown();
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        }).start();
        downLatch.await();
        return responseData[0];
    }
}
