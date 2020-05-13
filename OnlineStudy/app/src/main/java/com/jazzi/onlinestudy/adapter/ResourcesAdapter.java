package com.jazzi.onlinestudy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.jazzi.onlinestudy.util.IpConfig;
import com.jazzi.onlinestudy.R;
import com.jazzi.onlinestudy.ResourcesActivity;
import com.jazzi.onlinestudy.entity.Resources;

import java.util.List;

/*这个类是用作滑动控件的适配器
* 设置滑动控件的一些方法与属性*/
public class ResourcesAdapter extends RecyclerView.Adapter<ResourcesAdapter.ViewHolder> {

    private Context mContext;
    private List<Resources> mFruitList;

    public ResourcesAdapter(List<Resources> fruitList){
        mFruitList=fruitList;
    }
    /*原本FruitAdapter应该继承 RecyclerView.ViewHolder
    * 而这里则创建RecyclerView.ViewHolder的继承类ViewHolder
    * FruitAdapter.ViewHolder则代替了RecyclerView.ViewHolder*/
     /*ViewHolder通常出现在适配器里，为的是listview滚动的时候快速设置值，
        而不必每次都重新创建很多对象，从而提升性能。*/
    class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView fruitImage;
        TextView fruitName;


        public ViewHolder(View view){
            super(view);
            cardView=(CardView) view;
            fruitImage=(ImageView) view.findViewById(R.id.fruit_image);
            fruitName=(TextView) view.findViewById(R.id.fruit_name);
        }

    }


    //用于创建ViewHolder实例
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext==null){
            mContext=parent.getContext();//获得父活动的上下文
        }
        //获得主布局对象
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item,parent,false);
        // 只有通过主布局对象才能获得子布局对象
//        return new ViewHolder(view);
        final ViewHolder holder=new ViewHolder(view);

        /*获得点击的对象在mFruitList中的id
        * 并且根据id找到该点击对象的实例
        * 然后创建一个上下文，装入实例的图片的名字，以及图片的路径
        * 然后开启一个新的活动*/
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                Resources fruit=mFruitList.get(position);
                Intent intent =new Intent(mContext, ResourcesActivity.class);
                intent.putExtra("id",fruit.getResourceId());
                intent.putExtra(ResourcesActivity.FRUIT_NAME,fruit.getCourseName());
                intent.putExtra("image_url",fruit.getCoverPath());
                intent.putExtra("course_controduction",fruit.getCourseIntroduction());
                intent.putExtra("course_teacher",fruit.getLecturer());
                intent.putExtra("numberEpisodes",fruit.getNumberEpisodes());
                intent.putExtra("CoursePath",fruit.getCoursePath());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }



    /*用于对子项数据进行赋值
     * 每个子项被滚动到屏幕内的时候执行
     * 通过position与fruits得到当前fruit实例
     * 然后将数据设置到ViewHolder的ImageView和TextView中
     *
     *  Glide.with传入一个Context参数，然后调用load方法加载图片然后设置到具体的
     *  某一个ImageView中即可
     *  Glide可以加载压缩的文件，所以用它*/
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Resources fruit=mFruitList.get(position);
        holder.fruitName.setText(fruit.getCourseName());
        Glide.with(mContext).load(IpConfig.getIp()+"/get/coverImage/?name="+fruit.getCourseName()+"&coverPath="+fruit.getCoverPath()).into(holder.fruitImage);
    //    Glide.with(mContext).load(IpConfig.getIp()+"/getImage/?path="+fruit.getResourceId()).into(holder.fruitImage);
//        holder.fruitImage.setImageResource(fruit.getImageId());

    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
