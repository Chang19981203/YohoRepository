package com.example.yohoshop.mvp.ui.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;


public class MyClassViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private String[] mStrs = new String[]{"品类","品牌"};

    public MyClassViewPagerAdapter(@NonNull FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    public MyClassViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public MyClassViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mStrs[position];
    }
}
