package com.example.yohoshop.mvp.presenter;

import android.util.Log;

import com.example.yohoshop.mvp.contact.HomeContact;
import com.example.yohoshop.mvp.model.entity.BannerEntity;
import com.example.yohoshop.mvp.model.entity.BaseEntity;
import com.example.yohoshop.mvp.model.entity.GoodsListEntity;
import com.example.yohoshop.mvp.model.entity.MenuEntity;
import com.example.yohoshop.mvp.model.entity.RecommendEntity;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@ActivityScope
public class HomePresenter extends BasePresenter<HomeContact.HomeIModel, HomeContact.HomeIView> {
    @Inject
    public HomePresenter(HomeContact.HomeIModel model, HomeContact.HomeIView view){
        super(model,view);
    }
    //默认值
    private int page = 1;//页数
    private String categoryId = "1";//分类Id
    private String menuId = "1";//菜单Id

    public void requestAll(){
        //方式1
        String categorySrc = "{\"menu\":\""+menuId+"\"}";
        //方式2
        JSONObject objectcate = new JSONObject();

        JSONObject object = new JSONObject();
        try {
            objectcate.put("menu",menuId);
            object.put("category",categoryId);
            object.put("page",page);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //
        String goodsListSrc = object.toString();
        mModel.requestAll(objectcate.toString(),goodsListSrc).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BaseEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseEntity baseEntity) {
                if (baseEntity!=null){
                    if (baseEntity instanceof BannerEntity && baseEntity!=null){
                        Log.e(TAG, "onNext: "+baseEntity.toString());
                        if (mRootView!=null){
                            mRootView.resultSuccess(baseEntity,1);
                        }

                    }else if (baseEntity instanceof RecommendEntity && baseEntity!=null){
                        Log.e(TAG, "onNext: "+baseEntity.toString());
                        if (mRootView!=null){
                            mRootView.resultSuccess(baseEntity,2);
                        }
                    }else if (baseEntity instanceof GoodsListEntity && baseEntity!=null){
                        Log.e(TAG, "onNext: "+baseEntity.toString());
                        if (mRootView!=null){
                            mRootView.resultSuccess(baseEntity,0);
                        }

                    }else if (baseEntity instanceof MenuEntity && baseEntity!=null){
                        if (mRootView!=null){
                            mRootView.resultSuccess(baseEntity,3);
                        }
                    }
                }else{
                    Log.e(TAG, "onNext: 数据为空" );
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: 错" );
            }

            @Override
            public void onComplete() {
                if (mRootView!=null){
                    mRootView.onSuccess();
                }
            }
        });
    }
    //分类筛选 刷新
    public void categroyGoods(String categroyId){
        page = 1;
        JSONObject object = new JSONObject();
        try {
            object.put("category",categoryId);
            object.put("page",""+page);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String goodsListSrc = object.toString();
        mModel.pulltoRefresh(goodsListSrc).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<GoodsListEntity>() {
            @Override
            public void accept(GoodsListEntity goodsListEntity) throws Exception {
                if (mRootView!=null){
                    mRootView.pulltoRefresh(goodsListEntity);
                }
            }
        });
    }
    //下拉刷新
    public void refresh(){
        page = 1;
        JSONObject object = new JSONObject();
        try {
            object.put("category",categoryId);
            object.put("page",""+page);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String goodsListSrc = object.toString();
        mModel.pulltoRefresh(goodsListSrc).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<GoodsListEntity>() {
            @Override
            public void accept(GoodsListEntity goodsListEntity) throws Exception {
                if (goodsListEntity ==null||goodsListEntity.getValues()==null){
                    mRootView.showMessage("无数据");
                }else{
                    mRootView.pulltoRefresh(goodsListEntity);
                }

            }
        });
    }
    //上拉加载
    public void load(){
        page = page+1;
        JSONObject object = new JSONObject();
        try {
            object.put("category",categoryId);
            object.put("page",""+page);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String goodsListSrc = object.toString();
        mModel.loadMore(goodsListSrc).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<GoodsListEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(GoodsListEntity goodsListEntity) {
                if (goodsListEntity ==null||goodsListEntity.getValues()==null){
                    mRootView.showMessage("无数据");
                }else{
                    mRootView.loadMore(goodsListEntity);
                }
            }

            @Override
            public void onError(Throwable e) {
                mRootView.showMessage("最后一页");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
