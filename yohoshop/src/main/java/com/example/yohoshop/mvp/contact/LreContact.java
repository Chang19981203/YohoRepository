package com.example.yohoshop.mvp.contact;

import com.example.yohoshop.mvp.model.entity.BaseEntity;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import io.reactivex.Observable;

public interface LreContact {
    interface  LreView extends IView{
        void success(BaseEntity baseEntity,int type);
        void error(Throwable throwable);

        void refreshSuccess(BaseEntity baseEntity,int type);
        void loadMoreSuccess(BaseEntity baseEntity,int type);
    }


    interface LreModel extends IModel{
        //请求数据
        Observable<BaseEntity> lreRequest(String params,int type);
        //下拉刷新
        Observable<BaseEntity> lreRefreshRequest(String params,int type);
        //下拉加载
        Observable<BaseEntity> lreLoadMore (String params,int type);
    }
}
