package com.example.yohoshop.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yohoshop.R;
import com.example.yohoshop.mvp.model.entity.AddressListEntity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAddressListRecycleAdapter extends RecyclerView.Adapter<MyAddressListRecycleAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<AddressListEntity.ValuesBean> list;

    public MyAddressListRecycleAdapter(Context context, ArrayList<AddressListEntity.ValuesBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_mine_address_list, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.address_list_name.setText(list.get(position).getUser_name());
        holder.address_list_phone.setText(list.get(position).getPhone());
        holder.address_list_address.setText(list.get(position).getAddress_area()+list.get(position).getAddress_detail()+list.get(position).getAddress_tag());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView address_list_name;
        TextView address_list_phone;
        TextView address_list_address;
        TextView address_list_delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            address_list_name = itemView.findViewById(R.id.address_list_name);
            address_list_phone = itemView.findViewById(R.id.address_list_phone);
            address_list_address = itemView.findViewById(R.id.address_list_address);
            address_list_delete = itemView.findViewById(R.id.address_list_delete);
        }
    }
}
