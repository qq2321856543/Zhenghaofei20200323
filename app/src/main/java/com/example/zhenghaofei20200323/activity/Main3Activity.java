package com.example.zhenghaofei20200323.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zhenghaofei20200323.R;
import com.example.zhenghaofei20200323.base.BaseActivity;
import com.example.zhenghaofei20200323.base.BasePresenter;
import com.example.zhenghaofei20200323.bean.LoginBean;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.decoding.DecodeFormatManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Hashtable;
import java.util.Vector;

import butterknife.BindView;
/**
 * Zhenghaofei20200323
 * 二维码
 * 2020-03-23
 */
public class Main3Activity extends BaseActivity {
    //获取控件
    @BindView(R.id.iv)
    ImageView iv;
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main3;
    }

    @Override
    protected void initData() {
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getBean(LoginBean.ResultBean result){
        String headPic = result.getHeadPic();
        String nickName = result.getNickName();
        String phone = result.getPhone();

        //创建二维码
        Bitmap image = CodeUtils.createImage("" + nickName + phone, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.xiao9));
        iv.setImageBitmap(image);
        //解绑
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);


        //解析二维码
        Bitmap bitmap = ((BitmapDrawable) iv.getDrawable()).getBitmap();
        Hashtable<DecodeHintType, Object> hashtable = new Hashtable<>(2);
        Vector<BarcodeFormat> vector = new Vector<>();
        if (vector.isEmpty()){
            vector.addAll(DecodeFormatManager.QR_CODE_FORMATS);
        }
        Result decode =null;
        hashtable.put(DecodeHintType.POSSIBLE_FORMATS,vector);
        hashtable.put(DecodeHintType.CHARACTER_SET,"UTF-8");
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] ints=new int[width*height];
        bitmap.getPixels(ints,0,width,0,0,width,height);
        RGBLuminanceSource rgbLuminanceSource = new RGBLuminanceSource(width, height, ints);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new GlobalHistogramBinarizer(rgbLuminanceSource));
        QRCodeReader qrCodeReader = new QRCodeReader();
        try {
             decode = qrCodeReader.decode(binaryBitmap, hashtable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (decode!=null){
            Log.i("xxx",""+decode.getText());
        }
    }

}
