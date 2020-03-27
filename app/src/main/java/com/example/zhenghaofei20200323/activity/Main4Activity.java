package com.example.zhenghaofei20200323.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.zhenghaofei20200323.R;
import com.example.zhenghaofei20200323.adapter.LeftAdapter;
import com.example.zhenghaofei20200323.adapter.RightAdapter;
import com.example.zhenghaofei20200323.base.BaseActivity;
import com.example.zhenghaofei20200323.base.BasePresenter;
import com.example.zhenghaofei20200323.bean.ListViewBean;
import com.example.zhenghaofei20200323.bean.QueryListView;
import com.example.zhenghaofei20200323.bean.TwoListView;
import com.example.zhenghaofei20200323.contrll.IContrll;
import com.example.zhenghaofei20200323.presenter.Presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

public class Main4Activity extends BaseActivity implements IContrll.IView {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.rvv)
    RecyclerView rvv;
    private List<TwoListView.ResultBean> result;

    @Override
    protected BasePresenter initPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main4;
    }

    @Override
    protected void initData() {
        BasePresenter presenter = getPresenter();
        if (presenter!=null && presenter instanceof IContrll.IPresenter){
            ((IContrll.IPresenter)presenter).getTwoList();
        }
    }

    @Override
    public void getSuccess(String str) {

    }

    @Override
    public void getErroy(String str) {

    }

    @Override
    public void getListView(ListViewBean listViewBean) {

    }

    @Override
    public void getQueryList(QueryListView queryListView) {

    }

    @Override
    public void getTwoList(TwoListView twoListView) {
        result = twoListView.getResult();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rv.setLayoutManager(layoutManager);
        LeftAdapter leftAdapter = new LeftAdapter(this, result);
        rv.setAdapter(leftAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getClick(String id){
        for (TwoListView.ResultBean list:result){
            if (list.getId().equals(id)){
                List<TwoListView.ResultBean.SecondCategoryVoBean> secondCategoryVo = list.getSecondCategoryVo();
                RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,3);
                rvv.setLayoutManager(layoutManager);
                RightAdapter rightAdapter = new RightAdapter(this, secondCategoryVo);
                rvv.setAdapter(rightAdapter);

            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }
}
