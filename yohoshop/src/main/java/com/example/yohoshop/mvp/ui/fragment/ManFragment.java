package com.example.yohoshop.mvp.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.example.yohoshop.mvp.ui.activity.GoodsValuesActivity;
import com.example.yohoshop.mvp.ui.adapter.MyManBottomOnClick;
import com.example.yohoshop.mvp.ui.adapter.MyManCenterAdapter;
import com.example.yohoshop.mvp.ui.adapter.MyManRecycleAdapter;
import com.google.android.material.tabs.TabLayout;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManFragment extends BaseFragment<HomePresenter> implements HomeContact.HomeIView {

    private static String TAG = "ManFragment Chang";

    @BindView(R.id.man_top_banner)
    Banner man_top_banner;
    @BindView(R.id.man_bottom_Banner)
    Banner map_bottom_banner;
    @BindView(R.id.man_center_recycle)
    RecyclerView man_center_recycle;
    @BindView(R.id.man_bottom_recycle)
    RecyclerView man_bottom_recycle;
    @BindView(R.id.man_bottom_tab)
    TabLayout man_bottom_tab;

    private View view;

    //中间黑色列表适配器
    private MyManCenterAdapter myManCenterAdapter;

    //顶部banner图片集合
    private ArrayList<BannerEntity.ValuesBean> topBannerList = new ArrayList<>();
    //下方banner图片集合
    private ArrayList<Integer> bottomBannerList = new ArrayList<>();
    //中间黑色列表  6
    private HashMap<String,Integer> map = new HashMap<>();

    //最底部商品列表适配器
    private MyManRecycleAdapter myManRecycleAdapter;
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
        view = View.inflate(getContext(),R.layout.fragment_man,null);
        ButterKnife.bind(this,view);
        initMyView();

        return this.view;
    }

    private void initMyView() {
        map.put("球鞋鉴定",R.mipmap.shoe);
        map.put("球鞋日历",R.mipmap.day);
        map.put("有货推手",R.mipmap.pig);
        map.put("0元抽奖",R.mipmap.ling);
        map.put("潮物奥莱",R.mipmap.sale);
        map.put("潮流趋势",R.mipmap.fashion);
        myManCenterAdapter = new MyManCenterAdapter(getContext(),map);
        man_center_recycle.setAdapter(myManCenterAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation((RecyclerView.HORIZONTAL));
        man_center_recycle.setLayoutManager(linearLayoutManager);

        bottomBannerList.add(R.mipmap.glide1);
        bottomBannerList.add(R.mipmap.glide2);
        bottomBannerList.add(R.mipmap.glide3);

        map_bottom_banner.setImages(bottomBannerList);
        map_bottom_banner.setDelayTime(3000);
        map_bottom_banner.setImageLoader(new MyImageLoader());
        map_bottom_banner.setBannerStyle(BannerConfig.CENTER);
        map_bottom_banner.start();

        myManRecycleAdapter = new MyManRecycleAdapter(getContext(),bottom_List);
        man_bottom_recycle.setAdapter(myManRecycleAdapter);
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
        myManRecycleAdapter.setMyManBottomOnClick(new MyManBottomOnClick() {
            @Override
            public void myBottomClickListener(int position) {
                GoodsListEntity.ValuesBean valuesBean = bottom_List.get(position);
                String goods_id = valuesBean.getGoods_id();
                String goods_img_path = valuesBean.getGoods_img_path();
                startActivity(new Intent(getContext(), GoodsValuesActivity.class).putExtra("values",valuesBean));
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
            myManRecycleAdapter.notifyDataSetChanged();

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
            myManRecycleAdapter.notifyDataSetChanged();
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
    class MyImageLoader extends ImageLoader{
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
