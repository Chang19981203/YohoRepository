package com.example.yohoshop.mvp.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yohoshop.R;
import com.example.yohoshop.mvp.model.entity.BrandListEntity;


import java.util.ArrayList;

public class MyBrandBottomRecycleAdapter extends RecyclerView.Adapter<MyBrandBottomRecycleAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<BrandListEntity.ValuesBean> list;

    public MyBrandBottomRecycleAdapter(Context context, ArrayList<BrandListEntity.ValuesBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_brand_bottom_recycle, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (list.get(position)!=null){
            holder.brand_bottom_title.setText(list.get(position).getBrand_letter());
            holder.brand_bottom_name.setText(list.get(position).getBrand_name());

            if (list.get(position).getHot_tag().equals("true")){
                Log.e("chang", "onBindViewHolder: "+list.get(position).getHot_tag() );
                holder.brand_bottom_hot.setVisibility(View.VISIBLE);
            }else{

            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView brand_bottom_title;
        TextView brand_bottom_name;
        ImageView brand_bottom_hot;
        ImageView brand_bottom_new;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            brand_bottom_title =  itemView.findViewById(R.id.brand_bottom_title);
            brand_bottom_name  = itemView.findViewById(R.id.brand_bottom_name);
            brand_bottom_hot = itemView.findViewById(R.id.brand_bottom_hot);
            brand_bottom_new =  itemView.findViewById(R.id.brand_bottom_new);

        }
    }
}
