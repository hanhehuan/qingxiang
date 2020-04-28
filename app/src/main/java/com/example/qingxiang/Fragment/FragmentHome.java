package com.example.qingxiang.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.qingxiang.Adapter.HomeAdapter;
import com.example.qingxiang.R;
import com.example.qingxiang.entity.Post;
import com.example.qingxiang.entity.User;
import com.example.qingxiang.util.ToastUtils;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

/**
 * 首页
 */
public class FragmentHome extends Fragment {

    private RecyclerView rv;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView HelloHome;
    private List<Post> datas;
    private TextView username,ok;

    private HomeAdapter homeAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //逻辑处理
        initView();
        initData();
    }

    private void initData() {
        Refresh();//初始刷新
        swipeRefreshLayout.setColorSchemeResources(R.color.holo_green_light,R.color.holo_red_light,R.color.holo_blue_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Refresh();//刷新
            }
        });

        BmobUser bu = BmobUser.getCurrentUser(User.class);
        String userid = bu.getObjectId();
        BmobQuery<User> userBmobQuery = new BmobQuery<>();
        userBmobQuery.getObject(userid, new QueryListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(e==null){
                    username.setText(user.getNickname());
                }else {
                    //ToastUtils.showShort(getActivity(),"查询用户失败");
                }
            }
        });

    }
    //刷新
    private void Refresh() {

        BmobQuery<Post> Po = new BmobQuery<Post>();
        Po.order("-createdAt");
        Po.setLimit(1000);
        Po.findObjects(new FindListener<Post>() {
            @Override
            public void done(List<Post> list, BmobException e) {
                swipeRefreshLayout.setRefreshing(false);

                if(e == null){
                    datas = list;
                    homeAdapter = new HomeAdapter(getContext(),datas);
                    rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv.setAdapter(homeAdapter);


                }else {
                    //ToastUtils.showLong(getActivity(),e.getMessage());
                    ToastUtils.showShort(getActivity(),"获取数据失败");
                }
            }
        });

    }

    private void initView() {

        rv = getActivity().findViewById(R.id.recyclerview);
        swipeRefreshLayout = getActivity().findViewById(R.id.swipe);
        HelloHome = getActivity().findViewById(R.id.HelloHome);
        username = getActivity().findViewById(R.id.user);
        ok = getActivity().findViewById(R.id.hy);


    }
}
