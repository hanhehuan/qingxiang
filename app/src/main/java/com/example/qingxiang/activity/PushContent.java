package com.example.qingxiang.activity;
/*
 * 项目名： qingxiang
 * 包名： com.example.qingxiang.activity
 * 文件名： PushContent
 * 创建者：hanhehuann
 * 创建时间：2020-04-30 11:14
 * 描述：TODO
 */

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.qingxiang.R;
import com.example.qingxiang.entity.Post;
import com.example.qingxiang.entity.User;
import com.example.qingxiang.util.ToastUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class PushContent extends AppCompatActivity {

    private EditText content;
    private ImageView back;
    private Button push;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        
        initView();
        initData();
    }

    private void initData() {
        push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt = content.getText().toString();
                if(txt.isEmpty()){
                    ToastUtils.showShort(PushContent.this,"请输入内容！");
                }else {
                    User user = BmobUser.getCurrentUser(User.class);
                    Post post = new Post();
                    post.setName(user.getUsername());
                    post.setContent(txt);
                    post.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e==null){
                                ToastUtils.showShort(PushContent.this,"发布成功");
                                content.setText("");
                            }else {
                                ToastUtils.showShort(PushContent.this,"发布失败");
                            }
                        }
                    });
                }

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
        content = findViewById(R.id.content);
        back = findViewById(R.id.back);
        push = findViewById(R.id.push);
    }
}
