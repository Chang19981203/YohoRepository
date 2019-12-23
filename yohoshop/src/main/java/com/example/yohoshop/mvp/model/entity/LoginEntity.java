package com.example.yohoshop.mvp.model.entity;

import com.google.gson.Gson;

import java.util.List;

public class LoginEntity extends BaseEntity{


    private List<ValuesBean> values;

    public static LoginEntity objectFromData(String str) {

        return new Gson().fromJson(str, LoginEntity.class);
    }

    public List<ValuesBean> getValues() {
        return values;
    }

    public void setValues(List<ValuesBean> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "LoginEntity{" +
                "values=" + values +
                '}';
    }

    public static class ValuesBean {
        /**
         * user_id : 1
         * nick_name : 王忠华
         * qr_code : /qr_img/wzh.png
         * user_name : wangzhonghua
         * user_pwd : 123456
         * user_sex : 男
         * user_birthday : 1552221466
         * user_height : 170cm
         * user_weight : 190kg
         * user_head : /head_img/wzh_head.png
         */

        private String user_id;
        private String nick_name;
        private String qr_code;
        private String user_name;
        private String user_pwd;
        private String user_sex;
        private String user_birthday;
        private String user_height;
        private String user_weight;
        private String user_head;

        public static ValuesBean objectFromData(String str) {

            return new Gson().fromJson(str, ValuesBean.class);
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getQr_code() {
            return qr_code;
        }

        public void setQr_code(String qr_code) {
            this.qr_code = qr_code;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_pwd() {
            return user_pwd;
        }

        public void setUser_pwd(String user_pwd) {
            this.user_pwd = user_pwd;
        }

        public String getUser_sex() {
            return user_sex;
        }

        public void setUser_sex(String user_sex) {
            this.user_sex = user_sex;
        }

        public String getUser_birthday() {
            return user_birthday;
        }

        public void setUser_birthday(String user_birthday) {
            this.user_birthday = user_birthday;
        }

        public String getUser_height() {
            return user_height;
        }

        public void setUser_height(String user_height) {
            this.user_height = user_height;
        }

        public String getUser_weight() {
            return user_weight;
        }

        public void setUser_weight(String user_weight) {
            this.user_weight = user_weight;
        }

        public String getUser_head() {
            return user_head;
        }

        public void setUser_head(String user_head) {
            this.user_head = user_head;
        }

        @Override
        public String toString() {
            return "ValuesBean{" +
                    "user_id='" + user_id + '\'' +
                    ", nick_name='" + nick_name + '\'' +
                    ", qr_code='" + qr_code + '\'' +
                    ", user_name='" + user_name + '\'' +
                    ", user_pwd='" + user_pwd + '\'' +
                    ", user_sex='" + user_sex + '\'' +
                    ", user_birthday='" + user_birthday + '\'' +
                    ", user_height='" + user_height + '\'' +
                    ", user_weight='" + user_weight + '\'' +
                    ", user_head='" + user_head + '\'' +
                    '}';
        }
    }
}
