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

public class MyOrderRecycleAdapter extends RecyclerView.Adapter<MyOrderRecycleAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<ShoppingCarEntity.ValuesBean> list;

    public MyOrderRecycleAdapter(Context context, ArrayList<ShoppingCarEntity.ValuesBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_order_recycle, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(Api.APP_DOMAIN+list.get(position).getCar_path()).into(holder.order_shopping_pic);
        holder.order_shopping_name.setText(list.get(position).getShop_name());
        holder.order_shopping_color.setText(list.get(position).getShop_color());
        holder.order_shopping_size.setText(list.get(position).getShop_size());
        holder.order_shopping_new_money.setText(list.get(position).getShop_price());
        holder.order_shopping_old_money.setText(list.get(position).getShop_price());
        holder.order_shopping_num.setText(list.get(position).getShop_num());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView order_shopping_number;
        TextView order_shopping_payment;
        ImageView order_shopping_pic;
        TextView order_shopping_name;
        TextView order_shopping_color;
        TextView order_shopping_size;
        TextView order_shopping_new_money;
        TextView order_shopping_old_money;
        TextView order_shopping_num;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            order_shopping_number = itemView.findViewById(R.id.order_shopping_number);
            order_shopping_payment = itemView.findViewById(R.id.order_shopping_payment);
            order_shopping_pic = itemView.findViewById(R.id.order_shopping_pic);
            order_shopping_name = itemView.findViewById(R.id.order_shopping_name);
            order_shopping_color = itemView.findViewById(R.id.order_shopping_color);
            order_shopping_size = itemView.findViewById(R.id.order_shopping_size);
            order_shopping_new_money = itemView.findViewById(R.id.order_shopping_new_money);
            order_shopping_old_money = itemView.findViewById(R.id.order_shopping_old_money);
            order_shopping_num = itemView.findViewById(R.id.order_shopping_num);
        }
    }
}
