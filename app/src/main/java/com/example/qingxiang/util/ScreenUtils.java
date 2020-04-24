package com.example.qingxiang.util;
/*
 * 项目名： qingxiang
 * 包名： com.example.qingxiang.util
 * 文件名： ScreenUtils
 * 创建者：hanhehuann
 * 创建时间：2020-04-24 15:13
 * 描述：TODO
 */

import android.content.Context;

public class ScreenUtils {

    /**
     * 获取屏幕高度(px)
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
    /**
     * 获取屏幕宽度(px)
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

}
