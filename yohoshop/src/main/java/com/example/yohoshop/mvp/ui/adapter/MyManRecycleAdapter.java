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
import com.example.yohoshop.mvp.model.entity.GoodsListEntity;

import java.util.ArrayList;

public class MyManRecycleAdapter extends RecyclerView.Adapter<MyManRecycleAdapter.MyBottomViewHoolder> {
    private Context context;
    private ArrayList<GoodsListEntity.ValuesBean> list;
    private MyManBottomOnClick myManBottomOnClick;

    public void setMyManBottomOnClick(MyManBottomOnClick myManBottomOnClick) {
        this.myManBottomOnClick = myManBottomOnClick;
    }

    public MyManRecycleAdapter(Context context, ArrayList<GoodsListEntity.ValuesBean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyBottomViewHoolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_man_bottom_recycle, null);
        return new MyBottomViewHoolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBottomViewHoolder holder, int position) {
        holder.bottom_name.setText(list.get(position).getGoods_name());
        holder.bottom_price.setText("￥"+list.get(position).getGoods_discount_price());
        Glide.with(context).load(Api.APP_DOMAIN+list.get(position).getGoods_img_path()).into(holder.bottom_pic);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myManBottomOnClick.myBottomClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyBottomViewHoolder extends RecyclerView.ViewHolder{
        ImageView bottom_pic;
        TextView bottom_name;
        TextView bottom_price;

        public MyBottomViewHoolder(@NonNull View itemView) {
            super(itemView);
            bottom_pic = itemView.findViewById(R.id.bottom_pic);
            bottom_name = itemView.findViewById(R.id.bottom_name);
            bottom_price = itemView.findViewById(R.id.bottom_price);
        }
    }

}
