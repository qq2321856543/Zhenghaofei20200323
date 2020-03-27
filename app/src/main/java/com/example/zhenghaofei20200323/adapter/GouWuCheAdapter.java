package com.example.zhenghaofei20200323.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.zhenghaofei20200323.R;
import com.example.zhenghaofei20200323.bean.QueryListView;


import java.util.List;

public class GouWuCheAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<QueryListView.ResultBean> list;

    public GouWuCheAdapter(Context context, List<QueryListView.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_gouwucheadapter, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

//        private final RecyclerView rv;
//        private final TextView tv;
//        private final CheckBox cb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            rv = itemView.findViewById(R.id.rv);
//            tv = itemView.findViewById(R.id.tv);
//            cb = itemView.findViewById(R.id.cb);
        }
    }
}
