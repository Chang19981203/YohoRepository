package com.example.yohoshop.mvp.model.entity;

/**
 * @ProjectName: Yoho
 * @Package: com.example.yohoshop.mvp.model.entity
 * @ClassName: BaseEntity
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/11/29 18:52
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/11/29 18:52
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */


public class BaseEntity {
    private String stataue;
    private String msg;

    public String getStataue() {
        return stataue;
    }

    public void setStataue(String stataue) {
        this.stataue = stataue;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
