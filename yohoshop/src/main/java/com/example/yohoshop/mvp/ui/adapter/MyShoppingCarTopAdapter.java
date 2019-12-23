package com.example.yohoshop.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yohoshop.R;
import com.example.yohoshop.mvp.model.api.Api;
import com.example.yohoshop.mvp.model.entity.ShoppingCarEntity;

import java.util.ArrayList;

public class MyShoppingCarTopAdapter extends RecyclerView.Adapter<MyShoppingCarTopAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<ShoppingCarEntity.ValuesBean> list;
    private GoodsValuesCallBack goodsValuesCallBack;

    public void setGoodsValuesCallBack(GoodsValuesCallBack goodsValuesCallBack) {
        this.goodsValuesCallBack = goodsValuesCallBack;
    }

    public MyShoppingCarTopAdapter(Context context, ArrayList<ShoppingCarEntity.ValuesBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_shoppingcar_top_recycle, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(Api.APP_DOMAIN+list.get(position).getCar_path()).into(holder.shopping_top_pic);
        holder.shopping_top_name.setText(list.get(position).getShop_name());
        holder.shopping_top_color.setText(list.get(position).getShop_color());
        holder.shopping_top_size.setText(list.get(position).getShop_size());
        holder.shopping_top_price.setText(list.get(position).getShop_price());
        holder.shopping_top_num.setText(list.get(position).getShop_num());
        holder.shopping_top_check.setChecked(list.get(position).isCheck());
        holder.shopping_top_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                list.get(position).setCheck(b);
                goodsValuesCallBack.getGoodsValues(getCheck(),getMoney(),getNum());
            }
        });
    }

public float getMoney(){
        float sum = 0;
    for (int i = 0; i < list.size(); i++) {
        if (list.get(i).isCheck()){
            sum += (Integer.parseInt(list.get(i).getShop_price()))*(Integer.parseInt(list.get(i).getShop_num()));
        }
    }
    return sum;
}
public int getNum(){
        int num = 0;
    for (int i = 0; i < list.size(); i++) {
        if (list.get(i).isCheck()){
            num += Integer.parseInt(list.get(i).getShop_num());
        }
    }
    return num;
}
public boolean getCheck(){
    for (int i = 0; i < list.size(); i++) {
        if (!list.get(i).isCheck()){
            return false;
        }
    }
        return true;
}
public void setAllCheck(){
    for (int i = 0; i < list.size(); i++) {
        list.get(i).setCheck(true);
    }
    notifyDataSetChanged();
}
public void setUnAllCheck(){
    for (int i = 0; i < list.size(); i++) {
        list.get(i).setCheck(false);
    }
    notifyDataSetChanged();
}

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        CheckBox shopping_top_check;
        ImageView shopping_top_pic;
        TextView shopping_top_name;
        TextView shopping_top_color;
        TextView shopping_top_size;
        TextView shopping_top_price;
        TextView shopping_top_num;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            shopping_top_check = itemView.findViewById(R.id.shopping_top_check);
            shopping_top_pic = itemView.findViewById(R.id.shopping_top_pic);
            shopping_top_name = itemView.findViewById(R.id.shopping_top_name);
            shopping_top_color = itemView.findViewById(R.id.shopping_top_color);
            shopping_top_size = itemView.findViewById(R.id.shopping_top_size);
            shopping_top_price = itemView.findViewById(R.id.shopping_top_price);
            shopping_top_num = itemView.findViewById(R.id.shopping_top_num);
        }
    }

    public interface GoodsValuesCallBack{
        public void getGoodsValues(boolean isFlag,float allMoney,int allNum);
    }
}
