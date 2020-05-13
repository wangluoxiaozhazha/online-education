package com.jazzi.onlinestudy;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jazzi.onlinestudy.util.IpConfig;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ResourcesActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String FRUIT_NAME ="fruit_name";
    public static final String FRUIT_IMAGE_ID="fruit_image_id";
    public String CoursePath;
    private FloatingActionButton loveButton;
    private Integer id;
    private String fruitName;
    @Override
    /*设置这个活动的背景布局activity_fruit
    * 得到了这个从上个活动传来的上下文对象实例
    * 取得实例中水果的名称，水果图片的id号
    * */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        loveButton=(FloatingActionButton)findViewById(R.id.love_button);

        Intent intent =getIntent();

        //获得任课教师的信息
        String teacher=intent.getStringExtra("course_teacher");



        loveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendRequestWithOkHttp();
                Toast.makeText(ResourcesActivity.this,"已添加至我喜欢",Toast.LENGTH_LONG).show();
            }
        });
        //添加按钮
        // 生成一个LinearLayout，作为布局容器来动态添加3个Button
        LinearLayout layout1 = (LinearLayout) findViewById (R.id.class_button11);
        LinearLayout layout2 = (LinearLayout) findViewById (R.id.class_button22);
        LinearLayout layout3 = (LinearLayout) findViewById (R.id.class_button33);
        List<LinearLayout> L=new ArrayList<>();
        L.add(layout1);
        L.add(layout2);
        L.add(layout3);

        int lessons=intent.getIntExtra("numberEpisodes",0);
        for (int i=0;i<lessons;i++){
            Button btn = new Button(this);
            btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            btn.setText(i+1+"");
            btn.setId(i+1);
            L.get(i%3).addView(btn);
            btn.setOnClickListener(this);
        }


        fruitName=intent.getStringExtra(FRUIT_NAME);
        //int fruitImageId=intent.getIntExtra(FRUIT_IMAGE_ID,0);
        //获得intent中图片的服务器url
        id=intent.getIntExtra("id",0);
        String imageUrl=intent.getStringExtra("image_url");
        String courseImageUrl= IpConfig.getIp()+"/get/coverImage/?name="+fruitName+"&coverPath="+imageUrl;

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView fruitImageView=(ImageView)findViewById(R.id.fruit_image_view);
        TextView fruitContentText=(TextView)findViewById(R.id.fruit_content_text);

        /*设置当前活动的标题栏为toolbar
        * 为标题栏设置系统自带的返回主页的按钮*/
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        /*设置可折叠布局的标题为水果的名称
        * 并把上个活动点击的图像放入ImageView布局中
        * 把上个活动点击图片的名字重复组成一串数字放入TextView中*/
        collapsingToolbarLayout.setTitle(fruitName);
        Glide.with(this).load(courseImageUrl).into(fruitImageView);
        String fruitContent=generateFruitContent(fruitName);

        //载入课程介绍
        String con="任课教师："+teacher+"\n\n课程介绍:\n"+fruitContent;
        fruitContentText.setText(con);
    }

    /*生成水果内容
    * 将水果内容的字符串循环拼接500次，生成了一个较长的字符串，放在TextView上面
    * 改成了从intent中取课程介绍*/
    private String generateFruitContent(String fruitName){
        Intent intent =getIntent();

        StringBuilder fruitContent =new StringBuilder();

        fruitContent.append(intent.getStringExtra("course_controduction"));
        CoursePath=intent.getStringExtra("CoursePath");
        return fruitContent.toString();
    }

    /*系统标题栏上有系统自带的HomeAsUp按钮
    * 当点击了这个按钮，就会调用finish()方法关闭当前的活动
    * 返回上一个活动
    * 如果不是，就加载它父类的方法，对相应的点击操作相应*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v){

        Intent intent=new Intent(ResourcesActivity.this,VideoActivity.class);
        intent.putExtra("id",v.getId());
        intent.putExtra("resourceName",fruitName);
        startActivity(intent);
    }

    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody requestBody=new FormBody.Builder()
                        .add("id",UserInformation.id)
                        .add("like",id.toString())
                        .build();
                try {
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder()
                            .url(IpConfig.getIp()+"/user/updatelike")
                            .post(requestBody)
                            .build();
                    Response response=client.newCall(request).execute();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
