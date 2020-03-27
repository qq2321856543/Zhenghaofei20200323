package com.example.zhenghaofei20200323.base;

import com.example.zhenghaofei20200323.bean.ListViewBean;
import com.example.zhenghaofei20200323.bean.QueryListView;
import com.example.zhenghaofei20200323.bean.TwoListView;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Apis {
    @GET("commodity/v1/commodityList")
    Observable<ListViewBean> getList();

    @GET("order/verify/v1/findShoppingCart")
    Observable<QueryListView> getQueryList();

    @GET("commodity/v1/findCategory")
    Observable<TwoListView> getTwoList();

}
