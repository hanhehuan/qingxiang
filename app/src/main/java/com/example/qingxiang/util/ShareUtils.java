package com.example.qingxiang.util;
/*
 * 项目名： SmartButler
 * 包名： com.example.smartbutler.utils
 * 文件名： ShareUtils
 * 创建者：hanhehuann
 * 创建时间：2019-12-30 14:48
 * 描述：SharedPreferences封装
 */

import android.content.Context;
import android.content.SharedPreferences;

public class ShareUtils {

    /*private void test(Context mContext){
        SharedPreferences sp = mContext.getSharedPreferences("config",Context.MODE_PRIVATE);
        sp.getString("key","未获取到");
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("key","value");
        editor.commit();
    }*/
    public static final String NAME = "qingxiang";
    //键 值
    public static void putString(Context mContext,String key,String value){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }
    //键 默认值
    public static String getString(Context mContext,String key,String defvalue){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getString(key,defvalue);
    }
    //键 值
    public static void putInt(Context mContext,String key,int value){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putInt(key,value).commit();
    }
    //键 默认值
    public static int getInt(Context mContext,String key,int defvalue){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getInt(key,defvalue);
    }
    //键 值
    public static void putBoolean(Context mContext,String key,boolean value){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }
    //键 默认值
    public static boolean getBoolean(Context mContext,String key,boolean defvalue){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getBoolean(key,defvalue);
    }
    //删除单个
    public static void deleShare(Context mContext,String key){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }
    //删除全部
    public static void deleAll(Context mContext){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }
}
