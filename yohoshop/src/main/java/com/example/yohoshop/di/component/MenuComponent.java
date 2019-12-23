package com.example.yohoshop.di.component;

import com.example.yohoshop.mvp.ui.activity.MainActivity;
import com.example.yohoshop.di.module.MenuModule;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = MenuModule.class,dependencies = AppComponent.class)
public interface MenuComponent {
    void inject(MainActivity mainActivity);





}
