package com.example.zhenghaofei20200323.model;

import android.util.Log;

import com.example.zhenghaofei20200323.bean.ListViewBean;
import com.example.zhenghaofei20200323.bean.QueryListView;
import com.example.zhenghaofei20200323.bean.TwoListView;
import com.example.zhenghaofei20200323.contrll.IContrll;
import com.example.zhenghaofei20200323.utils.OkHttpRetrofit;
import com.example.zhenghaofei20200323.utils.OkHttpUtil;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Zhenghaofei20200323
 * Model
 * 2020-03-23
 */
public class Model implements IContrll.IModel {

    @Override
    public void getJson(String url, Map<String, String> map, final IContrll.Callback callback) {
        OkHttpUtil.getInstance().doPost(url, new HashMap<String, String>(), map, new OkHttpUtil.Callback() {
            @Override
            public void getSuccess(String str) {
                callback.getSuccess(str);
            }

            @Override
            public void getErroy(String str) {
                callback.getErroy(str);
            }
        });
    }

    @Override
    public void getListViewJson(final IContrll.ListViewCallback listViewCallback) {
        OkHttpRetrofit.getInstance().getApis().getList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ListViewBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("rrr","onSubscribe");

                    }

                    @Override
                    public void onNext(ListViewBean listViewBean) {
                        Log.i("rrr","next");
                        listViewCallback.getSuccess(listViewBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("rrr","onError"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i("rrr","onComplete");
                    }
                });
    }

    @Override
    public void getQueryList(final IContrll.QueryListViewCallback queryListViewCallback) {
            OkHttpRetrofit.getInstance().getApis().getQueryList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<QueryListView>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(QueryListView queryListView) {
                            queryListViewCallback.getSuccess(queryListView);
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
    }

    @Override
    public void getTwoList(final IContrll.TwoListViewCallback twoListViewCallback) {
        OkHttpRetrofit.getInstance().getApis().getTwoList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TwoListView>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TwoListView twoListView) {
                        twoListViewCallback.getSuccess(twoListView);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
