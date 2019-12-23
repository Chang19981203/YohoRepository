package com.example.yohoshop.mvp.model.api;

import com.example.yohoshop.mvp.model.entity.AddAddressEntity;
import com.example.yohoshop.mvp.model.entity.AddCarEntity;
import com.example.yohoshop.mvp.model.entity.AddressListEntity;
import com.example.yohoshop.mvp.model.entity.BannerEntity;
import com.example.yohoshop.mvp.model.entity.BrandListEntity;
import com.example.yohoshop.mvp.model.entity.CategoryGoodsEntity;
import com.example.yohoshop.mvp.model.entity.CommunityEntity;
import com.example.yohoshop.mvp.model.entity.GoodsListEntity;
import com.example.yohoshop.mvp.model.entity.GoodsValuesEntity;
import com.example.yohoshop.mvp.model.entity.LoginEntity;
import com.example.yohoshop.mvp.model.entity.MenuEntity;
import com.example.yohoshop.mvp.model.entity.RecommendEntity;
import com.example.yohoshop.mvp.model.entity.RegisterEntity;
import com.example.yohoshop.mvp.model.entity.ShoesListEntity;
import com.example.yohoshop.mvp.model.entity.ShoppingCarEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * ================================================
 * 存放一些与 API 有关的东西,如请求地址,请求码等
 * <p>
 * Created by MVPArmsTemplate on 11/29/2019 18:31
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public interface Api {
    String APP_DOMAIN = "http://169.254.168.40/yoho/";
//    String APP_DOMAIN = "http://10.1.2.80/yoho/";
    //首页菜单请求
    @GET("home_menu.php")
    Observable<MenuEntity> getMenuList();
    //banner页请求
    @GET("home_banner.php")
    Observable<BannerEntity> getBannerList();
    //推荐页请求
    @FormUrlEncoded
    @POST("home_recommend.php")
    Observable<RecommendEntity> postRecommendList(@Field("request") String request);

    //首页列表页请求
    @FormUrlEncoded
    @POST("home_goods.php")
    Observable<GoodsListEntity> postGoodsList(@Field("request") String request);

    //品类商品页请求
    @FormUrlEncoded
    @POST("category_goods.php")
    Observable<CategoryGoodsEntity> postCategoryGoods(@Field("request") String request);

    //品牌列表页请求
    @FormUrlEncoded
    @POST("Brand_list.php")
    Observable<BrandListEntity> postBrandList(@Field("request") String request);

    //球鞋交易请求
    @FormUrlEncoded
    @POST("shoes_list.php")
    Observable<ShoesListEntity> postShoesList(@Field("request") String request);

    //登录
    @FormUrlEncoded
    @POST("Login.php")
    Observable<LoginEntity> postLoginList(@Field("request") String request);

    //社区
    @FormUrlEncoded
    @POST("community.php")
    Observable<CommunityEntity> postCommunityList(@Field("request") String request);

    //注册
    @FormUrlEncoded
    @POST("Register.php")
    Observable<RegisterEntity> postRegisterList(@Field("request") String request);

    //购物车
    @FormUrlEncoded
    @POST("car_list.php")
    Observable<ShoppingCarEntity> postCarList(@Field("request") String request);

    //商品详情
    @FormUrlEncoded
    @POST("goods_values.php")
    Observable<GoodsValuesEntity> postGoodsValuesList(@Field("request") String request);
    //加入购物车
    @FormUrlEncoded
    @POST("add_car.php")
    Observable<AddCarEntity> postAddCarList(@Field("request") String request);

    //address_list.php
    //获取地址列表
    @FormUrlEncoded
    @POST("address_list.php")
    Observable<AddressListEntity> postAddressList(@Field("request") String request);

    //添加地址
    @FormUrlEncoded
    @POST("add_address.php")
    Observable<AddAddressEntity> postAddAddressList(@Field("request") String request);

}
