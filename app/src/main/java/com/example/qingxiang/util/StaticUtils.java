package com.example.qingxiang.util;
/*
 * 项目名： qingxiang
 * 包名： com.example.qingxiang.util
 * 文件名： StaticUtils
 * 创建者：hanhehuann
 * 创建时间：2020-04-24 13:56
 * 描述：TODO
 */

import android.Manifest;

public class StaticUtils {
    //bmob的应用key
    public static final String BMOB_KEY = "6ba4f479d1de89e19cb7702a32eebd96";
    //在SDCard中创建与删除文件权限
    //往SDCard写入数据权限
    public static final String[] SDCARD_PERMISSIONS = {Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS, Manifest.permission.WRITE_EXTERNAL_STORAGE};
}
