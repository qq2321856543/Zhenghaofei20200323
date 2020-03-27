package com.example.zhenghaofei20200323.base;

import com.example.zhenghaofei20200323.base.IBaseView;

import java.lang.ref.WeakReference;
/**
 * Zhenghaofei20200323
 * BasePresenter
 * 2020-03-23
 */
public abstract class BasePresenter<V extends IBaseView> {

    private WeakReference<V> vWeakReference;

    public BasePresenter(V v) {
        vWeakReference = new WeakReference<>(v);
        initModel();
    }
    public V getView(){
        if (vWeakReference!=null){
            return vWeakReference.get();
        }
        return null;
    }

    protected abstract void initModel();
    public void Destory(){
        if (vWeakReference!=null){
            vWeakReference.clear();
            vWeakReference=null;
        }
    }
}
