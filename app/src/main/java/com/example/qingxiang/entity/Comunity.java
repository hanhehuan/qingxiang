package com.example.qingxiang.entity;
/*
 * 项目名： qingxiang
 * 包名： com.example.qingxiang.entity
 * 文件名： Comunity
 * 创建者：hanhehuann
 * 创建时间：2020-04-29 12:20
 * 描述：
 */

import cn.bmob.v3.BmobObject;

public class Comunity extends BmobObject {

    private User user;
    private String name;
    private String info;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
