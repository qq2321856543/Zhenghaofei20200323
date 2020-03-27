package com.example.zhenghaofei20200323.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zhenghaofei20200323.R;
import com.example.zhenghaofei20200323.base.BaseActivity;
import com.example.zhenghaofei20200323.base.BasePresenter;
import com.example.zhenghaofei20200323.bean.Bean;
import com.example.zhenghaofei20200323.bean.ListViewBean;
import com.example.zhenghaofei20200323.bean.LoginBean;
import com.example.zhenghaofei20200323.bean.QueryListView;
import com.example.zhenghaofei20200323.bean.TwoListView;
import com.example.zhenghaofei20200323.contrll.IContrll;
import com.example.zhenghaofei20200323.presenter.Presenter;
import com.example.zhenghaofei20200323.utils.OkHttpUtil;
import com.example.zhenghaofei20200323.utils.SPUtil;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Zhenghaofei20200323
 * 登录
 * 2020-03-23
 */
public class Main2Activity extends BaseActivity implements IContrll.IView {
    //获取控件
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.psw)
    EditText psw;
    @BindView(R.id.ok)
    Button ok;


    @Override
    protected BasePresenter initPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initData() {
        //判断是否注册并注册
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void getSuccess(String str) {
        Log.i("xxx",""+str);
        Gson gson = new Gson();
        LoginBean loginBean = gson.fromJson(str, LoginBean.class);
        LoginBean.ResultBean result = loginBean.getResult();
        String sessionId = result.getSessionId();
        int userId = result.getUserId();
        SPUtil.putString(this,"userId",""+userId);
        SPUtil.putString(this,"sessionId",sessionId);
        //粘性事件
        EventBus.getDefault().postSticky(result);
        Intent intent = new Intent(Main2Activity.this, HomePageMainActivity.class);
        //跳转
        startActivity(intent);
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

    }

    //粘性事件接受
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getBean(Bean bean){
        name.setText(bean.getName());
        psw.setText(bean.getPsw());
        //解绑
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }
    //点击
    @OnClick(R.id.ok)
    public void buttonOk(View view){
        if (OkHttpUtil.getInstance().isWifi(Main2Activity.this)){
            if (!TextUtils.isEmpty(name.getText().toString())&&!TextUtils.isEmpty(psw.getText().toString())){
                HashMap<String, String> map = new HashMap<>();
                map.put("phone",name.getText().toString());
                map.put("pwd",psw.getText().toString());
                String url="http://mobile.bwstudent.com/small/user/v1/login";
                BasePresenter presenter = getPresenter();
                if (presenter!=null&&presenter instanceof IContrll.IPresenter){
                    ((IContrll.IPresenter)presenter).getLogin(url,map);
                }
            }else {
                Toast.makeText(this, "帐号或密码不能为空", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "没有网络", Toast.LENGTH_SHORT).show();
        }
    }
}
