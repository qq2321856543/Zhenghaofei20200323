package com.example.zhenghaofei20200323.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.zhenghaofei20200323.R;
import com.example.zhenghaofei20200323.adapter.GouWuCheAdapter;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomePageMainActivity extends BaseActivity implements IContrll.IView {
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.tv_count)
    TextView tv_count;
    @BindView(R.id.tv_price)
    TextView tv_price;
    ArrayList<QueryListView.ResultBean> list = new ArrayList<>();
    private GouWuCheAdapter gouWuCheAdapter;


    @Override
    protected BasePresenter initPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_home_page_main;
    }

    @Override
    protected void initData() {
        BasePresenter presenter = getPresenter();
        if (presenter!=null&&presenter instanceof IContrll.IPresenter){
            ((IContrll.IPresenter)presenter).getQueryList();
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
        ListViewBean.ResultBean.RxxpBean rxxp = listViewBean.getResult().getRxxp();
        String name = rxxp.getName();
        Log.i("rrr","getListView"+name);
    }

    @Override
    public void getQueryList(QueryListView queryListView) {
        List<QueryListView.ResultBean> result = queryListView.getResult();
        list.clear();
        list.addAll(result);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        gouWuCheAdapter = new GouWuCheAdapter(this, list);
        rv.setAdapter(gouWuCheAdapter);
    }

    @Override
    public void getTwoList(TwoListView twoListView) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void isCheack(String i){
        Boolean boll=true;
        int count=0;
        double price=0;
        for(QueryListView.ResultBean list:list){
            List<QueryListView.ResultBean.ShoppingCartListBean> shoppingCartList = list.getShoppingCartList();

            for (QueryListView.ResultBean.ShoppingCartListBean cartList:shoppingCartList){
                if (cartList.getCheack()){
                    count+=cartList.getCount();
                    price+=cartList.getPrice()*cartList.getCount();
                }else {
                    boll=false;
                }
            }
            list.setAllnamecheack(boll);
        }
        gouWuCheAdapter.notifyDataSetChanged();
        tv_count.setText("数量:"+count+"");
        tv_price.setText("合计:￥"+String.format("%.2f",price));
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
