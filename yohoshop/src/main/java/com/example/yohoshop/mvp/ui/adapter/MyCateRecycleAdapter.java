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
import com.example.yohoshop.mvp.model.entity.CategoryGoodsEntity;

import java.util.ArrayList;

public class MyCateRecycleAdapter extends RecyclerView.Adapter<MyCateRecycleAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<CategoryGoodsEntity.ValuesBean> list;

    public MyCateRecycleAdapter(Context context, ArrayList<CategoryGoodsEntity.ValuesBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_cate_recycle, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(Api.APP_DOMAIN+list.get(position).getImage_path()).into(holder.cate_xrecycle_pic);
        holder.cate_xrecycle_name.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView cate_xrecycle_pic;
        TextView cate_xrecycle_name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cate_xrecycle_pic = itemView.findViewById(R.id.cate_recycle_pic);
            cate_xrecycle_name = itemView.findViewById(R.id.cate_recycle_name);
        }
    }
}
