package com.example.qingxiang.util;
/*
 * 项目名： qingxiang
 * 包名： com.example.qingxiang.util
 * 文件名： NoScrollViewPageUtils
 * 创建者：hanhehuann
 * 创建时间：2020-04-29 10:27
 * 描述：TODO
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class NoScrollViewPageUtils extends ViewPager {

    private Boolean NoScroll = true;

    public void setNoScroll(Boolean noScroll) {
        NoScroll = noScroll;
    }

    public NoScrollViewPageUtils(@NonNull Context context) {
        super(context);
    }

    public NoScrollViewPageUtils(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(NoScroll){
            return false;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(NoScroll){
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void setCurrentItem(int item) {

        super.setCurrentItem(item);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {

        super.setCurrentItem(item, smoothScroll);
    }
}
