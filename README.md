# Online education(在线教育)

### 项目介绍

------

Online education是我在学校期间开发的一款视频点播系统，由Android客户端和 Java服务端组成。

我们可以在后台管理系统对整个系统内容进行管理：

可以对分类，资源进行增删改查，也可以对资源进行推荐设置(被推荐的资源会在Android客户端的主页中的Banner上显示)，可以在浏览器上对视频文件进行Web上传(使用分片技术)，要知道浏览器在文件上传方面做了诸多限制，还可以对用户进行管理，重置密码等等。

用户在客户端进行注册登录后，可以对课程进行关注，添加至我的最爱，随后进入个人面板可以看到关注课程的信息。

视频播放方面我弃用了Android原生的视频播放组件，采用了GitHub开源框架JiaoZiVideoPlayer，对哔哩哔哩开源视频播放框架ijkplayer进行了集成，比较流行，优点也比较多，支持多格式视频播放。



### Android端界面

------

<img src="https://github.com/wangluoxiaozhazha/online-education/blob/master/images/Screenshot_2020-05-12-22-36-54-33_11eee3e6b847cc2.png" width="300" height="550"/><img src="https://github.com/wangluoxiaozhazha/online-education/blob/master/images/Screenshot_2020-05-12-22-37-04-32_11eee3e6b847cc2.png" width="300" height="550" /><img src="https://github.com/wangluoxiaozhazha/online-education/blob/master/images/Screenshot_2020-05-12-22-37-11-95_11eee3e6b847cc2.png" width="300" height="550" /><img src="https://github.com/wangluoxiaozhazha/online-education/blob/master/images/Screenshot_2020-05-12-22-37-17-37_11eee3e6b847cc2.png" width="300" height="550" /><img src="https://github.com/wangluoxiaozhazha/online-education/blob/master/images/Screenshot_2020-05-12-22-37-24-27_11eee3e6b847cc2.png" width="300" height="550" /><img src="https://github.com/wangluoxiaozhazha/online-education/blob/master/images/Screenshot_2020-05-12-22-38-20-29_11eee3e6b847cc2.png" width="300" height="550" /><img src="https://github.com/wangluoxiaozhazha/online-education/blob/master/images/Screenshot_2020-05-12-22-38-24-31_11eee3e6b847cc2.png" width="300" height="550" />
<img src="https://github.com/wangluoxiaozhazha/online-education/blob/master/images/Screenshot_2020-05-13-13-44-49-87_11eee3e6b847cc2.png" width="1000" height="450"/>


### 后台管理系统界面

------
<img src="https://github.com/wangluoxiaozhazha/online-education/blob/master/images/1589345974(1).png" width="1000" height="450"/><img src="https://github.com/wangluoxiaozhazha/online-education/blob/master/images/1589346068(1).png" width="1000" height="450"/><img src="https://github.com/wangluoxiaozhazha/online-education/blob/master/images/1589346095(1).png" width="1000" height="450"/><img src="https://github.com/wangluoxiaozhazha/online-education/blob/master/images/1589346176(1).png" width="1000" height="450"/><img src="https://github.com/wangluoxiaozhazha/online-education/blob/master/images/1589346198(1).png" width="1000" height="450"/><img src="https://github.com/wangluoxiaozhazha/online-education/blob/master/images/1589346217(1).png" width="1000" height="450"/>


### 项目结构

------

|—online-education

​				|—online-education(服务端)

​				|—OnlineStudy(客户端)

​				|—sql(数据库文件)

### 使用技术

------

服务端：

- Spring Boot(开箱即用的懒人开发包)
- Mybatis(灵活且流行的持久化框架)
- jetty(代替tomcat的轻量级服务器)
- thymeleaf(代替jsp的模板引擎)

前端：

- Bootstrap(流行，便捷，美观的前端框架)
- fcup2(来自封尘的js文件分片技术框架)  项目地址：[https://gitee.com/lovefc/fcup2](https://gitee.com/lovefc/fcup2)

Android端：

- Android原生组件
- okhttp3(流行的网络框架)
- jiaozivideoplayer(强大灵活视频内核集成框架，可集成多种解码内核)  项目地址[https://github.com/Jzvd/JiaoZiVideoPlayer](https://github.com/Jzvd/JiaoZiVideoPlayer)
- ijkplayer(流行强大的视频解析内核)
- banner(Android开发必用的广告图片轮播控件)  项目地址：[https://github.com/youth5201314/banner](https://github.com/youth5201314/banner)

### 开发环境

------

- IntelliJ IDEA    地址：https://www.jetbrains.com/idea/
- Android Studio  地址：https://developer.android.google.cn/studio/

### 感谢

------

感谢各个开源框架的作者，能够让我站在巨人的肩膀上飞翔。

也感谢网络上的许多开发者平台和平台上的开发者，在我开发中出现问题能够及时的在平台上求助以及获得帮助。

### 免责声明

------

本项目所有图片及视频资源均来自于网络，且项目没有任何盈利，若侵犯了您的权力请及时通知我，我会立刻删除！
