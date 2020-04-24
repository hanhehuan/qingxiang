package com.example.qingxiang.application;
/*
 * 项目名： qingxiang
 * 包名： com.example.qingxiang.application
 * 文件名： MyApplication
 * 创建者：hanhehuann
 * 创建时间：2020-04-24 14:13
 * 描述：TODO
 */

import android.app.Application;
import android.content.Context;

import com.example.qingxiang.util.StaticUtils;

import cn.bmob.v3.Bmob;

public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        //初始化bmob
        Bmob.initialize(this, StaticUtils.BMOB_KEY);
    }

    public static Context getInstance(){
        return mContext;
    }
}
