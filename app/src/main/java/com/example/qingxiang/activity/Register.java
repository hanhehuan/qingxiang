package com.example.qingxiang.activity;
/*
 * 项目名： qingxiang
 * 包名： com.example.qingxiang.activity
 * 文件名： Register
 * 创建者：hanhehuann
 * 创建时间：2020-04-16 17:07
 * 描述：注册页面
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qingxiang.R;
import com.example.qingxiang.entity.User;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private Button login,register;
    private EditText username,password,nickname;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

    }

    //初始化view
    private void initView() {
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        nickname = findViewById(R.id.nickname);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                startActivity(new Intent(this,Login.class));
                finish();
                break;
            case R.id.register:
                String name = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String nick = nickname.getText().toString().trim();
                User user = new User();
                user.setNickname(nick);
                user.setUsername(name);
                user.setPassword(pass);
                if(name.equals("")){
                    Toast.makeText(this,"请输入用户名！",Toast.LENGTH_SHORT).show();
                }else if(pass.equals("")){
                    Toast.makeText(this,"请输入密码！",Toast.LENGTH_SHORT).show();
                }else {
                    user.signUp(new SaveListener<User>() {
                        @Override
                        public void done(User user, BmobException e) {
                            if(e == null){
                                Toast.makeText(Register.this,"注册成功",Toast.LENGTH_SHORT).show();
                                finish();
                            }else {

                                Toast.makeText(Register.this,"注册错误，错误码："+e.getErrorCode()+"："+e.getMessage(),Toast.LENGTH_LONG)
                                        .show();
                            }
                        }
                    });
                }


                break;
        }
    }
}
