package com.example.qingxiang.entity;
/*
 * 项目名： qingxiang
 * 包名： com.example.qingxiang.entity
 * 文件名： User
 * 创建者：hanhehuann
 * 创建时间：2020-04-16 16:48
 * 描述：TODO
 */

import cn.bmob.v3.BmobUser;

public class User extends BmobUser {

    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
