package com.example.yohoshop.mvp.model.entity;

import com.google.gson.Gson;

import java.util.List;


public class RecommendEntity extends BaseEntity {

    private List<RecommendBean> recommend;
    private List<CategoryBean> category;

    public static RecommendEntity objectFromData(String str) {

        return new Gson().fromJson(str, RecommendEntity.class);
    }

    public List<RecommendBean> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<RecommendBean> recommend) {
        this.recommend = recommend;
    }

    public List<CategoryBean> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryBean> category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "RecommendEntity{" +
                "recommend=" + recommend +
                ", category=" + category +
                '}';
    }

    public static class RecommendBean {
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

        public static RecommendBean objectFromData(String str) {

            return new Gson().fromJson(str, RecommendBean.class);
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
            return "RecommendBean{" +
                    "recommend_id='" + recommend_id + '\'' +
                    ", recommend_name='" + recommend_name + '\'' +
                    ", recommend_url='" + recommend_url + '\'' +
                    ", recommend_path='" + recommend_path + '\'' +
                    '}';
        }
    }

    public static class CategoryBean {
        /**
         * category_id : 1
         * menu_id : 1
         * category_name : 上衣
         * category_img_path : /brand_img/brand_big_1.jpg
         */

        private String category_id;
        private String menu_id;
        private String category_name;
        private String category_img_path;

        public static CategoryBean objectFromData(String str) {

            return new Gson().fromJson(str, CategoryBean.class);
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getMenu_id() {
            return menu_id;
        }

        public void setMenu_id(String menu_id) {
            this.menu_id = menu_id;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public String getCategory_img_path() {
            return category_img_path;
        }

        public void setCategory_img_path(String category_img_path) {
            this.category_img_path = category_img_path;
        }

        @Override
        public String toString() {
            return "CategoryBean{" +
                    "category_id='" + category_id + '\'' +
                    ", menu_id='" + menu_id + '\'' +
                    ", category_name='" + category_name + '\'' +
                    ", category_img_path='" + category_img_path + '\'' +
                    '}';
        }
    }
}
