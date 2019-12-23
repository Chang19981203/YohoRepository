package com.example.yohoshop.mvp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.yohoshop.R;
import com.example.yohoshop.mvp.ui.adapter.MyComViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;

public class ComFragment extends Fragment {
    private View view;
    //TabLayout
    @BindView(R.id.com_tab)
    TabLayout com_tab;
    @BindView(R.id.com_viewPager)
    ViewPager com_viewPager;

    //ViewPager适配器
    MyComViewPagerAdapter myComViewPagerAdapter;
    //适配器数据
    ArrayList<Fragment> comList = new ArrayList<>();
    //Fragment数据
    private AttentionFragment attentionFragment = new AttentionFragment();
    private RecommendFragment recommendFragment = new RecommendFragment();
    private NewestFragment newestFragment = new NewestFragment();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.fragment_com, null);
        ButterKnife.bind(this,view);
        initView();

        return view;
    }

    private void initView() {
        comList.clear();
        comList.add(attentionFragment);
        comList.add(recommendFragment);
        comList.add(newestFragment);
        com_tab.setupWithViewPager(com_viewPager);
        myComViewPagerAdapter = new MyComViewPagerAdapter(getChildFragmentManager(),comList);
        com_viewPager.setAdapter(myComViewPagerAdapter);
    }
}
