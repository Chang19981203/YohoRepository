package com.example.yohoshop.mvp.model.entity;

import com.google.gson.Gson;

import java.util.List;

public class CommunityEntity extends BaseEntity {

    private List<BannerBean> banner;
    private List<ValuesBean> values;

    public static CommunityEntity objectFromData(String str) {

        return new Gson().fromJson(str, CommunityEntity.class);
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<ValuesBean> getValues() {
        return values;
    }

    public void setValues(List<ValuesBean> values) {
        this.values = values;
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
    }

    public static class ValuesBean {
        /**
         * community_id : 1
         * user_id : 1
         * title : 王忠华上头条
         * community_values : 头条王忠华
         * community_time : 1553436563
         * tag : 华仔
         * hot_flag : 1
         * fabulous : 100
         * collection : 20
         * imgs : [{"community_img_id":"1","community_id":"1","img_path":"/goods_img/goods_img_1"},{"community_img_id":"2","community_id":"1","img_path":"/goods_img/goods_img_2"},{"community_img_id":"3","community_id":"1","img_path":"/goods_img/goods_img_3"},{"community_img_id":"4","community_id":"1","img_path":"/goods_img/goods_img_4"},{"community_img_id":"5","community_id":"1","img_path":"/goods_img/goods_img_5"},{"community_img_id":"6","community_id":"1","img_path":"/goods_img/goods_img_6"},{"community_img_id":"7","community_id":"1","img_path":"/goods_img/goods_img_7"},{"community_img_id":"8","community_id":"1","img_path":"/goods_img/goods_img_8"},{"community_img_id":"9","community_id":"1","img_path":"/goods_img/goods_img_9"}]
         */

        private String community_id;
        private String user_id;
        private String title;
        private String community_values;
        private String community_time;
        private String tag;
        private String hot_flag;
        private String fabulous;
        private String collection;
        private List<ImgsBean> imgs;

        public static ValuesBean objectFromData(String str) {

            return new Gson().fromJson(str, ValuesBean.class);
        }

        public String getCommunity_id() {
            return community_id;
        }

        public void setCommunity_id(String community_id) {
            this.community_id = community_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCommunity_values() {
            return community_values;
        }

        public void setCommunity_values(String community_values) {
            this.community_values = community_values;
        }

        public String getCommunity_time() {
            return community_time;
        }

        public void setCommunity_time(String community_time) {
            this.community_time = community_time;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getHot_flag() {
            return hot_flag;
        }

        public void setHot_flag(String hot_flag) {
            this.hot_flag = hot_flag;
        }

        public String getFabulous() {
            return fabulous;
        }

        public void setFabulous(String fabulous) {
            this.fabulous = fabulous;
        }

        public String getCollection() {
            return collection;
        }

        public void setCollection(String collection) {
            this.collection = collection;
        }

        public List<ImgsBean> getImgs() {
            return imgs;
        }

        public void setImgs(List<ImgsBean> imgs) {
            this.imgs = imgs;
        }

        public static class ImgsBean {
            /**
             * community_img_id : 1
             * community_id : 1
             * img_path : /goods_img/goods_img_1
             */

            private String community_img_id;
            private String community_id;
            private String img_path;

            public static ImgsBean objectFromData(String str) {

                return new Gson().fromJson(str, ImgsBean.class);
            }

            public String getCommunity_img_id() {
                return community_img_id;
            }

            public void setCommunity_img_id(String community_img_id) {
                this.community_img_id = community_img_id;
            }

            public String getCommunity_id() {
                return community_id;
            }

            public void setCommunity_id(String community_id) {
                this.community_id = community_id;
            }

            public String getImg_path() {
                return img_path;
            }

            public void setImg_path(String img_path) {
                this.img_path = img_path;
            }
        }
    }
}
