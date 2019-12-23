package com.example.yohoshop.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yohoshop.R;

import java.util.ArrayList;

public class MyMineShoppingRecycleAdapter extends RecyclerView.Adapter<MyMineShoppingRecycleAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Integer> list;

    public MyMineShoppingRecycleAdapter(Context context, ArrayList<Integer> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_mine_shopping_recycle, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(list.get(position)).into(holder.item_pic);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView item_pic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_pic =  itemView.findViewById(R.id.mine_shopping_item_pic);
        }
    }
}
