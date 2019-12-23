package com.example.yohoshop.mvp.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yohoshop.R;
import com.example.yohoshop.di.component.DaggerLreComponent;
import com.example.yohoshop.di.module.LreModule;
import com.example.yohoshop.doman.ApiDoman;
import com.example.yohoshop.mvp.contact.LreContact;
import com.example.yohoshop.mvp.model.entity.BaseEntity;
import com.example.yohoshop.mvp.model.entity.BrandListEntity;
import com.example.yohoshop.mvp.presenter.LrePresenter;
import com.example.yohoshop.mvp.ui.adapter.MyBrandBottomNewRecycleAdapter;
import com.example.yohoshop.mvp.ui.adapter.MyBrandBottomRecycleAdapter;
import com.example.yohoshop.mvp.ui.adapter.MyBrandCenterRecycleAdapter;
import com.gjiazhe.wavesidebar.WaveSideBar;
import com.google.android.material.tabs.TabLayout;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import qdx.indexbarlayout.IndexBar;
import qdx.indexbarlayout.IndexLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BrandFragment extends BaseFragment<LrePresenter> implements LreContact.LreView {
    private View view;
    //侧边栏数据
    String[] rightStrs = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","#"};
    List<String> rightList = Arrays.asList(rightStrs);
    //头部TabLayout
    @BindView(R.id.brand_title_tab)
    TabLayout brand_tab;
    //搜索栏
    @BindView(R.id.brand_search)
    EditText brand_search;
    //顶部Banner
    @BindView(R.id.brand_title_banner)
    Banner brand_title_banner;
    //品牌RecycleView
    @BindView(R.id.brand_center_recycle)
    RecyclerView brand_center_recycle;
    //切换TabLayout
    @BindView(R.id.brand_center_tab)
    TabLayout brand_center_tab;
    //底部数据RecycleView
    @BindView(R.id.brand_bottom_recycle)
    RecyclerView brand_bottom_recycle;
    //索引栏
    @BindView(R.id.brand_right_index)
    IndexLayout brand_right_index;

    //头部Banner数据
    private ArrayList<Integer> title_BannerList = new ArrayList<>();
    //头部TabLayout数据
    private ArrayList<String> tabList = new ArrayList<>();
    //数据TabLayout数据
    private ArrayList<String> centerList = new ArrayList<>();

    //品牌数据
    private ArrayList<BrandListEntity.ValuesBean> brandList = new ArrayList<>();
    //品牌适配器
    private MyBrandCenterRecycleAdapter myBrandCenterRecycleAdapter;
    //底部品牌数据
    private ArrayList<BrandListEntity.ValuesBean> bottomList = new ArrayList<>();
    //底部数据适配器
    private MyBrandBottomRecycleAdapter myBrandBottomRecycleAdapter;
    //底部第二个数据
    private MyBrandBottomNewRecycleAdapter myBrandBottomNewRecycleAdapter;
    //布局1
    private LinearLayoutManager linearLayoutManager;
    //布局2
    private GridLayoutManager gridLayoutManager;


    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerLreComponent.builder().appComponent(appComponent).lreModule(new LreModule(this)).build().inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.fragment_brand, null);
        ButterKnife.bind(this,view);
        initMyView();

        return view;
    }

    private void initMyView() {
        //清空集合
        tabList.clear();
        centerList.clear();
        title_BannerList.clear();

        title_BannerList.add(R.mipmap.brand_banner1);
        title_BannerList.add(R.mipmap.brand_banner2);
        title_BannerList.add(R.mipmap.brand_banner3);

        tabList.add("男装");
        tabList.add("女装");
        tabList.add("生活方式");
        tabList.add("潮童");
        tabList.add("高街BLK");

        centerList.add("全部品牌");
        centerList.add("新入驻品牌");
        centerList.add("热门品牌");

        brand_title_banner.setImageLoader(new MyImageLoader());
        brand_title_banner.setImages(title_BannerList);
        brand_title_banner.setDelayTime(3000);
        brand_title_banner.setBannerStyle(BannerConfig.CENTER);
        brand_title_banner.start();

        brand_tab.setSelectedTabIndicatorHeight(0);
        brand_center_tab.setSelectedTabIndicatorHeight(0);

        for (int i = 0; i < tabList.size(); i++) {
            LinearLayout linearLayout = (LinearLayout) brand_tab.getChildAt(0);
            linearLayout.setDividerPadding(20);
            linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            linearLayout.setDividerDrawable(ContextCompat.getDrawable(getContext(),
                    R.drawable.layout_divider_vertical));
            brand_tab.addTab(brand_tab.newTab().setText(tabList.get(i)));
        }
        for (int i = 0; i < centerList.size(); i++) {
            LinearLayout linearLayout = (LinearLayout) brand_center_tab.getChildAt(0);
            linearLayout.setDividerPadding(20);
            linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            linearLayout.setDividerDrawable(ContextCompat.getDrawable(getContext(),
                    R.drawable.layout_divider_vertical));
            brand_center_tab.addTab(brand_center_tab.newTab().setText(centerList.get(i)));
        }
        brand_center_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setCustomView(null);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        brand_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView text = new TextView(getContext());
                text.setText(tab.getText());
                text.setTextSize(18);
                text.setTextColor(Color.BLACK);
                tab.setCustomView(text);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setCustomView(null);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        brand_bottom_recycle.setNestedScrollingEnabled(true);

        brand_right_index.getIndexBar().setIndexsList(rightList);
        brand_right_index.getIndexBar().setIndexTextSize(26);
        brand_right_index.getIndexBar().setSelTextColor(Color.BLACK);
        brand_right_index.getIndexBar().setNorTextColor(Color.GRAY);
        brand_right_index.setCircleDuration(100);
        brand_right_index.setIndexBarHeightRatio(0.7f);
        brand_right_index.getIndexBar().setIndexChangeListener(new IndexBar.IndexChangeListener() {
            @Override
            public void indexChanged(String s) {
                for (int i = 0; i < bottomList.size(); i++) {
                    if (bottomList.get(i).getBrand_letter().equals(s)){
                        Log.e(TAG, "indexChanged: "+s );
                        ((LinearLayoutManager) brand_bottom_recycle.getLayoutManager()).scrollToPositionWithOffset(i,0);
                    }
                }
            }
        });

        brand_center_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText().equals("全部品牌")){
                    brand_right_index.setVisibility(View.VISIBLE);
                    brand_bottom_recycle.setAdapter(myBrandBottomRecycleAdapter);
                    brand_bottom_recycle.setLayoutManager(linearLayoutManager);
                }else if (tab.getText().equals("新入驻品牌")){
                    brand_right_index.setVisibility(View.INVISIBLE);
                    brand_bottom_recycle.setAdapter(myBrandBottomNewRecycleAdapter);
                    brand_bottom_recycle.setLayoutManager(gridLayoutManager);
                }else if(tab.getText().equals("热门品牌")){
                    brand_right_index.setVisibility(View.INVISIBLE);

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        myBrandCenterRecycleAdapter = new MyBrandCenterRecycleAdapter(getContext(),brandList);
        brand_center_recycle.setAdapter(myBrandCenterRecycleAdapter);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        brand_center_recycle.setLayoutManager(linearLayoutManager1);

        myBrandBottomRecycleAdapter = new MyBrandBottomRecycleAdapter(getContext(),bottomList);
        brand_bottom_recycle.setAdapter(myBrandBottomRecycleAdapter);
        linearLayoutManager = new LinearLayoutManager(getContext());
        brand_bottom_recycle.setLayoutManager(new LinearLayoutManager(getContext()));

        myBrandBottomNewRecycleAdapter = new MyBrandBottomNewRecycleAdapter(getContext(),bottomList);
        gridLayoutManager = new GridLayoutManager(getContext(),3);






    }

    @Override
    public void success(BaseEntity baseEntity, int type) {
        brandList.clear();
        bottomList.clear();
        if (baseEntity instanceof BrandListEntity &&baseEntity!=null){
            List<BrandListEntity.ValuesBean> values = ((BrandListEntity) baseEntity).getValues();
            brandList.addAll(values);
            myBrandCenterRecycleAdapter.notifyDataSetChanged();
            bottomList.addAll(values);
            myBrandBottomRecycleAdapter.notifyDataSetChanged();
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
    public void initData(@Nullable Bundle savedInstanceState) {
        JSONObject object = new JSONObject();
        try {
            object.put("menuid","menuid");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(object.toString(), ApiDoman.BRAND_LIST);
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
