package com.example.qingxiang.util;
/*
 * 项目名： qingxiang
 * 包名： com.example.qingxiang.util
 * 文件名： ToastUtils
 * 创建者：hanhehuann
 * 创建时间：2020-04-24 14:02
 * 描述：TODO
 */


import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    //短提示
    public static void showShort(Context context, String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
    //长提示
    public static void showLong(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }
}
