package com.example.yohoshop.mvp.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.yohoshop.R;

import com.example.yohoshop.di.component.DaggerHomeComponent;
import com.example.yohoshop.di.module.HomeModule;
import com.example.yohoshop.mvp.contact.HomeContact;
import com.example.yohoshop.mvp.model.entity.BannerEntity;
import com.example.yohoshop.mvp.model.entity.BaseEntity;
import com.example.yohoshop.mvp.model.entity.GoodsListEntity;
import com.example.yohoshop.mvp.model.entity.MenuEntity;
import com.example.yohoshop.mvp.model.entity.RecommendEntity;
import com.example.yohoshop.mvp.presenter.HomePresenter;
import com.example.yohoshop.mvp.ui.adapter.MyHomeViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import java.util.ArrayList;


public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContact.HomeIView {
    @BindView(R.id.home_scan)
    ImageView home_scan;
    @BindView(R.id.home_mess)
    ImageView home_mess;
    @BindView(R.id.home_search)
    EditText home_edit;
    @BindView(R.id.home_tab)
    TabLayout home_tab;
    @BindView(R.id.home_viewPager)
    ViewPager home_viewPager;

    private View view;

    private ManFragment manFragment = new ManFragment();
    private WoManFragment woManFragment = new WoManFragment();
    private KidsFragment kidsFragment = new KidsFragment();
    private LifeFragment lifeFragment = new LifeFragment();

    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private MyHomeViewPagerAdapter myHomeViewPagerAdapter;


    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerHomeComponent.builder().appComponent(appComponent).homeModule(new HomeModule(this)).build().inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.fragment_home, null);
        ButterKnife.bind(this,view);

        initMyView();

        return this.view;
    }

    private void initMyView() {
        fragmentList.clear();
        fragmentList.add(manFragment);
//        fragmentList.add(manFragment);
//        fragmentList.add(manFragment);
//        fragmentList.add(manFragment);
        fragmentList.add(woManFragment);
        fragmentList.add(kidsFragment);
        fragmentList.add(lifeFragment);
        home_tab.setupWithViewPager(home_viewPager);
        myHomeViewPagerAdapter = new MyHomeViewPagerAdapter(getChildFragmentManager(),fragmentList);
        home_viewPager.setAdapter(myHomeViewPagerAdapter);
        home_tab.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText()!=null){
                    TextView textView = new TextView(getContext());
                    textView.setTextSize(18);
                    textView.setText(tab.getText().toString());
                    textView.setTextColor(Color.BLACK);
                    tab.setCustomView(textView);
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setCustomView(null);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



//        if (homr_xrecycle!=null){
//            myHomeRecycleAdapter = new MyHomeRecycleAdapter(getContext(),list);
//            homr_xrecycle.setAdapter(myHomeRecycleAdapter);
//            homr_xrecycle.setLayoutManager(new LinearLayoutManager(getContext()));
//            xrecycle_Title = View.inflate(getContext(), R.layout.xrecycle_title, null);
//            xrecycle_banner = xrecycle_Title.findViewById(R.id.xrecycle_banner);
//            homr_xrecycle.addHeaderView(xrecycle_Title);
//        }

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


        }else if (type == 2){
            List<RecommendEntity.CategoryBean> category = ((RecommendEntity) baseEntity).getCategory();
            List<RecommendEntity.RecommendBean> recommend = ((RecommendEntity) baseEntity).getRecommend();
            Log.e(TAG, "resultSuccess: "+category+"\n"+recommend );
        }else if (type == 0){
            List<GoodsListEntity.ValuesBean> values = ((GoodsListEntity) baseEntity).getValues();
            Log.e(TAG, "resultSuccess: "+values );

        }else if (type == 3){
            home_tab.removeAllTabs();
            List<MenuEntity.ValuesBean> values = ((MenuEntity) baseEntity).getValues();
            home_tab.setSelectedTabIndicatorHeight(0);
            Log.e(TAG, "resultSuccess: "+values );
            if (values!=null){
                for (int i = 0; i < values.size(); i++) {
                    Log.e("chang", "resultSuccess: "+values.get(i).getMenu_name());
                    home_tab.addTab(home_tab.newTab().setText(values.get(i).getMenu_name()));
                }
            }

        }
    }

    @Override
    public void pulltoRefresh(BaseEntity baseEntity) {

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




//    class MyImageLoader extends ImageLoader{
//        @Override
//        public void displayImage(Context context, Object path, ImageView imageView) {
//            Glide.with(context).load(Api.APP_DOMAIN+path).into(imageView);
//        }
//    }

}
