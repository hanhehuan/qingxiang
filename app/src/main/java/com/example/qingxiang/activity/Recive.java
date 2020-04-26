package com.example.qingxiang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qingxiang.R;
import com.example.qingxiang.entity.Post;
import com.example.qingxiang.util.ToastUtils;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class Recive extends AppCompatActivity implements View.OnClickListener {

    private TextView username,content,time;
    private ImageView back;
    private FrameLayout frame_sx;
    private Button sx;

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
                    frame_sx.setVisibility(View.GONE);
                }else {
                    ToastUtils.showShort(Recive.this,"获取失败");
                    frame_sx.setVisibility(View.VISIBLE);
                }
            }
        });



    }

    private void initView() {
        username = findViewById(R.id.username);
        content = findViewById(R.id.content);
        time = findViewById(R.id.time);
        back = findViewById(R.id.back);
        frame_sx = findViewById(R.id.frame_sx);
        sx = findViewById(R.id.sx);
        back.setOnClickListener(this);
        sx.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.sx:
                initData();
                break;
        }
    }
}
