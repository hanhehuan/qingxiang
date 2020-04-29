package com.example.qingxiang.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.qingxiang.R;
import com.example.qingxiang.activity.Login;
import com.example.qingxiang.entity.User;
import com.example.qingxiang.util.ToastUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

/**
 * 我的
 */
public class FragmentMine extends Fragment {
    private TextView username;
    private TextView nickname;
    private Button exit;
    //初始化Fragment的布局
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine,container,false);
    }
    //执行该方法时，与Fragment绑定的Activity的onCreate方法已经执行完成并返回
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initData();

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BmobUser.logOut();
                startActivity(new Intent(getActivity(), Login.class));
                getActivity().finish();
            }
        });
    }

    private void initData() {
        BmobUser bs = BmobUser.getCurrentUser(BmobUser.class);
        String id = bs.getObjectId();
        BmobQuery<User> userBmobQuery = new BmobQuery<>();
        userBmobQuery.getObject(id, new QueryListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(e==null){
                    username.setText(user.getUsername());
                    nickname.setText(user.getNickname());
                }else {
                    ToastUtils.showShort(getActivity(),"获取失败");
                }
            }
        });
    }

    private void initView() {
        username = getActivity().findViewById(R.id.username);
        nickname = getActivity().findViewById(R.id.nickname);
        exit = getActivity().findViewById(R.id.btn_exit);
    }
}
