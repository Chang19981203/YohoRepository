package com.example.yohoshop.mvp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.yohoshop.R;
import com.example.yohoshop.di.component.DaggerLreComponent;
import com.example.yohoshop.di.module.LreModule;
import com.example.yohoshop.mvp.contact.LreContact;
import com.example.yohoshop.mvp.model.entity.BaseEntity;
import com.example.yohoshop.mvp.presenter.LrePresenter;
import com.example.yohoshop.mvp.ui.adapter.MyClassViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;

public class ClassFragment extends BaseFragment<LrePresenter> implements LreContact.LreView{
    private View view;

    @BindView(R.id.class_tab)
    TabLayout class_tab;

    @BindView(R.id.class_viewPager)
    ViewPager class_viewPager;

    private CateFragment cateFragment = new CateFragment();
    private BrandFragment brandFragment = new BrandFragment();

    private MyClassViewPagerAdapter myClassViewPagerAdapter;

    private ArrayList<Fragment> list =  new ArrayList<>();


    @Override
    public void success(BaseEntity baseEntity, int type) {

    }

    @Override
    public void error(Throwable throwable) {

    }

    @Override
    public void refreshSuccess(BaseEntity baseEntity, int type) {

    }

    @Override
    public void loadMoreSuccess(BaseEntity baseEntity, int type) {

    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerLreComponent.builder().appComponent(appComponent).lreModule(new LreModule(this)).build().inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.fragment_class, null);
        ButterKnife.bind(this,view);
        initMyView();

        return view;
    }

    private void initMyView() {
        list.clear();
        //添加Fragment
        list.add(cateFragment);
        list.add(brandFragment);
        myClassViewPagerAdapter = new MyClassViewPagerAdapter(getChildFragmentManager(),list);
        class_viewPager.setAdapter(myClassViewPagerAdapter);
        //挂载
        class_tab.setupWithViewPager(class_viewPager);

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

}
