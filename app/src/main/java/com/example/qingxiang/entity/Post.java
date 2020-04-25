package com.example.qingxiang.entity;

import cn.bmob.v3.BmobObject;

public class Post extends BmobObject {

    private User author;//上传的对应用户

    private String title,content,nickname;//帖子对应的信息

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
