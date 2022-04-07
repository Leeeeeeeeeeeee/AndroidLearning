package com.example.androidlearning.ui.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.androidlearning.R;

public class MyListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public MyListAdapter(Context context){
        this.mContext=context;
        mLayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return 10;//实际开发不是固定的
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder{
        public ImageView imageView;
        public TextView tvTitle,tvTime,tvContent;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.layout_list_item,null);
            holder = new ViewHolder();
            holder.imageView = (ImageView)convertView.findViewById(R.id.iv);
            holder.tvTitle = (TextView)convertView.findViewById(R.id.tv_title);
            holder.tvTime = (TextView)convertView.findViewById(R.id.tv_time);
            holder.tvContent = (TextView)convertView.findViewById(R.id.tv_content);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //给控件赋值
        holder.tvTitle.setText("这是标题");
        holder.tvTime.setText("2020-10-01");
        holder.tvContent.setText("这是内容");
        Glide.with(mContext).load("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png").into(holder.imageView);
        return convertView;
    }
}
