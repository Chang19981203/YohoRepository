package com.example.yohoshop.mvp.model;

import com.example.yohoshop.doman.ApiDoman;
import com.example.yohoshop.mvp.contact.LreContact;
import com.example.yohoshop.mvp.model.api.Api;
import com.example.yohoshop.mvp.model.entity.AddAddressEntity;
import com.example.yohoshop.mvp.model.entity.AddCarEntity;
import com.example.yohoshop.mvp.model.entity.AddressListEntity;
import com.example.yohoshop.mvp.model.entity.BaseEntity;
import com.example.yohoshop.mvp.model.entity.BrandListEntity;
import com.example.yohoshop.mvp.model.entity.CategoryGoodsEntity;
import com.example.yohoshop.mvp.model.entity.CommunityEntity;
import com.example.yohoshop.mvp.model.entity.GoodsValuesEntity;
import com.example.yohoshop.mvp.model.entity.LoginEntity;
import com.example.yohoshop.mvp.model.entity.RegisterEntity;
import com.example.yohoshop.mvp.model.entity.ShoesListEntity;
import com.example.yohoshop.mvp.model.entity.ShoppingCarEntity;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.Functions;

@ActivityScope
public class LreModel extends BaseModel implements LreContact.LreModel {
    @Inject
    public LreModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseEntity> lreRequest(String params, int type) {
        Observable<BaseEntity> ob = null;
        switch (type){
            case ApiDoman.CATEGORY_GOODS:{
                Observable<CategoryGoodsEntity> categoryGoodsEntity = mRepositoryManager.obtainRetrofitService(Api.class).postCategoryGoods(params);
                ob  =Observable.fromArray(categoryGoodsEntity).flatMap((Function)Functions.identity(),false,1);

                break;
            }
            case ApiDoman.BRAND_LIST:{
                Observable<BrandListEntity> brandListEntity = mRepositoryManager.obtainRetrofitService(Api.class).postBrandList(params);
                ob  =Observable.fromArray(brandListEntity).flatMap((Function)Functions.identity(),false,1);

                break;
            }
            case ApiDoman.SHOES_LIST:{
                Observable<ShoesListEntity> shoesListEntity = mRepositoryManager.obtainRetrofitService(Api.class).postShoesList(params);
                ob = Observable.fromArray(shoesListEntity).flatMap((Function)Functions.identity(),false,1);

                break;
            }
            case ApiDoman.LOGIN:{
                Observable<LoginEntity> loginEntity = mRepositoryManager.obtainRetrofitService(Api.class).postLoginList(params);
                ob = Observable.fromArray(loginEntity).flatMap((Function)Functions.identity(),false,1);

                break;
            }

            case ApiDoman.COMMUNITY:{
                Observable<CommunityEntity> communityEntity = mRepositoryManager.obtainRetrofitService(Api.class).postCommunityList(params);
                ob = Observable.fromArray(communityEntity).flatMap((Function)Functions.identity(),false,1);

                break;
            }

            case ApiDoman.REGISTER:{
                Observable<RegisterEntity> registerEntity = mRepositoryManager.obtainRetrofitService(Api.class).postRegisterList(params);
                ob = Observable.fromArray(registerEntity).flatMap((Function)Functions.identity(),false,1);

                break;
            }
            case ApiDoman.CARLIST:{
                Observable<ShoppingCarEntity> shoppingCarEntity = mRepositoryManager.obtainRetrofitService(Api.class).postCarList(params);
                ob = Observable.fromArray(shoppingCarEntity).flatMap((Function)Functions.identity(),false,1);

                break;
            }
            case ApiDoman.GOODSVALUES:{
                Observable<GoodsValuesEntity> goodsValuesEntity = mRepositoryManager.obtainRetrofitService(Api.class).postGoodsValuesList(params);
                ob = Observable.fromArray(goodsValuesEntity).flatMap((Function)Functions.identity(),false,1);

                break;
            }
            case ApiDoman.ADDCAR:{
                Observable<AddCarEntity> addCarEntity = mRepositoryManager.obtainRetrofitService(Api.class).postAddCarList(params);
                ob = Observable.fromArray(addCarEntity).flatMap((Function)Functions.identity(),false,1);
                break;
            }
            case ApiDoman.ADDADDRESS:{
                Observable<AddAddressEntity> addAddressEntity = mRepositoryManager.obtainRetrofitService(Api.class).postAddAddressList(params);
                ob = Observable.fromArray(addAddressEntity).flatMap((Function)Functions.identity(),false,1);
                break;
            }
            case ApiDoman.ADDRESSLIST:{
                Observable<AddressListEntity> addressListEntity = mRepositoryManager.obtainRetrofitService(Api.class).postAddressList(params);
                ob = Observable.fromArray(addressListEntity).flatMap((Function)Functions.identity(),false,1);
            }

        }
        return ob;
    }

    @Override
    public Observable<BaseEntity> lreRefreshRequest(String params, int type) {
        Observable<BaseEntity> ob = null;
        switch (type){
            case ApiDoman.CATEGORY_GOODS:{
                Observable<CategoryGoodsEntity> categoryGoodsEntity = mRepositoryManager.obtainRetrofitService(Api.class).postCategoryGoods(params);
                ob  =Observable.fromArray(categoryGoodsEntity).flatMap((Function)Functions.identity(),false,1);

                break;
            }
            case ApiDoman.BRAND_LIST:{
                Observable<BrandListEntity> categoryGoodsEntity = mRepositoryManager.obtainRetrofitService(Api.class).postBrandList(params);
                ob  =Observable.fromArray(categoryGoodsEntity).flatMap((Function)Functions.identity(),false,1);

                break;
            }
            case ApiDoman.SHOES_LIST:{
                Observable<ShoesListEntity> shoesListEntity = mRepositoryManager.obtainRetrofitService(Api.class).postShoesList(params);
                ob = Observable.fromArray(shoesListEntity).flatMap((Function)Functions.identity(),false,1);

                break;
            }
            case ApiDoman.LOGIN:{
                Observable<LoginEntity> loginEntity = mRepositoryManager.obtainRetrofitService(Api.class).postLoginList(params);
                ob = Observable.fromArray(loginEntity).flatMap((Function)Functions.identity(),false,1);

                break;
            }

            case ApiDoman.COMMUNITY:{
                Observable<CommunityEntity> communityEntity = mRepositoryManager.obtainRetrofitService(Api.class).postCommunityList(params);
                ob = Observable.fromArray(communityEntity).flatMap((Function)Functions.identity(),false,1);

                break;
            }

            case ApiDoman.REGISTER:{
                Observable<RegisterEntity> registerEntity = mRepositoryManager.obtainRetrofitService(Api.class).postRegisterList(params);
                ob = Observable.fromArray(registerEntity).flatMap((Function)Functions.identity(),false,1);

                break;
            }
            case ApiDoman.CARLIST:{
                Observable<ShoppingCarEntity> shoppingCarEntity = mRepositoryManager.obtainRetrofitService(Api.class).postCarList(params);
                ob = Observable.fromArray(shoppingCarEntity).flatMap((Function)Functions.identity(),false,1);

                break;
            }
            case ApiDoman.GOODSVALUES:{
                Observable<GoodsValuesEntity> goodsValuesEntity = mRepositoryManager.obtainRetrofitService(Api.class).postGoodsValuesList(params);
                ob = Observable.fromArray(goodsValuesEntity).flatMap((Function)Functions.identity(),false,1);

                break;
            }
            case ApiDoman.ADDCAR:{
                Observable<AddCarEntity> addCarEntity = mRepositoryManager.obtainRetrofitService(Api.class).postAddCarList(params);
                ob = Observable.fromArray(addCarEntity).flatMap((Function)Functions.identity(),false,1);
                break;
            }
            case ApiDoman.ADDADDRESS:{
                Observable<AddAddressEntity> addAddressEntity = mRepositoryManager.obtainRetrofitService(Api.class).postAddAddressList(params);
                ob = Observable.fromArray(addAddressEntity).flatMap((Function)Functions.identity(),false,1);
                break;
            }
            case ApiDoman.ADDRESSLIST:{
                Observable<AddressListEntity> addressListEntity = mRepositoryManager.obtainRetrofitService(Api.class).postAddressList(params);
                ob = Observable.fromArray(addressListEntity).flatMap((Function)Functions.identity(),false,1);
            }
        }
        return ob;
    }

    @Override
    public Observable<BaseEntity> lreLoadMore(String params, int type) {
        Observable<BaseEntity> ob = null;
        switch (type){
            case ApiDoman.CATEGORY_GOODS:{
                Observable<CategoryGoodsEntity> categoryGoodsEntity = mRepositoryManager.obtainRetrofitService(Api.class).postCategoryGoods(params);
                ob  =Observable.fromArray(categoryGoodsEntity).flatMap((Function)Functions.identity(),false,1);

                break;
            }
            case ApiDoman.BRAND_LIST:{
                Observable<BrandListEntity> categoryGoodsEntity = mRepositoryManager.obtainRetrofitService(Api.class).postBrandList(params);
                ob  =Observable.fromArray(categoryGoodsEntity).flatMap((Function)Functions.identity(),false,1);

                break;
            }
            case ApiDoman.SHOES_LIST:{
                Observable<ShoesListEntity> shoesListEntity = mRepositoryManager.obtainRetrofitService(Api.class).postShoesList(params);
                ob = Observable.fromArray(shoesListEntity).flatMap((Function)Functions.identity(),false,1);

                break;
            }
            case ApiDoman.LOGIN:{
                Observable<LoginEntity> loginEntity = mRepositoryManager.obtainRetrofitService(Api.class).postLoginList(params);
                ob = Observable.fromArray(loginEntity).flatMap((Function)Functions.identity(),false,1);

                break;
            }
            case ApiDoman.COMMUNITY:{
                Observable<CommunityEntity> communityEntity = mRepositoryManager.obtainRetrofitService(Api.class).postCommunityList(params);
                ob = Observable.fromArray(communityEntity).flatMap((Function)Functions.identity(),false,1);

                break;
            }

            case ApiDoman.REGISTER:{
                Observable<RegisterEntity> registerEntity = mRepositoryManager.obtainRetrofitService(Api.class).postRegisterList(params);
                ob = Observable.fromArray(registerEntity).flatMap((Function)Functions.identity(),false,1);

                break;
            }
            case ApiDoman.CARLIST:{
                Observable<ShoppingCarEntity> shoppingCarEntity = mRepositoryManager.obtainRetrofitService(Api.class).postCarList(params);
                ob = Observable.fromArray(shoppingCarEntity).flatMap((Function)Functions.identity(),false,1);

                break;
            }
            case ApiDoman.GOODSVALUES:{
                Observable<GoodsValuesEntity> goodsValuesEntity = mRepositoryManager.obtainRetrofitService(Api.class).postGoodsValuesList(params);
                ob = Observable.fromArray(goodsValuesEntity).flatMap((Function)Functions.identity(),false,1);

                break;
            }
            case ApiDoman.ADDCAR:{
                Observable<AddCarEntity> addCarEntity = mRepositoryManager.obtainRetrofitService(Api.class).postAddCarList(params);
                ob = Observable.fromArray(addCarEntity).flatMap((Function)Functions.identity(),false,1);
                break;
            }
            case ApiDoman.ADDADDRESS:{
                Observable<AddAddressEntity> addAddressEntity = mRepositoryManager.obtainRetrofitService(Api.class).postAddAddressList(params);
                ob = Observable.fromArray(addAddressEntity).flatMap((Function)Functions.identity(),false,1);
                break;
            }
            case ApiDoman.ADDRESSLIST:{
                Observable<AddressListEntity> addressListEntity = mRepositoryManager.obtainRetrofitService(Api.class).postAddressList(params);
                ob = Observable.fromArray(addressListEntity).flatMap((Function)Functions.identity(),false,1);
            }
        }
        return ob;
    }
}
