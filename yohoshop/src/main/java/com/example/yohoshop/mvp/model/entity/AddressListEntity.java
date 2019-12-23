package com.example.yohoshop.mvp.model.entity;

import com.google.gson.Gson;

import java.util.List;

public class AddressListEntity extends BaseEntity {

    private List<ValuesBean> values;

    public static AddressListEntity objectFromData(String str) {

        return new Gson().fromJson(str, AddressListEntity.class);
    }

    public List<ValuesBean> getValues() {
        return values;
    }

    public void setValues(List<ValuesBean> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "AddressListEntity{" +
                "values=" + values +
                '}';
    }

    public static class ValuesBean {
        /**
         * address_id : 1
         * user_id : 1
         * user_name : 王忠华
         * phone : 13888888888
         * address_area : 北京市海淀区
         * address_detail : 八维研修学院
         * address_tag : 学校
         * address_default : 1
         */

        private String address_id;
        private String user_id;
        private String user_name;
        private String phone;
        private String address_area;
        private String address_detail;
        private String address_tag;
        private String address_default;

        public static ValuesBean objectFromData(String str) {

            return new Gson().fromJson(str, ValuesBean.class);
        }

        public String getAddress_id() {
            return address_id;
        }

        public void setAddress_id(String address_id) {
            this.address_id = address_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress_area() {
            return address_area;
        }

        public void setAddress_area(String address_area) {
            this.address_area = address_area;
        }

        public String getAddress_detail() {
            return address_detail;
        }

        public void setAddress_detail(String address_detail) {
            this.address_detail = address_detail;
        }

        public String getAddress_tag() {
            return address_tag;
        }

        public void setAddress_tag(String address_tag) {
            this.address_tag = address_tag;
        }

        public String getAddress_default() {
            return address_default;
        }

        public void setAddress_default(String address_default) {
            this.address_default = address_default;
        }

        @Override
        public String toString() {
            return "ValuesBean{" +
                    "address_id='" + address_id + '\'' +
                    ", user_id='" + user_id + '\'' +
                    ", user_name='" + user_name + '\'' +
                    ", phone='" + phone + '\'' +
                    ", address_area='" + address_area + '\'' +
                    ", address_detail='" + address_detail + '\'' +
                    ", address_tag='" + address_tag + '\'' +
                    ", address_default='" + address_default + '\'' +
                    '}';
        }
    }
}
