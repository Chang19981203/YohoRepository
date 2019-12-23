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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewestFragment extends BaseFragment<LrePresenter> implements LreContact.LreView {
    private View view = null;

    @BindView(R.id.news_xrecycle)
    XRecyclerView news_xrecycle;

    private MyRecommendXRecycleViewAdapter myRecommendXRecycleViewAdapter;
    private ArrayList<CommunityEntity.ValuesBean> list = new ArrayList<>();

    @Override
    public void success(BaseEntity baseEntity, int type) {
        Log.e("chang", "success: "+baseEntity );
        if (type == ApiDoman.COMMUNITY){
            if (baseEntity!=null){
                List<CommunityEntity.ValuesBean> values = ((CommunityEntity) baseEntity).getValues();
                list.addAll(values);
                myRecommendXRecycleViewAdapter.notifyDataSetChanged();

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
        view = View.inflate(getContext(), R.layout.fragment_newest, null);
        ButterKnife.bind(this,view);
        initMyView();

        return view;
    }

    private void initMyView() {

        myRecommendXRecycleViewAdapter = new MyRecommendXRecycleViewAdapter(getContext(),list);
        news_xrecycle.setAdapter(myRecommendXRecycleViewAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        news_xrecycle.setLayoutManager(gridLayoutManager);
        news_xrecycle.setPullRefreshEnabled(true);
        news_xrecycle.setLoadingMoreEnabled(true);
        news_xrecycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                news_xrecycle.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                news_xrecycle.loadMoreComplete();
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
}