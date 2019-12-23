package com.example.yohoshop.mvp.presenter;


import android.util.Log;

import com.example.yohoshop.mvp.contact.LreContact;
import com.example.yohoshop.mvp.model.entity.BaseEntity;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@ActivityScope
public class LrePresenter extends BasePresenter<LreContact.LreModel,LreContact.LreView> {
    @Inject
    public LrePresenter(LreContact.LreModel model, LreContact.LreView rootView) {
        super(model, rootView);
    }

    public void lreAll(String params,int type){
        mModel.lreRequest(params,type).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BaseEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseEntity baseEntity) {
                if (baseEntity!=null){
                    if (mRootView!=null){
                        mRootView.success(baseEntity,type);
                        Log.e("chang","111"+baseEntity);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void lreRefresh(String params,int type){
        int page = 0;
        try {
            JSONObject object = new JSONObject();
            object.put("page",""+page);
            params = object.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mModel.lreRefreshRequest(params,type).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BaseEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseEntity baseEntity) {
                mRootView.success(baseEntity,type);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}
