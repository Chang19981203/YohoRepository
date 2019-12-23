package com.example.yohoshop.di.module;

import com.example.yohoshop.mvp.contact.LreContact;

import com.example.yohoshop.mvp.model.LreModel;
import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class LreModule {
    private LreContact.LreView view;
    @Inject
    public LreModule(LreContact.LreView view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    LreContact.LreView providerView(){
        return view;
    }

    @ActivityScope
    @Provides
    LreContact.LreModel providerModel(LreModel lreModel){
        return lreModel;
    }
}
