package com.example.yohoshop.di.component;

import com.example.yohoshop.di.module.HomeModule;
import com.example.yohoshop.mvp.ui.fragment.HomeFragment;
import com.example.yohoshop.mvp.ui.fragment.KidsFragment;
import com.example.yohoshop.mvp.ui.fragment.LifeFragment;
import com.example.yohoshop.mvp.ui.fragment.ManFragment;
import com.example.yohoshop.mvp.ui.fragment.WoManFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = HomeModule.class,dependencies = AppComponent.class)
public interface HomeComponent {
    void inject(HomeFragment homeFragment);
    void inject(ManFragment manFragment);
    void inject(WoManFragment woManFragment);
    void inject(KidsFragment kidsFragment);
    void inject(LifeFragment lifeFragment);

}
