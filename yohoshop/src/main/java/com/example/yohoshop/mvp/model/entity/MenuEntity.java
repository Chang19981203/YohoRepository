package com.example.yohoshop.mvp.model.entity;

import com.google.gson.Gson;

import java.util.List;

public class MenuEntity extends BaseEntity{

    private List<ValuesBean> values;

    public static MenuEntity objectFromData(String str) {

        return new Gson().fromJson(str, MenuEntity.class);
    }

    public List<ValuesBean> getValues() {
        return values;
    }

    public void setValues(List<ValuesBean> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "MenuEntity{" +
                "values=" + values +
                '}';
    }

    public static class ValuesBean {
        /**
         * menu_id : 1
         * menu_name : MAN
         */

        private String menu_id;
        private String menu_name;

        public static ValuesBean objectFromData(String str) {

            return new Gson().fromJson(str, ValuesBean.class);
        }

        public String getMenu_id() {
            return menu_id;
        }

        public void setMenu_id(String menu_id) {
            this.menu_id = menu_id;
        }

        public String getMenu_name() {
            return menu_name;
        }

        public void setMenu_name(String menu_name) {
            this.menu_name = menu_name;
        }

        @Override
        public String toString() {
            return "ValuesBean{" +
                    "menu_id='" + menu_id + '\'' +
                    ", menu_name='" + menu_name + '\'' +
                    '}';
        }
    }
}
