package com.example.yohoshop.di.module;

import com.example.yohoshop.mvp.contact.HomeContact;
import com.example.yohoshop.mvp.model.HomeModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {
    private HomeContact.HomeIView iView;
    public HomeModule(HomeContact.HomeIView view){
        this.iView = view;
    }

    @ActivityScope
    @Provides
    HomeContact.HomeIModel providerHomeIModel(HomeModel model){
        return model;
    }

    @ActivityScope
    @Provides
    HomeContact.HomeIView providerHomeIView(){
        return iView;
    }

}
