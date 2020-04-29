package com.example.qingxiang.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qingxiang.Adapter.ChatAdapter;
import com.example.qingxiang.R;
import com.example.qingxiang.entity.Comunity;
import com.example.qingxiang.entity.User;
import com.example.qingxiang.util.ToastUtils;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
    }
}
