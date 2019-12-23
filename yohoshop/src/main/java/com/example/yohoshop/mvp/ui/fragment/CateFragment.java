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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yohoshop.R;
import com.example.yohoshop.di.component.DaggerLreComponent;
import com.example.yohoshop.di.module.LreModule;
import com.example.yohoshop.doman.ApiDoman;
import com.example.yohoshop.mvp.contact.LreContact;
import com.example.yohoshop.mvp.model.api.Api;
import com.example.yohoshop.mvp.model.entity.BaseEntity;
import com.example.yohoshop.mvp.model.entity.CategoryGoodsEntity;
import com.example.yohoshop.mvp.model.entity.LeftRecycleEntity;
import com.example.yohoshop.mvp.presenter.LrePresenter;
import com.example.yohoshop.mvp.ui.adapter.MyCateRecycleAdapter;
import com.example.yohoshop.mvp.ui.adapter.MyClassLeftRecycleAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CateFragment extends BaseFragment<LrePresenter> implements LreContact.LreView {
    private View view;

    private String[]strs = new String[]{"推荐","男装","女装","球鞋","运动","高街","服配","包类","美妆","生活","数码","潮玩","潮食","潮童","好物"};
    ////侧边栏
    @BindView(R.id.cate_recycle)
    RecyclerView cate_recyle;
    //数据栏
    @BindView(R.id.cate_xrecycle)
    XRecyclerView cate_xrecycle;
    //数据栏头布局
    View title_view;

    //侧边栏适配器
    private MyClassLeftRecycleAdapter myClassLeftRecycleAdapter;
    //侧边栏数据
    private ArrayList<LeftRecycleEntity> leftList = new ArrayList<>();
    //数据适配器
    private MyCateRecycleAdapter myCateRecycleAdapter;
    //数据
    private ArrayList<CategoryGoodsEntity.ValuesBean> list = new ArrayList<>();
    //数据上Banner数据集
    private ArrayList<Integer> banner_List = new ArrayList<>();
    //数据中Banner对象
    private Banner cate_title_banner;

    @Override
    public void success(BaseEntity baseEntity, int type) {
       if (type == ApiDoman.CATEGORY_GOODS){
           List<CategoryGoodsEntity.ValuesBean> values = ((CategoryGoodsEntity) baseEntity).getValues();
           list.addAll(values);
           myCateRecycleAdapter.notifyDataSetChanged();

       }

        Log.e("Cate_chang", "success: "+baseEntity);
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
        view = View.inflate(getContext(), R.layout.fragment_cate, null);
        ButterKnife.bind(this,view);
        initMyView();

        return view;
    }
    private void initMyView() {
        banner_List.clear();
        leftList.clear();

        banner_List.add(R.mipmap.cate_banner1);
        banner_List.add(R.mipmap.cate_banner2);
        banner_List.add(R.mipmap.cate_banner3);
        banner_List.add(R.mipmap.cate_banner4);

        for (int i = 0; i < strs.length; i++) {
            if (i==0){
                leftList.add(new LeftRecycleEntity(true,strs[i]));
            }else{
                leftList.add(new LeftRecycleEntity(false,strs[i]));
            }
        }
        leftList.add(new LeftRecycleEntity(false,""));
        myClassLeftRecycleAdapter = new MyClassLeftRecycleAdapter(leftList, getContext(), new MyClassLeftRecycleAdapter.MyClassNotifion() {

            @Override
            public void UIRefresh(int position) {

            }
        });
        cate_recyle.setAdapter(myClassLeftRecycleAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        cate_recyle.setLayoutManager(linearLayoutManager);

        myCateRecycleAdapter = new MyCateRecycleAdapter(getContext(),list);
        cate_xrecycle.setAdapter(myCateRecycleAdapter);
        cate_xrecycle.setLayoutManager(new GridLayoutManager(getContext(),3));
        cate_xrecycle.setLoadingMoreEnabled(true);
        cate_xrecycle.setPullRefreshEnabled(true);

        cate_xrecycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                cate_xrecycle.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                cate_xrecycle.loadMoreComplete();
            }
        });
        title_view = View.inflate(getContext(),R.layout.item_cate_title,null);
        cate_title_banner = title_view.findViewById(R.id.cate_title_banner);
        cate_title_banner.setBannerStyle(BannerConfig.CENTER);
        cate_title_banner.setDelayTime(4000);
        cate_title_banner.setImages(banner_List);
        cate_title_banner.setImageLoader(new MyImageLoader());
        cate_title_banner.start();

        cate_xrecycle.addHeaderView(title_view);

    }



    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        JSONObject object = new JSONObject();
        try {
            object.put("categoryid",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(object.toString(), ApiDoman.CATEGORY_GOODS);
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
