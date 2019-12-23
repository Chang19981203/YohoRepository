package com.example.yohoshop.mvp.model.entity;

public class LeftRecycleEntity {
    private boolean isShow = false;
    private String name;

    public LeftRecycleEntity(boolean isShow, String name) {
        this.isShow = isShow;
        this.name = name;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LeftRecycleEntity{" +
                "isShow=" + isShow +
                ", name='" + name + '\'' +
                '}';
    }
}
