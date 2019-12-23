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
import com.example.yohoshop.mvp.model.entity.BrandListEntity;
import java.util.ArrayList;

public class MyBrandCenterRecycleAdapter extends RecyclerView.Adapter<MyBrandCenterRecycleAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<BrandListEntity.ValuesBean> list;

    public MyBrandCenterRecycleAdapter(Context context, ArrayList<BrandListEntity.ValuesBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_brand_center_recycle, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(Api.APP_DOMAIN+list.get(position).getBrand_icon()).into(holder.brand_center_pic);
        holder.brand_center_name.setText(list.get(position).getBrand_name());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView brand_center_pic;
        TextView brand_center_name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            brand_center_pic = itemView.findViewById(R.id.brand_center_pic);
            brand_center_name = itemView.findViewById(R.id.brand_center_name);
        }
    }
}
