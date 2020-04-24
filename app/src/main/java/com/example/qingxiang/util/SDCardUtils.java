package com.example.qingxiang.util;
/*
 * 项目名： qingxiang
 * 包名： com.example.qingxiang.util
 * 文件名： StatsUtils
 * 创建者：hanhehuann
 * 创建时间：2020-04-16 8:49
 * 描述：TODO
 */

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;

import androidx.core.content.ContextCompat;

public class SDCardUtils {

    /**
     * 判断有没有sd卡
     * @return
     */
    public static boolean ExistSDCard(){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            return true;
        }else {
            return false;
        }

    }

    /**
     * 获取sd卡空闲的空间 MB
     * @return
     */
    public static long getSDFreeSize(){
        File path = Environment.getExternalStorageDirectory();
        StatFs sf = new StatFs(path.getPath());
        //获取单个数据块的大小
        long blockSize;
        //空闲的数据块数量
        long freeBlocks;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){//api大于等于18
            blockSize = sf.getBlockSizeLong();
            freeBlocks = sf.getAvailableBlocksLong();
        }else {
            blockSize = sf.getBlockSize();
            freeBlocks = sf.getAvailableBlocks();
        }

        return (blockSize * freeBlocks)/1024/1024;
    }

    /**
     * 获取sd卡空闲的空间 MB
     * @return
     */
    public static long getSDAllSize(){
        File path = Environment.getExternalStorageDirectory();
        StatFs sf = new StatFs(path.getPath());
        //获取单个数据块的大小
        long blockSize;
        //总的数据块数量
        long AllBlocks;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){//api大于等于18
            blockSize = sf.getBlockSizeLong();
            AllBlocks = sf.getBlockCountLong();
        }else {
            blockSize = sf.getBlockSize();
            AllBlocks = sf.getBlockCount();
        }
        return (blockSize * AllBlocks)/1024/1024;
    }

    /**
     * 判断sd中能否继续保存文件
     * @param size
     * @return
     */
    public static boolean checkSDCrad(long size){
        if(getSDFreeSize()>size){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 判断是否缺少权限
     * @param context
     * @param permisson
     * @return
     */
    public static boolean lacksPermission(Context context, String permisson){
        return ContextCompat.checkSelfPermission(context,permisson) ==
                PackageManager.PERMISSION_DENIED;
    }

    /**
     * 判断是否开启了权限组 true为没开启，false为已开启
     * @param context
     * @return false 为没开启权限 ，true 为已开启权限
     */
    public static boolean lacksPermissions(Context context,String[] mPermissions){
        for(String permisson:mPermissions){
            if(lacksPermission(context,permisson)){
                //没有开启权限
                return false;
            }
        }
        //权限已开启
        return true;
    }
}
