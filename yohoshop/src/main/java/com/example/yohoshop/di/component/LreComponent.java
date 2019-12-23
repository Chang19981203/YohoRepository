package com.example.yohoshop.di.component;

import com.example.yohoshop.di.module.LreModule;
import com.example.yohoshop.mvp.ui.activity.AddAddressActivity;
import com.example.yohoshop.mvp.ui.activity.AddressActivity;
import com.example.yohoshop.mvp.ui.activity.GoodsValuesActivity;
import com.example.yohoshop.mvp.ui.activity.OrderActivity;
import com.example.yohoshop.mvp.ui.activity.RegisterActivity;
import com.example.yohoshop.mvp.ui.activity.ShoppingCarActivity;
import com.example.yohoshop.mvp.ui.activity.UserLoginActivity;
import com.example.yohoshop.mvp.ui.fragment.AttentionFragment;
import com.example.yohoshop.mvp.ui.fragment.BrandFragment;
import com.example.yohoshop.mvp.ui.fragment.CateFragment;
import com.example.yohoshop.mvp.ui.fragment.ClassFragment;
import com.example.yohoshop.mvp.ui.fragment.NewestFragment;
import com.example.yohoshop.mvp.ui.fragment.RecommendFragment;
import com.example.yohoshop.mvp.ui.fragment.UFOFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = LreModule.class,dependencies = AppComponent.class)
public interface LreComponent {
    void inject(ClassFragment classFragment);
    void inject(CateFragment cateFragment);
    void inject(BrandFragment brandFragment);
    void inject(UFOFragment ufoFragment);
    void inject(UserLoginActivity userLoginActivity);
    void inject(RecommendFragment recommendFragment);
    void inject(AttentionFragment attentionFragment);
    void inject(NewestFragment newestFragment);
    void inject(RegisterActivity registerActivity);
    void inject(ShoppingCarActivity shoppingCarActivity);
    void inject(GoodsValuesActivity goodsValuesActivity);
    void inject(OrderActivity orderActivity);
    void inject(AddAddressActivity addAddressActivity);
    void inject(AddressActivity addressActivity);

}
