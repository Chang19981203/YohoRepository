package com.example.yohoshop.mvp.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yohoshop.R;

import com.example.yohoshop.di.component.DaggerHomeComponent;
import com.example.yohoshop.di.module.HomeModule;
import com.example.yohoshop.mvp.contact.HomeContact;
import com.example.yohoshop.mvp.model.api.Api;
import com.example.yohoshop.mvp.model.entity.BannerEntity;
import com.example.yohoshop.mvp.model.entity.BaseEntity;
import com.example.yohoshop.mvp.model.entity.GoodsListEntity;
import com.example.yohoshop.mvp.model.entity.RecommendEntity;
import com.example.yohoshop.mvp.presenter.HomePresenter;
import com.example.yohoshop.mvp.ui.adapter.MyManRecycleAdapter;
import com.google.android.material.tabs.TabLayout;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LifeFragment extends BaseFragment<HomePresenter> implements HomeContact.HomeIView {

    private static String TAG = "LifeFragment Chang";

    @BindView(R.id.life_top_banner)
    Banner man_top_banner;
    @BindView(R.id.life_bottom_recycle)
    RecyclerView man_bottom_recycle;
    @BindView(R.id.life_bottom_tab)
    TabLayout man_bottom_tab;

    private View view;

    //顶部banner图片集合
    private ArrayList<BannerEntity.ValuesBean> topBannerList = new ArrayList<>();


    //最底部商品列表适配器
    private MyManRecycleAdapter myLifeRecycleAdapter;
    //列适配器集合
    private ArrayList<GoodsListEntity.ValuesBean> bottom_List = new ArrayList<>();

    //底部TabLayout数据HashMap
    private HashMap<String,String> tabMap = new HashMap<>();

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerHomeComponent.builder().appComponent(appComponent).homeModule(new HomeModule(this)).build().inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(),R.layout.fragment_life,null);
        ButterKnife.bind(this,view);
        initMyView();

        return this.view;
    }

    private void initMyView() {
        //
        myLifeRecycleAdapter = new MyManRecycleAdapter(getContext(),bottom_List);
        man_bottom_recycle.setAdapter(myLifeRecycleAdapter);
        man_bottom_recycle.setLayoutManager(new GridLayoutManager(getContext(),2));

        man_bottom_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String tabName = tab.getText().toString();
                String values = tabMap.get(tabName);
                mPresenter.categroyGoods(values);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.requestAll();
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void resultSuccess(BaseEntity baseEntity, int type) {
        if (type == 1){
            List<BannerEntity.ValuesBean> values = ((BannerEntity) baseEntity).getValues();
            Log.e(TAG, "resultSuccess: "+values.toString() );
            topBannerList.addAll(values);
            Log.e(TAG, "resultSuccess: "+topBannerList.toString() );
            man_top_banner.setBannerStyle(BannerConfig.CENTER);
            man_top_banner.setImageLoader(new MyImageLoader());
            man_top_banner.setDelayTime(Integer.parseInt(values.size()+"000"));
            man_top_banner.setImages(topBannerList);
            man_top_banner.start();


        }else if (type == 2){
            List<RecommendEntity.CategoryBean> category = ((RecommendEntity) baseEntity).getCategory();
            List<RecommendEntity.RecommendBean> recommend = ((RecommendEntity) baseEntity).getRecommend();
            Log.e(TAG, "resultSuccess: "+category+"\n"+recommend );

            man_bottom_tab.removeAllTabs();
            man_bottom_tab.setSelectedTabIndicatorHeight(0);
            Log.e(TAG, "resultSuccess: "+category );
            if (category!=null){
                for (int i = 0; i < category.size(); i++) {
                    Log.e("chang", "resultSuccess: "+category.get(i).getCategory_name());
                    tabMap.put(category.get(i).getCategory_name(),category.get(i).getCategory_id());
                    man_bottom_tab.addTab(man_bottom_tab.newTab().setText(category.get(i).getCategory_name()));
                }
            }

        }else if (type == 0){
            bottom_List.clear();
            List<GoodsListEntity.ValuesBean> values = ((GoodsListEntity) baseEntity).getValues();
            Log.e(TAG, "resultSuccess: "+values );
            bottom_List.addAll(values);
            myLifeRecycleAdapter.notifyDataSetChanged();

        }else if (type == 3){

        }
    }

    @Override
    public void pulltoRefresh(BaseEntity baseEntity) {
        if (baseEntity instanceof GoodsListEntity){
            bottom_List.clear();
            List<GoodsListEntity.ValuesBean> values = ((GoodsListEntity) baseEntity).getValues();
            Log.e(TAG, "pulltoRefresh: "+values );
            bottom_List.addAll(values);
            myLifeRecycleAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void loadMore(BaseEntity baseEntity) {

    }

    @Override
    public void getMenuList(BaseEntity baseEntity) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    //Banner图片加载器
    class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            if (path instanceof Integer){
                Glide.with(context).load(path).into(imageView);
            }else if (path instanceof BannerEntity.ValuesBean){
                Glide.with(context).load(Api.APP_DOMAIN+((BannerEntity.ValuesBean)path).getRecommend_url()).into(imageView);
            }

        }
    }


}
