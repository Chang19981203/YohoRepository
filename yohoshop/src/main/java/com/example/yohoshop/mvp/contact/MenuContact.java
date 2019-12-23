package com.example.yohoshop.mvp.contact;

import com.example.yohoshop.mvp.model.entity.MenuEntity;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import io.reactivex.Observable;

public interface MenuContact {
    //针对于menu页面的iview的接口
    interface MenuView extends IView{
        void getMenuOk(MenuEntity menuEntity);
    }
    interface MenuModel extends IModel{
        Observable<MenuEntity> requestMenu();
    }
}
