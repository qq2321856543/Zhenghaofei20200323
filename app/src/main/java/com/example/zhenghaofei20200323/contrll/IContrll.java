package com.example.zhenghaofei20200323.contrll;

import com.example.zhenghaofei20200323.base.IBaseView;
import com.example.zhenghaofei20200323.bean.ListViewBean;
import com.example.zhenghaofei20200323.bean.QueryListView;
import com.example.zhenghaofei20200323.bean.TwoListView;

import java.util.Map;
/**
 * Zhenghaofei20200323
 * 契约类
 * 2020-03-23
 */
public interface IContrll {
    interface IView extends IBaseView{
        void getSuccess(String str);
        void getErroy(String str);
        void getListView(ListViewBean listViewBean);
        void getQueryList(QueryListView queryListView);
        void getTwoList(TwoListView twoListView);
    }
    interface IPresenter{
        void getLogin(String url, Map<String,String> map);
        void getListView();
        void getQueryList();
        void getTwoList();

    }
    interface IModel{
        void getJson(String url,Map<String,String> map,Callback callback);
        void getListViewJson(ListViewCallback listViewCallback);
        void getQueryList(QueryListViewCallback queryListViewCallback);
        void getTwoList(TwoListViewCallback twoListViewCallback);

    }
    interface Callback{
        void getSuccess(String str);
        void getErroy(String str);
    }
    interface ListViewCallback{
        void getSuccess(ListViewBean listViewBean);
        void getErroy(String str);
    }
    interface QueryListViewCallback{
        void getSuccess(QueryListView queryListView);
        void getErroy(String str);
    }
    interface TwoListViewCallback{
        void getSuccess(TwoListView twoListView);
        void getErroy(String str);
    }
}
