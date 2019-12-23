package com.example.yohoshop.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yohoshop.mvp.model.entity.GoodsListEntity;

import java.util.ArrayList;


public class MyHomeRecycleAdapter extends RecyclerView.Adapter<MyHomeRecycleAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<GoodsListEntity.ValuesBean> list;

    public MyHomeRecycleAdapter(Context context, ArrayList<GoodsListEntity.ValuesBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }
    //添加头布局后 数量+1
    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
