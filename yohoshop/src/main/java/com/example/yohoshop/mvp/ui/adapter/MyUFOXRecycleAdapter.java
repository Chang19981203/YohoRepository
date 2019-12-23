package com.example.yohoshop.mvp.ui.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yohoshop.R;
import com.example.yohoshop.mvp.model.api.Api;
import com.example.yohoshop.mvp.model.entity.ShoesListEntity;

import java.util.ArrayList;

public class MyUFOXRecycleAdapter extends RecyclerView.Adapter<MyUFOXRecycleAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<ShoesListEntity.ValuesBean> list;

    public MyUFOXRecycleAdapter(Context context, ArrayList<ShoesListEntity.ValuesBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_ufo_xrecycle, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(Api.APP_DOMAIN+list.get(position).getGoods_img_path()).into(holder.ufo_xrecycle_pic);
        holder.ufo_xrecycle_name.setText(list.get(position).getGoods_name());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView ufo_xrecycle_pic;
        TextView ufo_xrecycle_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ufo_xrecycle_pic = itemView.findViewById(R.id.ufo_xrecycle_pic);
            ufo_xrecycle_name = itemView.findViewById(R.id.ufo_xrecycle_name);
        }
    }
}
