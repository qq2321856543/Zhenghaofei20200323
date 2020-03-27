package com.example.zhenghaofei20200323.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhenghaofei20200323.R;

public class View extends LinearLayout implements android.view.View.OnClickListener {

    private TextView tv_count;
    private setOnclick msetOnclick;

    public View(Context context) {
        super(context);
        init(context);
    }

    public View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public void setCount(int count){
        if (count<=0){
            return;
        }
        tv_count.setText(String.valueOf(count));
    }
    public void init(Context context){
        android.view.View view = View.inflate(context, R.layout.item_jiajian, null);
        view.findViewById(R.id.tv_jia).setOnClickListener(this);
        tv_count = view.findViewById(R.id.tv_count);
        view.findViewById(R.id.tv_jian).setOnClickListener(this);
        addView(view);
    }

    @Override
    public void onClick(android.view.View v) {
        int count=0;
        try {
            count=Integer.valueOf(tv_count.getText().toString());
        }catch (Exception e){

        }
        switch (v.getId()){
            case R.id.tv_jia:
                setCount(++count);
                msetOnclick.Click(count);
                break;
            case R.id.tv_jian:
                setCount(--count);
                msetOnclick.Click(count);
                break;
        }
    }
    public void Setonclick(setOnclick setOnclick){
        msetOnclick = setOnclick;
    }
    public interface setOnclick{
        void Click(int count);
    }
}
