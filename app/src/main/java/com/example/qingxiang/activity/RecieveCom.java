package com.example.qingxiang.activity;
/*
 * 项目名： qingxiang
 * 包名： com.example.qingxiang.activity
 * 文件名： RecieveCom
 * 创建者：hanhehuann
 * 创建时间：2020-05-07 16:54
 * 描述：TODO
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qingxiang.R;
import com.example.qingxiang.entity.Comunity;
import com.example.qingxiang.entity.User;
import com.example.qingxiang.util.ToastUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class RecieveCom extends AppCompatActivity {

    private TextView rec_cname,rec_cinfo,rec_cuser;
    private ImageView reccom_collect;

    private ImageView com_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recievecom);

        initView();

        getinfo();

        getisrelated();

        com_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        reccom_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = getIntent();
                String Id = in.getStringExtra("id");
                BmobQuery<Comunity> bmobQuery = new BmobQuery<>();
                bmobQuery.getObject(Id, new QueryListener<Comunity>() {
                    @Override
                    public void done(Comunity comunity, BmobException e) {

                        if (comunity.getIsrelated().equals("0")){
                            Intent in = getIntent();
                            String Id = in.getStringExtra("id");
                            User user = BmobUser.getCurrentUser(User.class);
                            Comunity com = new Comunity();
                            comunity.setObjectId(Id);
                            comunity.setIsrelated("1");
                            BmobRelation relation = new BmobRelation();
                            relation.add(user);
                            comunity.setRelation(relation);
                            comunity.update(new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e==null){
                                        reccom_collect.setImageResource(R.drawable.shoucang_black);
                                        ToastUtils.showShort(RecieveCom.this,"收藏成功");
                                        //Toast.makeText(RecieveCom.this, "收藏成功", Toast.LENGTH_SHORT).show();
                                    }else {
                                        ToastUtils.showShort(RecieveCom.this,"收藏失败");
                                        //Toast.makeText(RecieveCom.this, "收藏失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else {
                            //取消收藏
                            Intent in = getIntent();
                            String Id = in.getStringExtra("id");
                            User user = BmobUser.getCurrentUser(User.class);
                            Comunity com = new Comunity();
                            comunity.setObjectId(Id);
                            comunity.setIsrelated("0");
                            BmobRelation relation = new BmobRelation();
                            relation.remove(user);
                            comunity.setRelation(relation);
                            comunity.update(new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e==null){
                                        reccom_collect.setImageResource(R.drawable.sc_normal);
                                        ToastUtils.showShort(RecieveCom.this,"取消收藏成功");
                                        //Toast.makeText(RecieveCom.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
                                    }else {
                                        //Toast.makeText(RecieveCom.this, "取消收藏失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }

                    }
                });


            }
        });

    }

    private void getisrelated() {
        Intent in = getIntent();
        String Id = in.getStringExtra("id");
        BmobQuery<Comunity> bmobQuery = new BmobQuery<>();
        bmobQuery.getObject(Id, new QueryListener<Comunity>() {
            @Override
            public void done(Comunity comunity, BmobException e) {
                if (comunity.getIsrelated().equals("1")){
                    //已被收藏
                    reccom_collect.setImageResource(R.drawable.shoucang_black);
                }else {

                }
            }
        });
    }

    private void getinfo() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("username");
        String info = intent.getStringExtra("info");
        String user_own = intent.getStringExtra("onw");
        rec_cname.setText(name);
        rec_cinfo.setText(info);
        rec_cuser.setText(user_own);
    }

    private void initView() {
        rec_cname = findViewById(R.id.rec_cname);
        rec_cinfo = findViewById(R.id.rec_cinfo);
        rec_cuser = findViewById(R.id.rec_cuser);
        reccom_collect = findViewById(R.id.reccom_collect);
        com_back = findViewById(R.id.com_back);
    }
}