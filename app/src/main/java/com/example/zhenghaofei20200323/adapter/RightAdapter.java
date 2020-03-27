package com.example.zhenghaofei20200323.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhenghaofei20200323.R;
import com.example.zhenghaofei20200323.bean.TwoListView;

import java.util.List;

public class RightAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<TwoListView.ResultBean.SecondCategoryVoBean> list;

    public RightAdapter(Context context, List<TwoListView.ResultBean.SecondCategoryVoBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.right, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ViewHolder)viewHolder).name.setText(list.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }
    }
}
