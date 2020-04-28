package com.example.qingxiang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qingxiang.R;
import com.example.qingxiang.activity.Login;
import com.example.qingxiang.activity.Recive;
import com.example.qingxiang.entity.Post;
import com.example.qingxiang.util.ToastUtils;

import java.util.List;

import cn.bmob.v3.BmobUser;

/*
* 首页数据适配器
*/
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Post> datas;

    private final int N_TYPE = 0;//非footview的type
    private final int F_TYPE = 1;//footview的type

    private int MAX_NUM = 15; //预加载的数据，一共15条

    private Boolean isfootview = true;//是否存在footview


    public HomeAdapter(Context context, List<Post> datas){
        this.context = context;
        this.datas = datas;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ord_item,parent,false);
        View footview = LayoutInflater.from(parent.getContext()).inflate(R.layout.foot_item,parent,false);
        if(viewType == F_TYPE){
            return new RecyclerViewHolder(footview,F_TYPE);
        }else {
            return new RecyclerViewHolder(view,N_TYPE);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(isfootview && (getItemViewType(position))==F_TYPE){
            final  RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) holder;
            recyclerViewHolder.loading.setText("加载中...");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MAX_NUM += 8;
                    notifyDataSetChanged();
                }
            },2000);
        }else {
            final  RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) holder;
            Post post = datas.get(position);
            recyclerViewHolder.username.setText(post.getName());
            recyclerViewHolder.nickname.setText(post.getNickname());
            recyclerViewHolder.content.setText(post.getContent());
            recyclerViewHolder.time.setText(post.getCreatedAt());
            ToastUtils.showShort(context,post.getObjectId());
            recyclerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = recyclerViewHolder.getAdapterPosition();
                    if(BmobUser.getCurrentUser(BmobUser.class) != null){
                        Intent intent = new Intent(context, Recive.class);
                        intent.putExtra("id",datas.get(i).getObjectId());
                        context.startActivity(intent);
                    }else {
                        ToastUtils.showShort(context,"请登录");
                        context.startActivity(new Intent(context, Login.class));
                    }
                }
            });
        }



    }


    @Override
    public int getItemViewType(int position) {
        if(position == MAX_NUM-1){
            return F_TYPE;
        }else {
            return N_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        if(datas.size() < MAX_NUM){
            return datas.size();
        }
        return MAX_NUM;
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView username,nickname,content,time;
        public TextView loading;


        public RecyclerViewHolder(View itemview, int type) {
            super(itemview);

            if(type == N_TYPE){
                username = itemview.findViewById(R.id.username);
                nickname = itemview.findViewById(R.id.nickname);
                content = itemview.findViewById(R.id.content);
                time = itemview.findViewById(R.id.time);
            }else if(type == F_TYPE){
                loading = itemview.findViewById(R.id.footText);
            }


        }
    }
}
