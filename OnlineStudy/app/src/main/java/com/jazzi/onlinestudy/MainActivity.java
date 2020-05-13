package com.jazzi.onlinestudy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jazzi.onlinestudy.adapter.ClassAdapter;
import com.jazzi.onlinestudy.adapter.GlideImageLoader;
import com.jazzi.onlinestudy.adapter.ResourcesAdapter;
import com.jazzi.onlinestudy.adapter.SchoolAdapter;
import com.jazzi.onlinestudy.entity.Classification;
import com.jazzi.onlinestudy.entity.Resources;
import com.jazzi.onlinestudy.entity.School;
import com.jazzi.onlinestudy.util.HTTPRequest;
import com.jazzi.onlinestudy.util.IpConfig;
import com.jazzi.onlinestudy.util.ParseJson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*别忘记导入正确的Toolbar，安卓的Toolbar有多个，会有歧义
* 将实例调入setSupportActionBar应用成功即可*/
public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    /*滑动图片所需的***************************/
    public ViewPager viewPager;
    public TextView title;
    public LinearLayout point ;
    public int imageId[];
    public String suggestion[];
    public List<ImageView> mImageViewList;
    private int prepos=0;
    private NavigationView navView;
    private TextView username1;
    private ImageView imageView;
    private LinearLayout[] classify=new LinearLayout[6];
    private String userId;
    private RecyclerView fruitRecyclerView;
    private  RecyclerView classRecyclerView;
    /*滑动图片所需的***************************/
    private Banner banner;
    private List<Resources> tuijianList=new ArrayList<>();
    /**合作高校所需***********************/
    private List<School> users;
    private RecyclerView schoolLayout;
    /**合作高校所需***********************/



    private DrawerLayout mDrawerLayout;
//    private Fruit[] fruits={new Fruit("人工智能",R.drawable.pingguo),
//        new Fruit("玩转流行音乐",R.drawable.boluo),
//        new Fruit("高等数学",R.drawable.caomei),
//        new Fruit("设计的力量",R.drawable.juzi)};

    private Resources[] fruits;
    private List<Resources> resourceList=new ArrayList<>();
    private List<Classification> classificationList=new ArrayList<>();
    private ResourcesAdapter resourcesAdapter;
    private ClassAdapter classAdapter;
    private SwipeRefreshLayout swipeRefresh;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshFruits();

        banner=(Banner) findViewById(R.id.banner);
        /*滑动图片所需的***************************

        initView();
        initData();
        initMyAdapter();
        滑动图片所需的***************************/
        navView =(NavigationView) findViewById(R.id.nav_view);
        View headerView = navView.getHeaderView(0);
        username1=(TextView)headerView.findViewById(R.id.username1);
        imageView=(ImageView) headerView.findViewById(R.id.icon_image);
        fruitRecyclerView=(RecyclerView)findViewById(R.id.recycle_view);
        classRecyclerView=(RecyclerView)findViewById(R.id.class_view);


        Intent intent=getIntent();
        final String userName=intent.getStringExtra("userName");
        final String uimagePath=intent.getStringExtra("uimagePath");
        userId=intent.getStringExtra("userID");
        if (userName!=null){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Bundle bundle=new Bundle();
                    bundle.putString("userName",userName);
                    bundle.putString("uimagePath",uimagePath);
                    Message message=new Message();
                    message.setData(bundle);
                    handler1.sendMessage(message);
                }
            }).start();
        }

        /*************搜索键所需************/
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*************搜索键所需************/



        /*设置滑动列表布局*/
        mDrawerLayout =(DrawerLayout)findViewById(R.id.drawer_layout);


        /*该功能已经取消，登录放在了浮选按钮上*/
//        /*actionBar默认是在左上角的一个返回箭头，我们改了它的样式与作用
//        * setDisplayHomeAsUpEnabled是让导航按钮显现出来
//        * setHomeAsUpIndicator设置导航按钮的图标*/
//        ActionBar actionBar=getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);


        /*获得滑动窗口实例
        * 开启选项监听器
        * 设置点击选项触发事件，关闭滑动窗口*/

        /*选中清除缓存项*/
        navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_call://注册事件
                        Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_mylove://我的喜欢课程
                        Intent intent1=new Intent(MainActivity.this,ClassifyActivity.class);
                        intent1.putExtra("ClassName","我的课程");
                        intent1.putExtra("no","2");
                        intent1.putExtra("userId",userId);
                        startActivity(intent1);
                        break;
                    case R.id.nav_about://关于
                        Intent intent2 = new Intent(MainActivity.this,SetIpActivity.class);
                        startActivity(intent2);
                        break;

                }
//                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        //设置头部监听事件


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        FloatingActionButton fab=(FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            /*触发一个动画效果的提示框
            * 用make创建Snackbar对象，
            * 参数1：当前布局任意的View，Snackbar会利用参数1找到最外层布局，用于展示Snackbar
            * 参数2：显示的内容
            * 参数3：显示的时长*/
            @Override
            public void onClick(View view) {
                /*登录放在此按钮上，原来的功能弃用*/
//                Snackbar.make(view,"删除数据",Snackbar.LENGTH_SHORT)
//                        .setAction("撤销", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                Toast.makeText(MainActivity.this,"数据已经还原",
//                                        Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .show();

                /*开启滑动列表*/
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        /*初始化fruitList
        * 创建一个2列的网格布局
        * 放入可滑动布局recyclerView
        * 创建水果适配器
        * 适配器+控件=最终结果*/
//        initFruits();
//        RecyclerView fruitRecyclerView=(RecyclerView) findViewById(R.id.recycle_view);
//        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
//        fruitRecyclerView.setLayoutManager(layoutManager);
//        fruitAdapter =new FruitAdapter(fruitList);
//        fruitRecyclerView.setAdapter(fruitAdapter);


        /*setColorSchemeResources来设置下来刷新进度条的颜色
        * 设置下拉刷新器，当触发下拉的时候，就会调用这个监听器的onRefresh()方法*/
        swipeRefresh=(SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });





        /**合作高校所需***********************/
        initSchools();
        schoolLayout=(RecyclerView) findViewById(R.id.school_view);
        LinearLayoutManager l=new LinearLayoutManager(this);
        //设置为x轴方向滑动
        l.setOrientation(LinearLayoutManager.HORIZONTAL);
        schoolLayout.setLayoutManager(l);//指定内部局为线性布局
        //创建适配器
        SchoolAdapter SchoolAdapter =new SchoolAdapter(users);
        //适配器+控件==最终结果
       /* 论起一个精确名字的重要性*/
        schoolLayout.setAdapter(SchoolAdapter);



    }

    /**合作高校所需***********************/
    private void initSchools(){
        users =new ArrayList<School>();
        users.add(new School("清华大学",R.mipmap.qinghua));
        users.add(new School("北京大学",R.mipmap.beida));
        users.add(new School("厦门大学",R.mipmap.xiada));
        users.add(new School("武汉大学",R.mipmap.wuda));
        users.add(new School("人民大学",R.mipmap.renda));
        users.add(new School("浙江大学",R.mipmap.zheda));
    }



    /**合作高校所需***********************/






    /*先是开启了一个线程，然后将线程沉睡两秒
    * 沉睡结束后，runOnUiThread方法将线程切换回主线程
    * 然后调用 initFruits();重新生成图片数据
    * 接着再调用notifyDataSetChanged将滑动菜单适配器刷新数据
    * 最后隐藏刷新进度条，表示刷新时间结束*/
    private void refreshFruits(){
        new Thread(new Runnable() {
            @Override
            public void run() {
//                try {
//                    Thread.sleep(2000);
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        resourceList.clear();
                        classificationList.clear();
                        tuijianList.clear();
                        String data= null;
                        try {
                            data = HTTPRequest.getRequest("/resource/selectall");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        resourceList= ParseJson.parseResourceJson(data);
                        ReHandler.sendMessage(new Message());

                        String classData= null;
                        try {
                            classData = HTTPRequest.getRequest("/class/selectclass");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        classificationList=ParseJson.parseClassJson(classData);
                        classHandler.sendMessage(new Message());

                        String tuijianData=null;
                        try {
                            tuijianData=HTTPRequest.getRequest("/resource/reference");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        tuijianList=ParseJson.parseResourceJson(tuijianData);
                        bannerHandler.sendMessage(new Message());

                        swipeRefresh.setRefreshing(false);
                    }
                });
            }

        }).start();
    }

    /*将fruitList容器清空
    * 并且随机取fruits中50个可重复的Fruit对象加入fruitList中*/
    private void initFruits(){
//        fruitList.clear();
//        for(int i=0;i<12;++i){
//            Random random=new Random();
//            int index=random.nextInt(fruits.length);
//            fruitList.add(fruits[index]);
//        }

        Collections.reverse(resourceList);

    }

    /*getMenuInflater()方法先得到MenuInflater对象，再用这个对象inflate()方法给活动添加菜单
    inflate()方法接收两个参数
    参数1：资源文件
    参数2：指定我们菜单项将添加到哪个Menu对象中
    menu可能会在运行时自动接收活动中的菜单对象，然后对菜单对象进行显示操作。
    默认就是toolbar的菜单对象。
    返回true，将显示菜单
    返回flase，不会显示菜单
    自动加载菜单方法，自动运行。*/
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.nav_menu,menu);
        return true;
    }

    /*这个方法中处理菜单中各个按钮的点击事件
    * 左上角返回按钮的id默认是android.R.id.home
    * openDrawer即把这个滑动列表展示出来*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return true;
    }

    /*建立一个适配器*/
    public class MyAdapter extends PagerAdapter {

        /*获取需要展示在ViewPager的View总数*/
        @Override
        public int getCount() {
            return mImageViewList.size();
        }



        /*返回的是view复用规则
         * 判断当前轮播框显示的view是否与方法instantiateltem返回来的object相同
         * 诺相同则会在viewPage里显示出对应的view了
         *
         * 即判断当拖动到一定距离后，开始触发切换事件*/
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }


        /*向ViewGroup容器里添加view
         * 返回需要展示的view
         *
         * position表示拖动到的下一个位置i
         * 为了防止出现错误，%size防止溢出*/

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int newPosition=position%mImageViewList.size();
            ImageView imageView=mImageViewList.get(newPosition);
            container.addView(imageView);
            return imageView;
        }

        /*移除ViewGroup中已经展示过的view
         * 销毁已经展示的view*/
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }



    }


    /*以下四个方法实现了监听器*/


    /*当条目被选中时调用
     * 注：切换图片后自动选中切换后的图片*/
    @Override
    public void onPageSelected(int position) {
//        int newPosition=position%mImageViewList.size();
//        title.setText(suggestion[newPosition]);
//
//        point.getChildAt(prepos).setEnabled(false);
//        point.getChildAt(newPosition).setEnabled(true);
//        prepos=newPosition;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int newPosition=position%mImageViewList.size();
        title.setText(suggestion[newPosition]);

        point.getChildAt(prepos).setEnabled(false);
        point.getChildAt(newPosition).setEnabled(true);
        prepos=newPosition;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    /*滑动图片所需的***************************/



    private Handler ReHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            GridLayoutManager layoutManager=new GridLayoutManager(MainActivity.this,2);
            fruitRecyclerView.setLayoutManager(layoutManager);
            resourcesAdapter =new ResourcesAdapter(resourceList);
            fruitRecyclerView.setAdapter(resourcesAdapter);
        }
    };

    private Handler handler1=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            username1.setText(msg.getData().getString("userName"));
        }
    };

    private Handler classHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            LinearLayoutManager layoutManager=new LinearLayoutManager(MainActivity.this);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            classRecyclerView.setLayoutManager(layoutManager);
            classAdapter=new ClassAdapter(classificationList);
            classRecyclerView.setAdapter(classAdapter);
    }};

    private Handler bannerHandler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            System.out.println("---------------------------------------");
            System.out.println(tuijianList.size());
            List<String> images=new ArrayList<>();
            List<String> titles=new ArrayList<>();
            for (int i = 0; i <tuijianList.size() ; i++) {
                String s=IpConfig.getIp()+"/get/coverImage/?name="+tuijianList.get(i).getCourseName()+"&coverPath="+tuijianList.get(i).getCoverPath();
                images.add(s);
                titles.add(tuijianList.get(i).getCourseInformation());
            }
            banner.setImageLoader(new GlideImageLoader());
            banner.setImages(images);
            banner.setBannerTitles(titles);
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            banner.setDelayTime(4000);
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Resources fruit=tuijianList.get(position);
                    Intent intent =new Intent(MainActivity.this, ResourcesActivity.class);
                    intent.putExtra("id",fruit.getResourceId());
                    intent.putExtra(ResourcesActivity.FRUIT_NAME,fruit.getCourseName());
                    intent.putExtra("image_url",fruit.getCoverPath());
                    intent.putExtra("course_controduction",fruit.getCourseIntroduction());
                    intent.putExtra("course_teacher",fruit.getLecturer());
                    intent.putExtra("numberEpisodes",fruit.getNumberEpisodes());
                    intent.putExtra("CoursePath",fruit.getCoursePath());
                    startActivity(intent);
                }
            });
            banner.start();
        }
    };
    public Bitmap getHttpBitmap(String url){//根据网络路径获得图片
        URL myFileURL;
        Bitmap bitmap=null;

        try {
            myFileURL=new URL(url);
            HttpURLConnection conn=(HttpURLConnection)myFileURL.openConnection();

            conn.setConnectTimeout(6000);
            conn.setDoInput(true);
            conn.setUseCaches(false);

            InputStream is=conn.getInputStream();
            bitmap=BitmapFactory.decodeStream(is);
            is.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return bitmap;
    }

}
