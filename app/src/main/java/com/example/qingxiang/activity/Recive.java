package com.example.qingxiang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qingxiang.R;
import com.example.qingxiang.entity.Post;
import com.example.qingxiang.util.ToastUtils;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class Recive extends AppCompatActivity {

    private TextView username,content,time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recive);

        initView();
        initData();
    }

    private void initData() {
        Intent in = getIntent();
        String id = in.getStringExtra("id");
        BmobQuery<Post> query = new BmobQuery<>();
        query.getObject(id, new QueryListener<Post>() {
            @Override
            public void done(Post post, BmobException e) {
                if(e==null){
                    username.setText(post.getName());
                    content.setText(post.getContent());
                    time.setText(post.getCreatedAt());
                }else {
                    ToastUtils.showShort(Recive.this,"获取失败");
                }
            }
        });
    }

    private void initView() {
        username = findViewById(R.id.username);
        content = findViewById(R.id.content);
        time = findViewById(R.id.time);
    }
}
