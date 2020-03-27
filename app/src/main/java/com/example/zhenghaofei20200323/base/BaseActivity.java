package com.example.zhenghaofei20200323.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.zhenghaofei20200323.R;

import butterknife.ButterKnife;
/**
 * Zhenghaofei20200323
 * BaseActivity
 * 2020-03-23
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    private P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        presenter = initPresenter();
        ButterKnife.bind(this);
        initData();
    }
        public P getPresenter(){
        if (presenter!=null){
            return presenter;
             }
          return null;
        }
    protected abstract P initPresenter();

    protected abstract int getLayout();

    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.Destory();
            presenter=null;
        }
    }
}
