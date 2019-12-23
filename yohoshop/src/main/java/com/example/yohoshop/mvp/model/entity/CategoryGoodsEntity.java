package com.example.yohoshop.mvp.model.entity;

import com.google.gson.Gson;

import java.util.List;

public class CategoryGoodsEntity extends BaseEntity{

    private List<ValuesBean> values;

    public static CategoryGoodsEntity objectFromData(String str) {

        return new Gson().fromJson(str, CategoryGoodsEntity.class);
    }

    public List<ValuesBean> getValues() {
        return values;
    }

    public void setValues(List<ValuesBean> values) {
        this.values = values;
    }

    public static class ValuesBean {
        /**
         * category_id : 1
         * name : 上衣1
         * image_path : /goods_img/goods_img_1.jpg
         */

        private String category_id;
        private String name;
        private String image_path;

        public static ValuesBean objectFromData(String str) {

            return new Gson().fromJson(str, ValuesBean.class);
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage_path() {
            return image_path;
        }

        public void setImage_path(String image_path) {
            this.image_path = image_path;
        }

        @Override
        public String toString() {
            return "ValuesBean{" +
                    "category_id='" + category_id + '\'' +
                    ", name='" + name + '\'' +
                    ", image_path='" + image_path + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CategoryGoodsEntity{" +
                "values=" + values +
                '}';
    }
}
