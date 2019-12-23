package com.example.yohoshop.mvp.ui.adapter;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yohoshop.R;
import com.example.yohoshop.mvp.model.api.Api;
import com.example.yohoshop.mvp.model.entity.CommunityEntity;

import java.util.ArrayList;

public class MyRecommendXRecycleViewAdapter extends RecyclerView.Adapter<MyRecommendXRecycleViewAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<CommunityEntity.ValuesBean> list;

    public MyRecommendXRecycleViewAdapter(Context context, ArrayList<CommunityEntity.ValuesBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_com_recommend, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(Api.APP_DOMAIN+list.get(position).getImgs().get(0).getImg_path()+".jpg").into(holder.recommend_pic);
        Log.e("changIMG", "onBindViewHolder: "+Api.APP_DOMAIN+list.get(position).getImgs().get(0).getImg_path()+".jpg" );
        holder.recommend_name.setText(list.get(position).getTitle());
        holder.recommend_username.setText(list.get(position).getTag());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView recommend_pic;
        private TextView recommend_name;
        private TextView recommend_username;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recommend_pic = itemView.findViewById(R.id.recommend_pic);
            //获取ImageView的布局参数
            ViewGroup.LayoutParams layoutParams = recommend_pic.getLayoutParams();
            //修改高度
            int height = (int)(Math.random()*201+400);
            Log.e("chang", "MyViewHolder: 随即高度数据"+height );
            layoutParams.height = height;
            recommend_pic.setLayoutParams(layoutParams);
            recommend_name = itemView.findViewById(R.id.recommend_name);
            recommend_username = itemView.findViewById(R.id.recommend_username);

        }
    }
}
