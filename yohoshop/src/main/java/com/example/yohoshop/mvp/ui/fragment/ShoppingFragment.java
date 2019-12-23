package com.example.yohoshop.mvp.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yohoshop.R;
import com.example.yohoshop.mvp.ui.activity.AddAddressActivity;
import com.example.yohoshop.mvp.ui.activity.AddressActivity;
import com.example.yohoshop.mvp.ui.activity.OrderActivity;
import com.example.yohoshop.mvp.ui.adapter.MyMineShoppingRecycleAdapter;
import java.util.ArrayList;

public class ShoppingFragment extends Fragment {
    private View view = null;
    private RecyclerView mine_shopping_recycle;
    private MyMineShoppingRecycleAdapter myMineShoppingRecycleAdapter;
    private ArrayList<Integer> list = new ArrayList<>();
    private TextView shopping_order;
    private ImageView shopping_address;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.fragment_mine_shopping,null);
        mine_shopping_recycle = view.findViewById(R.id.mine_shopping_recycle);
        list.add(R.mipmap.shop1);
        list.add(R.mipmap.shop2);
        list.add(R.mipmap.shop3);
        list.add(R.mipmap.shop4);
        list.add(R.mipmap.shop5);
        list.add(R.mipmap.shop6);
        list.add(R.mipmap.shop7);
        list.add(R.mipmap.shop8);
        list.add(R.mipmap.shop9);
        list.add(R.mipmap.shop10);
        list.add(R.mipmap.shop12);
        list.add(R.mipmap.shop13);


        myMineShoppingRecycleAdapter = new MyMineShoppingRecycleAdapter(getContext(),list);
        mine_shopping_recycle.setAdapter(myMineShoppingRecycleAdapter);
        mine_shopping_recycle.setLayoutManager(new GridLayoutManager(getContext(),2));

        shopping_order = view.findViewById(R.id.shopping_order);
        shopping_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), OrderActivity.class));
            }
        });
        shopping_address = view.findViewById(R.id.shopping_address);
        shopping_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddressActivity.class));
            }
        });






        return view;
    }
}
