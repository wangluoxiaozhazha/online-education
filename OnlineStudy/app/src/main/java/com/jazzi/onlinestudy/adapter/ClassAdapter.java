package com.jazzi.onlinestudy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jazzi.onlinestudy.ClassifyActivity;
import com.jazzi.onlinestudy.ResourcesActivity;
import com.jazzi.onlinestudy.util.IpConfig;
import com.jazzi.onlinestudy.R;
import com.jazzi.onlinestudy.entity.Classification;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder>{

    private Context mContext;
    private List<Classification> classificationList;
    public ClassAdapter(List<Classification> classificationList){
        this.classificationList=classificationList;
    }
    @Override
    public ClassAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext==null){
            mContext=parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.class_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        holder.classImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                Classification classification=classificationList.get(position);
                Intent intent =new Intent(mContext, ClassifyActivity.class);
                intent.putExtra("no","1");
                intent.putExtra("ClassName",classification.getClassName());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ClassAdapter.ViewHolder holder, int position) {
        Classification classification=classificationList.get(position);
        holder.className.setText(classification.getClassName());
        Glide.with(mContext).load(IpConfig.getIp()+"/get/classImage/?className="+classification.getClassName()).into(holder.classImage);
    }

    @Override
    public int getItemCount() {
        return classificationList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView classImage;
        TextView className;

        public ViewHolder (View view)
        {
            super(view);
            classImage = (ImageView) view.findViewById(R.id.class_image);
            className = (TextView) view.findViewById(R.id.class_name);
        }

    }
}
