package com.example.yohoshop.mvp.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yohoshop.R;
import com.example.yohoshop.mvp.model.entity.LeftRecycleEntity;

import java.util.ArrayList;


public class MyClassLeftRecycleAdapter extends RecyclerView.Adapter<MyClassLeftRecycleAdapter.MyViewHolder> {
    private ArrayList<LeftRecycleEntity> list;
    private Context context;
    private MyClassNotifion myClassNotifion;

    public MyClassLeftRecycleAdapter(ArrayList<LeftRecycleEntity> list, Context context, MyClassNotifion myClassNotifion) {
        this.list = list;
        this.context = context;
        this.myClassNotifion = myClassNotifion;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_class_left_recycle, null);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.left_name.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setShow(false);
                }
                list.get(position).setShow(true);
                notifyDataSetChanged();
                myClassNotifion.UIRefresh(position);
            }
        });

        if (list.get(position).isShow()){
            holder.left_pic.setVisibility(View.VISIBLE);
            holder.left_name.setTextColor(Color.BLACK);
            holder.left_name.setTextSize(18);
        }else {
            holder.left_pic.setVisibility(View.INVISIBLE);
            holder.left_name.setTextColor(Color.GRAY);
            holder.left_name.setTextSize(16);
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView left_pic;
        TextView left_name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            left_pic = itemView.findViewById(R.id.left_recycle_pic);
            left_name = itemView.findViewById(R.id.left_recycle_name);
        }
    }


    public interface MyClassNotifion {
        void UIRefresh(int position);
    }
}
