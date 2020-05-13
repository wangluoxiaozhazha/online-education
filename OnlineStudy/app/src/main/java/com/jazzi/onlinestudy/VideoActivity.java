package com.jazzi.onlinestudy;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.jazzi.onlinestudy.CustomMedia.JZMediaIjk;
import com.jazzi.onlinestudy.util.IpConfig;

import java.net.URL;

import cn.jzvd.JZMediaInterface;
import cn.jzvd.JZUtils;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;


public class VideoActivity extends AppCompatActivity {


    private JzvdStd myJzvdStd;
    private int id;
    private String resourceName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_activity);
        myJzvdStd=(JzvdStd) findViewById(R.id.jz_video);
        Intent intent=getIntent();
        id=intent.getIntExtra("id",0);
        resourceName=intent.getStringExtra("resourceName");
        myJzvdStd.setUp(IpConfig.getIp()+"/getVideo/?resourceName="+resourceName+"&id="+id
                , resourceName+" "+id, JzvdStd.SCREEN_NORMAL, JZMediaIjk.class);
        myJzvdStd.posterImageView.setImageResource(R.drawable.gg);
        myJzvdStd.startVideo();
        //initView();
    }

//    private void initView() {
//        String url = IpConfig.getIp()+"/getVideo/?resourceName="+resourceName+"&id="+id;
//        //设置视频控制器
//        videoView.setMediaController(new MediaController(this));
//
//        //播放完成回调
//
//
//        videoView.setVideoPath(url);
//        videoView.requestFocus();
//        videoView.start();
//    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }
}
