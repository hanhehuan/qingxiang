package com.example.qingxiang.activity;
/*
 * 项目名： qingxiang
 * 包名： com.example.qingxiang.activity
 * 文件名： MyComunity
 * 创建者：hanhehuann
 * 创建时间：2020-05-07 16:05
 * 描述：TODO
 */

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qingxiang.Adapter.MyComunityAdapter;
import com.example.qingxiang.R;
import com.example.qingxiang.entity.Comunity;
import com.example.qingxiang.entity.User;
import com.example.qingxiang.util.ToastUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class MyComunity extends AppCompatActivity {

    private SwipeMenuRecyclerView mycomrv;
    private SwipeRefreshLayout mycom_swipe;
    private TextView mycom_error;
    private ImageView back;

    private List<Comunity> data;
    private MyComunityAdapter myComunityAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycomunity);

        initView();

        //初始刷新
        Refresh();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mycom_swipe.setColorSchemeResources(android.R.color.holo_green_light,android.R.color.holo_red_light,android.R.color.holo_blue_light);
        mycom_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Refresh();
            }
        });
    }

    private void Refresh() {

        //获取我发布的帖子  一对多
        User user = BmobUser.getCurrentUser(User.class);
        BmobQuery<Comunity> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("user",user);
        bmobQuery.order("-createdAt");
        bmobQuery.findObjects(new FindListener<Comunity>() {
            @Override
            public void done(List<Comunity> list, BmobException e) {
                mycom_swipe.setRefreshing(false);
                if (e==null){
                    data = list;
                    if (data.size()>0){
                        mycom_swipe.setVisibility(View.VISIBLE);

                        mycomrv.addItemDecoration(new DefaultItemDecoration(Color.GRAY));
                        mycomrv.setSwipeMenuCreator(swipeMenuCreator);
                        mycomrv.setSwipeMenuItemClickListener(swipeMenuItemClickListener);

                        myComunityAdapter = new MyComunityAdapter(MyComunity.this,data);
                        mycomrv.setLayoutManager(new LinearLayoutManager(MyComunity.this));
                        mycomrv.setAdapter(myComunityAdapter);

                    }else {
                        mycom_error.setVisibility(View.VISIBLE);
                    }
                }else {
                    ToastUtils.showShort(MyComunity.this,"加载失败"+e);
                    //Toast.makeText(MyComunity.this, "加载失败"+e, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // 设置菜单监听器。
    SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        // 创建菜单：
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.dp_70);
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            SwipeMenuItem deleteItem = new SwipeMenuItem(MyComunity.this)
                    .setTextColor(Color.WHITE)
                    .setBackgroundColor(Color.RED)
                    .setText("删除")
                    .setWidth(width)
                    .setHeight(height);
            swipeRightMenu.addMenuItem(deleteItem);
        }
    };

    // 菜单点击监听。
    SwipeMenuItemClickListener swipeMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection();//左边还是右边菜单
            final int adapterPosition = menuBridge.getAdapterPosition();//    recyclerView的Item的position。
            int position = menuBridge.getPosition();// 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {

                Comunity comunity = new Comunity();
                comunity.setObjectId(data.get(adapterPosition).getObjectId());
                comunity.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e==null){
                            data.remove(adapterPosition);//删除item
                            myComunityAdapter.notifyDataSetChanged();
                        }else {
                            ToastUtils.showShort(MyComunity.this,"删除失败");
                            //Toast.makeText(MyComunity.this, "删除失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

        }
    };

    private void initView() {
        mycomrv = findViewById(R.id.mycomrv);
        mycom_error = findViewById(R.id.mycom_error);
        mycom_swipe = findViewById(R.id.mycom_swipe);
        back = findViewById(R.id.back);
    }
}
