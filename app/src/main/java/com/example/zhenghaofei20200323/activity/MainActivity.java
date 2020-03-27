package com.example.zhenghaofei20200323.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zhenghaofei20200323.R;
import com.example.zhenghaofei20200323.base.BaseActivity;
import com.example.zhenghaofei20200323.base.BasePresenter;
import com.example.zhenghaofei20200323.bean.Bean;
import com.example.zhenghaofei20200323.bean.ListViewBean;
import com.example.zhenghaofei20200323.bean.QueryListView;
import com.example.zhenghaofei20200323.bean.RegisterBean;
import com.example.zhenghaofei20200323.bean.TwoListView;
import com.example.zhenghaofei20200323.contrll.IContrll;
import com.example.zhenghaofei20200323.presenter.Presenter;
import com.example.zhenghaofei20200323.utils.OkHttpUtil;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
/**
 * Zhenghaofei20200323
 * 注册
 * 2020-03-23
 */
public class MainActivity extends BaseActivity implements IContrll.IView {
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
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void getSuccess(String str) {
        Gson gson = new Gson();
        RegisterBean registerBean = gson.fromJson(str, RegisterBean.class);
        String message = registerBean.getMessage();
        if (message.equals("注册成功")){
            Bean bean = new Bean();
            bean.setName(name.getText().toString());
            bean.setPsw(psw.getText().toString());
            //粘性事件
            EventBus.getDefault().postSticky(bean);
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            //跳转
            startActivity(intent);
            //销毁
            finish();
        }
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

    @OnClick(R.id.ok)
    public void okButton(View view){
        //各种判断
        if (OkHttpUtil.getInstance().isWifi(MainActivity.this)){
            if (!TextUtils.isEmpty(name.getText().toString())&&!TextUtils.isEmpty(psw.getText().toString())){
                if (isPhone(psw.getText().toString())){
                    HashMap<String, String> map = new HashMap<>();
                    map.put("phone",name.getText().toString());
                    map.put("pwd",psw.getText().toString());
                    String url="http://mobile.bwstudent.com/small/user/v1/register";
                    BasePresenter presenter = getPresenter();
                    if (presenter!=null&&presenter instanceof IContrll.IPresenter){
                        ((IContrll.IPresenter)presenter).getLogin(url,map);
                    }
                }else {
                    Toast.makeText(this, "密码格式不对", Toast.LENGTH_SHORT).show();
                }

            }else {
                Toast.makeText(this, "帐号或密码不能为空", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "没有网络", Toast.LENGTH_SHORT).show();
        }
    }

    //正则判断
    public Boolean isPhone(String str){
        boolean matches = Pattern.compile("^1//10").matcher(str).matches();
        return true;
    }

}
