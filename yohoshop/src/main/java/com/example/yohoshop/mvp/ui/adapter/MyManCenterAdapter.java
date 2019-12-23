package com.example.yohoshop.mvp.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yohoshop.R;

import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;

public class MyManCenterAdapter extends RecyclerView.Adapter<MyManCenterAdapter.MyManViewHolder> {
    private Context context;
    private HashMap<String,Integer> map;
    private ArrayList<String> list = new ArrayList<>();

    public MyManCenterAdapter(Context context, HashMap<String, Integer> map) {
        this.context = context;
        this.map = map;
    }

    @NonNull
    @Override
    public MyManViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_man_recycle, null);
        return new MyManViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyManViewHolder holder, int position) {
        Set<String> strings = map.keySet();
        Log.e("chang", "onBindViewHolder: Set"+strings.toString());
        list.addAll(strings);
        Log.e("chang", "onBindViewHolder: List"+list.toString() );
        holder.man_title.setText(list.get(position));
        Glide.with(context).load(map.get(list.get(position))).into(holder.man_pic);

    }

    @Override
    public int getItemCount() {
        return map.size();
    }

    //
    class MyManViewHolder extends RecyclerView.ViewHolder{
        ImageView man_pic;
        TextView man_title;
        public MyManViewHolder(@NonNull View itemView) {
            super(itemView);
            man_pic = itemView.findViewById(R.id.man_pic);
            man_title = itemView.findViewById(R.id.man_title);
        }
    }
}
