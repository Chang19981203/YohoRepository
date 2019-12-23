package com.example.yohoshop.di.module;

import com.example.yohoshop.mvp.contact.MenuContact;
import com.example.yohoshop.mvp.model.MenuModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

//提供依赖
@Module
public class MenuModule {
    private MenuContact.MenuView view;

    public MenuModule(MenuContact.MenuView view) {
        this.view = view;
    }
    //和activity的生命周期相同
    @ActivityScope
    @Provides
    MenuContact.MenuView providerMenuView(){
        return view;
    }
    @Provides
    MenuContact.MenuModel providerMenuModel(MenuModel menuModel){
        return menuModel;
    }



}
