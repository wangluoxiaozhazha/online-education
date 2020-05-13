package com.jazzi.onlinestudy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private TextView sec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sec =(TextView)findViewById(R.id.sec_view);



        /*瓜皮实现广告秒数
        * 开启4个定时线程刷新秒数
        * 最后一个线程切换活动*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sec.setText(" 3 ");
            }

        },1000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sec.setText(" 2 ");
            }

        },2000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sec.setText(" 1 ");
            }

        },3000);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }


        },5000);



    }



}
