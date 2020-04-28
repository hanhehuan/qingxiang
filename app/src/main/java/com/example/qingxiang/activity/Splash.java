package com.example.qingxiang.activity;
/*
 * 项目名： qingxiang
 * 包名： com.example.qingxiang.activity
 * 文件名： Splash
 * 创建者：hanhehuann
 * 创建时间：2020-04-16 8:50
 * 描述：引导页
 */

import android.content.Intent;
import android.os.Bundle;



import com.example.qingxiang.R;


import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Bmob.initialize(this, StaticUtils.BMOB_KEY);

        Timer timer = new Timer();
        timer.schedule(timetask,2000);



    }
    TimerTask timetask = new TimerTask() {
        @Override
        public void run() {
            startActivity(new Intent(Splash.this, Login.class));
            finish();
        }
    };
}
