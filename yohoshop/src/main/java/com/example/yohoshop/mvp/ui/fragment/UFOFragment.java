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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.yohoshop.R;
import com.example.yohoshop.di.component.DaggerLreComponent;
import com.example.yohoshop.di.module.LreModule;
import com.example.yohoshop.doman.ApiDoman;
import com.example.yohoshop.mvp.contact.LreContact;
import com.example.yohoshop.mvp.model.entity.BaseEntity;
import com.example.yohoshop.mvp.model.entity.ShoesListEntity;
import com.example.yohoshop.mvp.presenter.LrePresenter;
import com.example.yohoshop.mvp.ui.adapter.MyUFOXRecycleAdapter;
import com.google.android.material.tabs.TabLayout;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.stx.xhb.androidx.XBanner;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;

public class UFOFragment extends BaseFragment<LrePresenter> implements LreContact.LreView {
    private View view;

    //顶部TabLayout
    @BindView(R.id.ufo_tab)
    TabLayout ufo_tab;
    //底部数据栏
    @BindView(R.id.ufo_xrecycle)
    XRecyclerView ufo_xrecycle;

    //TabLayout数据
    private String[] strs = new String[]{"推荐","新品","人气","潮配","配饰","实战","女神"};
    private ArrayList<String> tabList = new ArrayList<>();

    //数据适配器
    private MyUFOXRecycleAdapter myUFOXRecycleAdapter;
    //数据
    private ArrayList<ShoesListEntity.ValuesBean> xreList = new ArrayList<>();

    //第一个头布局
    private View headView = null;
    private ImageView ufo_title_pic;
    private ImageView ufo_center_pic;
    private ImageView ufo_bottom_pic;

    //第一页Banner数据集合
    private ArrayList<Integer> bannerList = new ArrayList<>();
    //Banner对象
    private XBanner ufo_banner;
    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerLreComponent.builder().appComponent(appComponent).lreModule(new LreModule(this)).build().inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.fragment_ufo, null);
        ButterKnife.bind(this,view);
        initMyView();
        return view;
    }

    private void initMyView() {
        tabList.clear();
        bannerList.clear();

        for (int i = 0; i < strs.length; i++) {
            tabList.add(strs[i]);
        }

        for (int i = 0; i < tabList.size(); i++) {
            ufo_tab.addTab(ufo_tab.newTab().setText(tabList.get(i)));
        }

        ufo_tab.setSelectedTabIndicatorHeight(0);
        //头布局
        headView = View.inflate(getContext(),R.layout.item_ufo_recommend,null);


        myUFOXRecycleAdapter = new MyUFOXRecycleAdapter(getContext(),xreList);
        ufo_xrecycle.setAdapter(myUFOXRecycleAdapter);
        ufo_xrecycle.setLayoutManager(new GridLayoutManager(getContext(),2));
        ufo_xrecycle.setPullRefreshEnabled(true);
        ufo_xrecycle.setLoadingMoreEnabled(true);
        ufo_xrecycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                ufo_xrecycle.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                ufo_xrecycle.loadMoreComplete();
            }
        });
        ufo_xrecycle.addHeaderView(headView);

        bannerList.add(R.mipmap.ufo_img2);
        bannerList.add(R.mipmap.ufo_img3);
        bannerList.add(R.mipmap.ufo_img4);
        bannerList.add(R.mipmap.ufo_img5);
        bannerList.add(R.mipmap.ufo_img6);

        ufo_banner = headView.findViewById(R.id.ufo_recommend_xBanner);
        ufo_banner.setData(bannerList,null);
        ufo_banner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(model).into((ImageView)view);
            }
        });
        ufo_title_pic = headView.findViewById(R.id.ufo_title_pic);
        ufo_center_pic = headView.findViewById(R.id.ufo_center_pic);
        ufo_bottom_pic = headView.findViewById(R.id.ufo_bottom_pic);

        ufo_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText().equals("推荐")){
                    Log.e("chang", "onTabSelected: 推荐" );
                    ufo_title_pic.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(R.mipmap.ufo_recommend).into(ufo_title_pic);
                    ufo_center_pic.setVisibility(View.VISIBLE);
                    ufo_bottom_pic.setVisibility(View.VISIBLE);
                    ufo_banner.setVisibility(View.VISIBLE);
                    myUFOXRecycleAdapter.notifyDataSetChanged();

                }else if (tab.getText().equals("新品")){
                    Log.e("chang", "onTabSelected: 新品" );
                    ufo_title_pic.setVisibility(View.GONE);
                    ufo_center_pic.setVisibility(View.GONE);
                    ufo_bottom_pic.setVisibility(View.GONE);
                    ufo_banner.setVisibility(View.GONE);
                    myUFOXRecycleAdapter.notifyDataSetChanged();


                }else if (tab.getText().equals("人气")){
                    Log.e("chang", "onTabSelected: 人气" );
                    ufo_title_pic.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(R.mipmap.ufo_moods).into(ufo_title_pic);
                    ufo_center_pic.setVisibility(View.GONE);
                    ufo_bottom_pic.setVisibility(View.GONE);
                    ufo_banner.setVisibility(View.GONE);
                    myUFOXRecycleAdapter.notifyDataSetChanged();


                }else if (tab.getText().equals("潮配")){
                    Log.e("chang", "onTabSelected: 潮配" );
                    ufo_title_pic.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(R.mipmap.ufo_fashion).into(ufo_title_pic);
                    ufo_center_pic.setVisibility(View.GONE);
                    ufo_bottom_pic.setVisibility(View.GONE);
                    ufo_banner.setVisibility(View.GONE);
                    myUFOXRecycleAdapter.notifyDataSetChanged();

                }else if (tab.getText().equals("配饰")){
                    Log.e("chang", "onTabSelected: 配饰" );
                    ufo_title_pic.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(R.mipmap.ufo_acc).into(ufo_title_pic);
                    ufo_center_pic.setVisibility(View.GONE);
                    ufo_bottom_pic.setVisibility(View.GONE);
                    ufo_banner.setVisibility(View.GONE);
                    myUFOXRecycleAdapter.notifyDataSetChanged();

                }else if (tab.getText().equals("实战")){
                    Log.e("chang", "onTabSelected: 实战" );
                    ufo_title_pic.setVisibility(View.GONE);
                    ufo_center_pic.setVisibility(View.GONE);
                    ufo_bottom_pic.setVisibility(View.GONE);
                    ufo_banner.setVisibility(View.GONE);
                    myUFOXRecycleAdapter.notifyDataSetChanged();

                }else if (tab.getText().equals("女神")){
                    Log.e("chang", "onTabSelected: 女神" );
                    ufo_title_pic.setVisibility(View.GONE);
                    ufo_center_pic.setVisibility(View.GONE);
                    ufo_bottom_pic.setVisibility(View.GONE);
                    ufo_banner.setVisibility(View.GONE);
                    myUFOXRecycleAdapter.notifyDataSetChanged();

                }
                myUFOXRecycleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
//        ufo_banner.setBannerStyle(BannerConfig.CENTER);
//        ufo_banner.setDelayTime(5000);
//        ufo_banner.setImages(bannerList);
//        ufo_banner.setImageLoader(new MyImageLoader());
//        ufo_banner.start();


    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        JSONObject object = new JSONObject();
        try {
            object.put("page","1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(object.toString(), ApiDoman.SHOES_LIST);
    }

    @Override
    public void success(BaseEntity baseEntity, int type) {
        if (baseEntity!=null&&baseEntity instanceof ShoesListEntity){
            List<ShoesListEntity.BannerBean> banner = ((ShoesListEntity) baseEntity).getBanner();
            Log.e(TAG, "success: "+banner );
            List<ShoesListEntity.ValuesBean> values = ((ShoesListEntity) baseEntity).getValues();
            xreList.addAll(values);
            myUFOXRecycleAdapter.notifyDataSetChanged();
        }
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
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }


    class MyImageLoader extends ImageLoader{
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
