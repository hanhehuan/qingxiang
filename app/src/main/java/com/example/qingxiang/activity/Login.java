package com.example.qingxiang.activity;
/*
 * 项目名： qingxiang
 * 包名： com.example.qingxiang.activity
 * 文件名： Login
 * 创建者：hanhehuann
 * 创建时间：2020-04-16 10:06
 * 描述：TODO
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qingxiang.MainActivity;
import com.example.qingxiang.R;
import com.example.qingxiang.entity.User;
import com.example.qingxiang.util.ShareUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private Button login,register;
    private EditText username;
    private EditText password;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login.setOnClickListener(this);//
        register.setOnClickListener(this);

        if(BmobUser.isLogin()){
            User user = BmobUser.getCurrentUser(User.class);
            startActivity(new Intent(Login.this, MainActivity.class));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                String name = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                //进行登录操作
                loginClass(name,pass);
                finish();
                break;
            case R.id.register:
                startActivity(new Intent(this,Register.class));
                finish();
                break;
        }
    }

    private void loginClass(String name, String pass) {

        User user = new User();
        user.setUsername(name);
        user.setPassword(pass);
        user.login(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(e == null){
                    //如果登录成功
                    User user1 = BmobUser.getCurrentUser(User.class);

                    ShareUtils.putString(Login.this,"username",user1.getUsername());
                    Toast.makeText(Login.this,"登录成功",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, MainActivity.class));
                }else {
                    Toast.makeText(Login.this,"登录失败",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
