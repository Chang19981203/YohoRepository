package com.example.yohoshop.mvp.contact;

import com.example.yohoshop.mvp.model.entity.BannerEntity;
import com.example.yohoshop.mvp.model.entity.BaseEntity;
import com.example.yohoshop.mvp.model.entity.GoodsListEntity;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import io.reactivex.Observable;

public interface HomeContact {
    interface HomeIView extends IView{
        //页面访问成功
        void onSuccess();
        //接口请求返回
        void resultSuccess(BaseEntity baseEntity,int type);
        //下拉刷新
        void pulltoRefresh(BaseEntity baseEntity);
        //下拉加载
        void loadMore(BaseEntity baseEntity);
        //菜单加载
        void getMenuList(BaseEntity baseEntity);
    }

    interface HomeIModel extends IModel{
        //请求全部接口
        Observable<BaseEntity> requestAll(String catagory,String goodslist);
        //下拉刷新
        Observable<GoodsListEntity> pulltoRefresh(String pramas);
        //上拉加载
        Observable<GoodsListEntity> loadMore(String pramas);
        //菜单加载
        Observable<BannerEntity> getMenuist();
    }
}
