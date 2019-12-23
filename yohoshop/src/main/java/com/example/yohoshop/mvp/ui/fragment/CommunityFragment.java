package com.example.yohoshop.mvp.ui.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.yohoshop.R;

public class CommunityFragment extends Fragment {
    private View view = null;
    private TextView mine_commnuity_post;
    private TextView mine_community_collect;
    private TextView mine_commnuity_post_after;
    private TextView mine_community_collect_after;
    private ImageView mine_community_img;
    private TextView mine_community_txt;
    private Button mine_community_btn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.fragment_mine_community,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mine_commnuity_post = view.findViewById(R.id.mine_community_post);
        mine_community_collect = view.findViewById(R.id.mine_community_collect);
        mine_commnuity_post_after = view.findViewById(R.id.mine_community_post_after);
        mine_community_collect_after = view.findViewById(R.id.mine_community_collect_after);
        mine_community_img = view.findViewById(R.id.mine_community_img);
        mine_community_txt = view.findViewById(R.id.mine_community_txt);
        mine_community_btn = view.findViewById(R.id.mine_community_btn);
        mine_commnuity_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mine_commnuity_post.setTextColor(Color.BLACK);
                        mine_commnuity_post_after.setTextColor(Color.BLACK);
                        mine_community_collect.setTextColor(Color.GRAY);
                        mine_community_collect_after.setTextColor(Color.GRAY);

                        Glide.with(getContext()).load(R.mipmap.mine_community_post).into(mine_community_img);
                        mine_community_txt.setText("发你的第一篇内容，可立赚5元");
                        mine_community_btn.setText("去发布");
                    }
                });

            }
        });
        mine_community_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mine_commnuity_post.setTextColor(Color.GRAY);
                        mine_commnuity_post_after.setTextColor(Color.GRAY);
                        mine_community_collect.setTextColor(Color.BLACK);
                        mine_community_collect_after.setTextColor(Color.BLACK);

                        Glide.with(getContext()).load(R.mipmap.mine_community_collect).into(mine_community_img);
                        mine_community_txt.setText("快去社区发现潮流好内容吧");
                        mine_community_btn.setText("去社区逛逛");
                    }
                });

            }
        });

    }
}
