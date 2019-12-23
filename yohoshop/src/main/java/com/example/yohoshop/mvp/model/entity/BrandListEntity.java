package com.example.yohoshop.mvp.model.entity;

import com.google.gson.Gson;

import java.util.List;

public class BrandListEntity extends BaseEntity {


    private List<ValuesBean> values;

    public static BrandListEntity objectFromData(String str) {

        return new Gson().fromJson(str, BrandListEntity.class);
    }

    public List<ValuesBean> getValues() {
        return values;
    }

    public void setValues(List<ValuesBean> values) {
        this.values = values;
    }

    public static class ValuesBean {
        /**
         * brand_id : 1
         * menu_id : 1
         * brand_letter : A
         * brand_name : AAAA
         * hot_tag : true
         * brand_time : 1552221466
         * brand_icon : /brand_img/brand1.png
         * brand_bg : /brand_img/brand_big_1.jpg
         */

        private String brand_id;
        private String menu_id;
        private String brand_letter;
        private String brand_name;
        private String hot_tag;
        private String brand_time;
        private String brand_icon;
        private String brand_bg;

        public static ValuesBean objectFromData(String str) {

            return new Gson().fromJson(str, ValuesBean.class);
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

        public String getBrand_letter() {
            return brand_letter;
        }

        public void setBrand_letter(String brand_letter) {
            this.brand_letter = brand_letter;
        }

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public String getHot_tag() {
            return hot_tag;
        }

        public void setHot_tag(String hot_tag) {
            this.hot_tag = hot_tag;
        }

        public String getBrand_time() {
            return brand_time;
        }

        public void setBrand_time(String brand_time) {
            this.brand_time = brand_time;
        }

        public String getBrand_icon() {
            return brand_icon;
        }

        public void setBrand_icon(String brand_icon) {
            this.brand_icon = brand_icon;
        }

        public String getBrand_bg() {
            return brand_bg;
        }

        public void setBrand_bg(String brand_bg) {
            this.brand_bg = brand_bg;
        }

        @Override
        public String toString() {
            return "ValuesBean{" +
                    "brand_id='" + brand_id + '\'' +
                    ", menu_id='" + menu_id + '\'' +
                    ", brand_letter='" + brand_letter + '\'' +
                    ", brand_name='" + brand_name + '\'' +
                    ", hot_tag='" + hot_tag + '\'' +
                    ", brand_time='" + brand_time + '\'' +
                    ", brand_icon='" + brand_icon + '\'' +
                    ", brand_bg='" + brand_bg + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BrandListEntity{" +
                "values=" + values +
                '}';
    }
}
