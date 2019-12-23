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
import com.example.yohoshop.mvp.model.entity.ShoppingCarEntity;

import java.util.ArrayList;

public class MyShoppingCarBottomAdapter extends RecyclerView.Adapter<MyShoppingCarBottomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<ShoppingCarEntity.RecommendGoodsBean> list;

    public MyShoppingCarBottomAdapter(Context context, ArrayList<ShoppingCarEntity.RecommendGoodsBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_shoppingcar_bottom, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(Api.APP_DOMAIN+list.get(position).getGoods_img_path()).into(holder.shopping_bottom_pic);
        holder.shopping_bottom_name.setText(list.get(position).getGoods_name());
        holder.shopping_bottom_price.setText(list.get(position).getGoods_discount_price());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView shopping_bottom_pic;
        TextView shopping_bottom_name;
        TextView shopping_bottom_price;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            shopping_bottom_pic = itemView.findViewById(R.id.shopping_bottom_pic);
            shopping_bottom_name = itemView.findViewById(R.id.shopping_bottom_name);
            shopping_bottom_price = itemView.findViewById(R.id.shopping_bottom_price);
        }
    }
}
