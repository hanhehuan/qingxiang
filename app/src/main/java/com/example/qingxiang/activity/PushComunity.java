package com.example.qingxiang.activity;
/*
 * 项目名： qingxiang
 * 包名： com.example.qingxiang.activity
 * 文件名： PushComunity
 * 创建者：hanhehuann
 * 创建时间：2020-04-30 11:15
 * 描述：TODO
 */

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.qingxiang.R;
import com.example.qingxiang.entity.Comunity;
import com.example.qingxiang.entity.User;
import com.example.qingxiang.util.ToastUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class PushComunity extends AppCompatActivity {

    private EditText name;
    private EditText desc;
    private Button add;
    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comunity);
        
        initView();
        initData();
    }

    private void initData() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = BmobUser.getCurrentUser(User.class);
                Comunity comunity = new Comunity();
                comunity.setName(name.getText().toString());
                comunity.setInfo(desc.getText().toString());
                comunity.setUser(user);
                comunity.setOwner(user.getUsername());
                comunity.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if(e==null){
                            ToastUtils.showShort(PushComunity.this,"创建成功！");
                            finish();
                        }else {
                            ToastUtils.showShort(PushComunity.this,"创建失败！");

                        }
                    }
                });

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        name = findViewById(R.id.comunity_name);
        desc = findViewById(R.id.comunity_desc);
        back = findViewById(R.id.back);
        add = findViewById(R.id.add);
    }
}
