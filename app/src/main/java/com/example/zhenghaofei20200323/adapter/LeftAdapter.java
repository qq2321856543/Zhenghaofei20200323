package com.example.zhenghaofei20200323.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhenghaofei20200323.R;
import com.example.zhenghaofei20200323.bean.TwoListView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class LeftAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<TwoListView.ResultBean> list;

    public LeftAdapter(Context context, List<TwoListView.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.left, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        TwoListView.ResultBean resultBean = list.get(i);
        final String id = resultBean.getId();
        ((ViewHolder)viewHolder).tv.setText(resultBean.getName());
        ((ViewHolder)viewHolder).tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
