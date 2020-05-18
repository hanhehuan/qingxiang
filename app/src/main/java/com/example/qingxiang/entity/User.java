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

/**
 * 用户类
 */
public class User extends BmobUser {

    private String nickname;
    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getFocusId_sum() {
        return focusId_sum;
    }

    public void setFocusId_sum(Integer focusId_sum) {
        this.focusId_sum = focusId_sum;
    }

    //关注的人数
    private Integer focusId_sum = 0;

    //private user_followers follower_id;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
