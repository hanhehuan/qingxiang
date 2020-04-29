package com.example.qingxiang.Adapter;
/*
 * 项目名： qingxiang
 * 包名： com.example.qingxiang.Adapter
 * 文件名： ChatAdapter
 * 创建者：hanhehuann
 * 创建时间：2020-04-29 12:18
 * 描述：TODO
 */

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qingxiang.R;
import com.example.qingxiang.activity.Recive;
import com.example.qingxiang.entity.Comunity;
import com.example.qingxiang.entity.Post;
import com.example.qingxiang.util.ToastUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Comunity> datas;

    private final int N_TYPE = 0;//非footview的type
    private final int F_TYPE = 1;//footview的type
    private int MAX_NUM = 15; //预加载的数据，一共15条
    private Boolean isfootview = true;//是否存在footview
    public ChatAdapter(Context context,List<Comunity> datas){
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comunity_item,parent,false);
        View footview = LayoutInflater.from(parent.getContext()).inflate(R.layout.foot_item,parent,false);
        if(viewType == F_TYPE){
            return new ChatAdapter.RecyclerViewHolder(footview,F_TYPE);
        }else {
            return new ChatAdapter.RecyclerViewHolder(view,N_TYPE);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(isfootview && (getItemViewType(position))==F_TYPE){
            final ChatAdapter.RecyclerViewHolder recyclerViewHolder = (ChatAdapter.RecyclerViewHolder) holder;
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
            final ChatAdapter.RecyclerViewHolder recyclerViewHolder = (ChatAdapter.RecyclerViewHolder) holder;
            Comunity comunity = datas.get(position);
            recyclerViewHolder.c_name.setText(comunity.getName());
            recyclerViewHolder.c_info.setText(comunity.getInfo());
            recyclerViewHolder.c_user.setText(comunity.getUser().getNickname());
            ToastUtils.showShort(context,"nickname="+comunity.getUser().getNickname());

            recyclerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = recyclerViewHolder.getAdapterPosition();
                    Intent intent = new Intent(context, Recive.class);
                    intent.putExtra("id",datas.get(i).getObjectId());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView c_name,c_info,c_user;
        public TextView loading;


        public RecyclerViewHolder(View itemview, int type) {
            super(itemview);

            if(type == N_TYPE){
                c_name = itemview.findViewById(R.id.c_name);
                c_info = itemview.findViewById(R.id.c_info);
                c_user = itemview.findViewById(R.id.c_user);
            }else if(type == F_TYPE){
                loading = itemview.findViewById(R.id.footText);
            }


        }
    }
}
