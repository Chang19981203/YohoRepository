package com.example.yohoshop.mvp.model.entity;

import com.google.gson.Gson;

import java.util.List;

public class ShoppingCarEntity extends BaseEntity{

    private List<ValuesBean> values;
    private List<RecommendGoodsBean> recommend_goods;

    public static ShoppingCarEntity objectFromData(String str) {

        return new Gson().fromJson(str, ShoppingCarEntity.class);
    }

    public List<ValuesBean> getValues() {
        return values;
    }

    public void setValues(List<ValuesBean> values) {
        this.values = values;
    }

    public List<RecommendGoodsBean> getRecommend_goods() {
        return recommend_goods;
    }

    public void setRecommend_goods(List<RecommendGoodsBean> recommend_goods) {
        this.recommend_goods = recommend_goods;
    }

    public static class ValuesBean {
        /**
         * car_id : 29
         * user_id : 1
         * goods_id : 1
         * car_path : /goods_img/goods_img_2.jpg
         * shop_name : hehe
         * shop_color : 1
         * shop_size : 1
         * shop_num : 1
         * shop_price : 1
         * shop_select : 1
         */

        private String car_id;
        private String user_id;
        private String goods_id;
        private String car_path;
        private String shop_name;
        private String shop_color;
        private String shop_size;
        private String shop_num;
        private String shop_price;
        private String shop_select;
        private boolean isCheck = false;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public static ValuesBean objectFromData(String str) {

            return new Gson().fromJson(str, ValuesBean.class);
        }

        public String getCar_id() {
            return car_id;
        }

        public void setCar_id(String car_id) {
            this.car_id = car_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getCar_path() {
            return car_path;
        }

        public void setCar_path(String car_path) {
            this.car_path = car_path;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getShop_color() {
            return shop_color;
        }

        public void setShop_color(String shop_color) {
            this.shop_color = shop_color;
        }

        public String getShop_size() {
            return shop_size;
        }

        public void setShop_size(String shop_size) {
            this.shop_size = shop_size;
        }

        public String getShop_num() {
            return shop_num;
        }

        public void setShop_num(String shop_num) {
            this.shop_num = shop_num;
        }

        public String getShop_price() {
            return shop_price;
        }

        public void setShop_price(String shop_price) {
            this.shop_price = shop_price;
        }

        public String getShop_select() {
            return shop_select;
        }

        public void setShop_select(String shop_select) {
            this.shop_select = shop_select;
        }
    }

    public static class RecommendGoodsBean {
        /**
         * goods_id : 1
         * category_id : 1
         * brand_id : 1
         * shop_id : 1
         * goods_name : 漂亮的衣服1
         * goods_original_price : 120
         * goods_discount_price : 100
         * discount_detail : 8
         * goods_guarantee : 7天无理由退货
         * goods_popularity : 100
         * follow : 99
         * time : 1552221466
         * goods_img_id : 1
         * goods_img_path : /goods_img/goods_img1.jpg
         * detail_flag : 1
         */

        private String goods_id;
        private String category_id;
        private String brand_id;
        private String shop_id;
        private String goods_name;
        private String goods_original_price;
        private String goods_discount_price;
        private String discount_detail;
        private String goods_guarantee;
        private String goods_popularity;
        private String follow;
        private String time;
        private String goods_img_id;
        private String goods_img_path;
        private String detail_flag;

        public static RecommendGoodsBean objectFromData(String str) {

            return new Gson().fromJson(str, RecommendGoodsBean.class);
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(String brand_id) {
            this.brand_id = brand_id;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_original_price() {
            return goods_original_price;
        }

        public void setGoods_original_price(String goods_original_price) {
            this.goods_original_price = goods_original_price;
        }

        public String getGoods_discount_price() {
            return goods_discount_price;
        }

        public void setGoods_discount_price(String goods_discount_price) {
            this.goods_discount_price = goods_discount_price;
        }

        public String getDiscount_detail() {
            return discount_detail;
        }

        public void setDiscount_detail(String discount_detail) {
            this.discount_detail = discount_detail;
        }

        public String getGoods_guarantee() {
            return goods_guarantee;
        }

        public void setGoods_guarantee(String goods_guarantee) {
            this.goods_guarantee = goods_guarantee;
        }

        public String getGoods_popularity() {
            return goods_popularity;
        }

        public void setGoods_popularity(String goods_popularity) {
            this.goods_popularity = goods_popularity;
        }

        public String getFollow() {
            return follow;
        }

        public void setFollow(String follow) {
            this.follow = follow;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getGoods_img_id() {
            return goods_img_id;
        }

        public void setGoods_img_id(String goods_img_id) {
            this.goods_img_id = goods_img_id;
        }

        public String getGoods_img_path() {
            return goods_img_path;
        }

        public void setGoods_img_path(String goods_img_path) {
            this.goods_img_path = goods_img_path;
        }

        public String getDetail_flag() {
            return detail_flag;
        }

        public void setDetail_flag(String detail_flag) {
            this.detail_flag = detail_flag;
        }
    }
}
