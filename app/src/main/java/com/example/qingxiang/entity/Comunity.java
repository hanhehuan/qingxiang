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
import cn.bmob.v3.datatype.BmobRelation;

public class Comunity extends BmobObject {

    private User user;
    private String name;
    private String info;
    private String owner;

    private BmobRelation relation;

    private String userOnlyId;

    private String isrelated;  //标识值

    public String getIsrelated() {
        return isrelated;
    }

    public void setIsrelated(String isrelated) {
        this.isrelated = isrelated;
    }

    public BmobRelation getRelation() {
        return relation;
    }

    public void setRelation(BmobRelation relation) {
        this.relation = relation;
    }

    public String getUserOnlyId() {
        return userOnlyId;
    }

    public void setUserOnlyId(String userOnlyId) {
        this.userOnlyId = userOnlyId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

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
