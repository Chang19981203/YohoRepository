package com.example.yohoshop.mvp.model;

import android.util.Log;

import com.example.yohoshop.mvp.contact.HomeContact;
import com.example.yohoshop.mvp.model.api.Api;
import com.example.yohoshop.mvp.model.entity.BannerEntity;
import com.example.yohoshop.mvp.model.entity.BaseEntity;
import com.example.yohoshop.mvp.model.entity.GoodsListEntity;
import com.example.yohoshop.mvp.model.entity.MenuEntity;
import com.example.yohoshop.mvp.model.entity.RecommendEntity;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;

@ActivityScope
public class HomeModel extends BaseModel implements HomeContact.HomeIModel {
    @Inject
    public HomeModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseEntity> requestAll(String category, String goodslist) {
        //合并Rxjava ->observerable ->任务
        //banner任务请求
        Log.e("requestAllChang", "requestAll: "+category+""+goodslist );
        Observable<BannerEntity> banner = mRepositoryManager.obtainRetrofitService(Api.class).getBannerList();
        //推荐任务请求
        Observable<RecommendEntity> recommend = mRepositoryManager.obtainRetrofitService(Api.class).postRecommendList(category);
        //商品的请求任务
        Observable<GoodsListEntity> goodsList = mRepositoryManager.obtainRetrofitService(Api.class).postGoodsList(goodslist);
        //菜单加载
        Observable<MenuEntity> menuList = mRepositoryManager.obtainRetrofitService(Api.class).getMenuList();

        Observable<BaseEntity> all = Observable.merge(goodsList,banner,recommend,menuList);
        return all;
    }

    @Override
    public Observable<GoodsListEntity> pulltoRefresh(String pramas) {
        return mRepositoryManager.obtainRetrofitService(Api.class).postGoodsList(pramas);
    }

    @Override
    public Observable<GoodsListEntity> loadMore(String pramas) {
        return mRepositoryManager.obtainRetrofitService(Api.class).postGoodsList(pramas);
    }

    @Override
    public Observable<BannerEntity> getMenuist() {
        return null;
    }
}
