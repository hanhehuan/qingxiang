package com.example.qingxiang.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.qingxiang.Adapter.ChatAdapter;
import com.example.qingxiang.R;
import com.example.qingxiang.activity.PushComunity;
import com.example.qingxiang.activity.PushContent;
import com.example.qingxiang.entity.Comunity;
import com.example.qingxiang.entity.User;
import com.example.qingxiang.util.ToastUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 论坛
 */
public class FragmentChat extends Fragment {

    private static final String TAG = "FragmentChat";

    private RecyclerView rv2;
    private SwipeRefreshLayout swipeRefreshLayout2;
    private List<Comunity> comunityList;
    private ChatAdapter chatAdapter;
    private FloatingActionButton add,addcontent,addcomunity;
    //private PopupWindow pop;
    //private RelativeLayout rv_layout;
    //private View pop_view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //pop_view = getLayoutInflater().inflate(R.layout.pop_item,null);
        initView();
        initData();
    }

    private void initData() {
        Refresh2();//初始刷新
        swipeRefreshLayout2.setColorSchemeResources(R.color.holo_green_light,R.color.holo_red_light,R.color.holo_blue_light);
        swipeRefreshLayout2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Refresh2();//刷新
            }
        });

        //发帖点击
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addcontent.setVisibility(addcontent.getVisibility()==View.VISIBLE?View.GONE:View.VISIBLE);
                addcomunity.setVisibility(addcomunity.getVisibility()==View.VISIBLE?View.GONE:View.VISIBLE);
                /*pop = new PopupWindow(pop_view,add.getWidth(),150,true);
                pop.setOutsideTouchable(true);
                pop.setFocusable(true);
                pop.showAtLocation(rv_layout, Gravity.END,add.getScrollX(),add.getScrollY());*/

            }
        });

        addcontent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showShort(getContext(),"点击了编辑按钮");
                startActivity(new Intent(getActivity(), PushContent.class));
            }
        });

        addcomunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showShort(getContext(),"点击了发帖按钮");
                startActivity(new Intent(getActivity(), PushComunity.class));
            }
        });

    }

    private void Refresh2() {
        BmobQuery<Comunity> com = new BmobQuery<>();
        com.setLimit(1000);
        com.order("-createdAt");
        com.include("user");
        com.findObjects(new FindListener<Comunity>() {
            @Override
            public void done(List<Comunity> object, BmobException e) {
                swipeRefreshLayout2.setRefreshing(false);
                if(e==null){
                    comunityList = object;
                    chatAdapter = new ChatAdapter(getActivity(),comunityList);
                    rv2.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv2.setAdapter(chatAdapter);
                }else {
                    ToastUtils.showShort(getActivity(),"加载失败");
                }
            }
        });
    }

    private void initView(){
        rv2 = getActivity().findViewById(R.id.rv2);
        swipeRefreshLayout2 = getActivity().findViewById(R.id.swipe2);
        add = getActivity().findViewById(R.id.fab);
        addcontent = getActivity().findViewById(R.id.add_content);
        addcomunity = getActivity().findViewById(R.id.add_comunity);
        //rv_layout = getActivity().findViewById(R.id.rv_layout);
    }
}
