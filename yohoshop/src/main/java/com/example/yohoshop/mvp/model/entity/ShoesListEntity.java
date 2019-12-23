package com.example.yohoshop.mvp.model.entity;

import com.google.gson.Gson;

import java.util.List;

public class ShoesListEntity extends BaseEntity {

    private List<BannerBean> banner;
    private List<BrandBean> brand;
    private List<ValuesBean> values;

    public static ShoesListEntity objectFromData(String str) {

        return new Gson().fromJson(str, ShoesListEntity.class);
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<BrandBean> getBrand() {
        return brand;
    }

    public void setBrand(List<BrandBean> brand) {
        this.brand = brand;
    }

    public List<ValuesBean> getValues() {
        return values;
    }

    public void setValues(List<ValuesBean> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "ShoesListEntity{" +
                "banner=" + banner +
                ", brand=" + brand +
                ", values=" + values +
                '}';
    }

    public static class BannerBean {
        /**
         * recommend_id : 1
         * recommend_name : 重点推荐1
         * recommend_url : /recommend_img/recommend1.jpg
         * recommend_path : http://www.baidu.com/
         */

        private String recommend_id;
        private String recommend_name;
        private String recommend_url;
        private String recommend_path;

        public static BannerBean objectFromData(String str) {

            return new Gson().fromJson(str, BannerBean.class);
        }

        public String getRecommend_id() {
            return recommend_id;
        }

        public void setRecommend_id(String recommend_id) {
            this.recommend_id = recommend_id;
        }

        public String getRecommend_name() {
            return recommend_name;
        }

        public void setRecommend_name(String recommend_name) {
            this.recommend_name = recommend_name;
        }

        public String getRecommend_url() {
            return recommend_url;
        }

        public void setRecommend_url(String recommend_url) {
            this.recommend_url = recommend_url;
        }

        public String getRecommend_path() {
            return recommend_path;
        }

        public void setRecommend_path(String recommend_path) {
            this.recommend_path = recommend_path;
        }

        @Override
        public String toString() {
            return "BannerBean{" +
                    "recommend_id='" + recommend_id + '\'' +
                    ", recommend_name='" + recommend_name + '\'' +
                    ", recommend_url='" + recommend_url + '\'' +
                    ", recommend_path='" + recommend_path + '\'' +
                    '}';
        }
    }

    public static class BrandBean {
        /**
         * brand_id : 1
         * menu_id : 1
         * brand_name : AAAA
         * brand_icon : /brand_img/brand1.png
         */

        private String brand_id;
        private String menu_id;
        private String brand_name;
        private String brand_icon;

        public static BrandBean objectFromData(String str) {

            return new Gson().fromJson(str, BrandBean.class);
        }

        public String getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(String brand_id) {
            this.brand_id = brand_id;
        }

        public String getMenu_id() {
            return menu_id;
        }

        public void setMenu_id(String menu_id) {
            this.menu_id = menu_id;
        }

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public String getBrand_icon() {
            return brand_icon;
        }

        public void setBrand_icon(String brand_icon) {
            this.brand_icon = brand_icon;
        }

        @Override
        public String toString() {
            return "BrandBean{" +
                    "brand_id='" + brand_id + '\'' +
                    ", menu_id='" + menu_id + '\'' +
                    ", brand_name='" + brand_name + '\'' +
                    ", brand_icon='" + brand_icon + '\'' +
                    '}';
        }
    }

    public static class ValuesBean {
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

        public static ValuesBean objectFromData(String str) {

            return new Gson().fromJson(str, ValuesBean.class);
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

        @Override
        public String toString() {
            return "ValuesBean{" +
                    "goods_id='" + goods_id + '\'' +
                    ", category_id='" + category_id + '\'' +
                    ", brand_id='" + brand_id + '\'' +
                    ", shop_id='" + shop_id + '\'' +
                    ", goods_name='" + goods_name + '\'' +
                    ", goods_original_price='" + goods_original_price + '\'' +
                    ", goods_discount_price='" + goods_discount_price + '\'' +
                    ", discount_detail='" + discount_detail + '\'' +
                    ", goods_guarantee='" + goods_guarantee + '\'' +
                    ", goods_popularity='" + goods_popularity + '\'' +
                    ", follow='" + follow + '\'' +
                    ", time='" + time + '\'' +
                    ", goods_img_id='" + goods_img_id + '\'' +
                    ", goods_img_path='" + goods_img_path + '\'' +
                    ", detail_flag='" + detail_flag + '\'' +
                    '}';
        }
    }
}
