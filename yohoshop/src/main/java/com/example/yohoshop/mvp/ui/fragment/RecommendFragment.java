package com.example.yohoshop.mvp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.yohoshop.R;
import com.example.yohoshop.di.component.DaggerLreComponent;
import com.example.yohoshop.di.module.LreModule;
import com.example.yohoshop.doman.ApiDoman;
import com.example.yohoshop.mvp.contact.LreContact;
import com.example.yohoshop.mvp.model.api.Api;
import com.example.yohoshop.mvp.model.entity.BaseEntity;
import com.example.yohoshop.mvp.model.entity.CommunityEntity;
import com.example.yohoshop.mvp.presenter.LrePresenter;
import com.example.yohoshop.mvp.ui.adapter.MyRecommendXRecycleViewAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;

public class RecommendFragment extends BaseFragment<LrePresenter> implements LreContact.LreView {
    private View view = null;
    private View titleView = null;
    private Banner recommend_Title_banner;

    @BindView(R.id.recommend_xrecycle)
    XRecyclerView recommend_xrecycle;

    private MyRecommendXRecycleViewAdapter myRecommendXRecycleViewAdapter;
    private ArrayList<CommunityEntity.ValuesBean> list = new ArrayList<>();

    //Banner数据
    private ArrayList<String> bannerList = new ArrayList<>();

    @Override
    public void success(BaseEntity baseEntity, int type) {
        Log.e("chang", "success: "+baseEntity );
        if (type == ApiDoman.COMMUNITY){
            if (baseEntity!=null){
                List<CommunityEntity.ValuesBean> values = ((CommunityEntity) baseEntity).getValues();
                list.addAll(values);
                myRecommendXRecycleViewAdapter.notifyDataSetChanged();
                List<CommunityEntity.BannerBean> banner = ((CommunityEntity) baseEntity).getBanner();
                for (int i = 0; i < banner.size(); i++) {
                    bannerList.add(banner.get(i).getRecommend_url());
                }
                recommend_Title_banner.setImageLoader(new MyImageLoader());
                recommend_Title_banner.setImages(bannerList);
                recommend_Title_banner.setDelayTime(4000);
                recommend_Title_banner.setBannerStyle(BannerConfig.CENTER);
                recommend_Title_banner.start();

            }

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
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerLreComponent.builder().appComponent(appComponent).lreModule(new LreModule(this)).build().inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.fragment_recommend, null);
        ButterKnife.bind(this,view);
        initMyView();

        return view;
    }

    private void initMyView() {
        titleView = View.inflate(getContext(),R.layout.item_com_recommend_title,null);
        recommend_Title_banner = titleView.findViewById(R.id.recommend_title_banner);
        myRecommendXRecycleViewAdapter = new MyRecommendXRecycleViewAdapter(getContext(),list);
        recommend_xrecycle.setAdapter(myRecommendXRecycleViewAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recommend_xrecycle.setLayoutManager(gridLayoutManager);
        recommend_xrecycle.addHeaderView(titleView);
        recommend_xrecycle.setPullRefreshEnabled(true);
        recommend_xrecycle.setLoadingMoreEnabled(true);
        recommend_xrecycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                recommend_xrecycle.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                recommend_xrecycle.loadMoreComplete();
            }
        });
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        JSONObject object = new JSONObject();
        try {
            object.put("page","1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(object.toString(),ApiDoman.COMMUNITY);
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
            Glide.with(context).load(Api.APP_DOMAIN+path).into(imageView);
        }
    }
}
