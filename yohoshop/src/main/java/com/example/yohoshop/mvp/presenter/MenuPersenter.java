package com.example.yohoshop.mvp.presenter;

import android.util.Log;

import com.example.yohoshop.mvp.contact.MenuContact;
import com.example.yohoshop.mvp.model.entity.MenuEntity;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MenuPersenter extends BasePresenter<MenuContact.MenuModel,MenuContact.MenuView> {
    @Inject
    public MenuPersenter(MenuContact.MenuModel menuModel,MenuContact.MenuView menuView){
        super(menuModel,menuView);
    }
    //网路访问
    public void requestMenu(){
        mModel.requestMenu().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<MenuEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe: 形成订阅" );
            }

            @Override
            public void onNext(MenuEntity menuEntity) {
                if (menuEntity!=null){
                    Log.e("requestMenu", "onNext: "+menuEntity.toString() );
                    mRootView.getMenuOk(menuEntity);
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " );
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: " );
            }
        });
    }




}
