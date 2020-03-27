package com.example.zhenghaofei20200323.presenter;

import com.example.zhenghaofei20200323.base.BasePresenter;
import com.example.zhenghaofei20200323.base.IBaseView;
import com.example.zhenghaofei20200323.bean.ListViewBean;
import com.example.zhenghaofei20200323.bean.QueryListView;
import com.example.zhenghaofei20200323.bean.TwoListView;
import com.example.zhenghaofei20200323.contrll.IContrll;
import com.example.zhenghaofei20200323.model.Model;

import java.util.Map;
/**
 * Zhenghaofei20200323
 * Presenter
 * 2020-03-23
 */
public class Presenter extends BasePresenter implements IContrll.IPresenter {

    private Model model;

    public Presenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new Model();
    }

    @Override
    public void getLogin(String url, Map<String, String> map) {
        model.getJson(url, map, new IContrll.Callback() {
            @Override
            public void getSuccess(String str) {
                IBaseView view = getView();
                if (view!=null&&view instanceof IContrll.IView){
                    ((IContrll.IView)view).getSuccess(str);
                }
            }

            @Override
            public void getErroy(String str) {
                IBaseView view = getView();
                if (view!=null&&view instanceof IContrll.IView){
                    ((IContrll.IView)view).getErroy(str);
                }
            }
        });
    }

    @Override
    public void getListView() {
        model.getListViewJson(new IContrll.ListViewCallback() {
            @Override
            public void getSuccess(ListViewBean listViewBean) {
                IBaseView view = getView();
                if (view!=null&&view instanceof IContrll.IView){
                    ((IContrll.IView)view).getListView(listViewBean);
                }
            }

            @Override
            public void getErroy(String str) {

            }
        });
    }

    @Override
    public void getQueryList() {
        model.getQueryList(new IContrll.QueryListViewCallback() {
            @Override
            public void getSuccess(QueryListView queryListView) {
                IBaseView view = getView();
                if (view!=null&&view instanceof IContrll.IView){
                    ((IContrll.IView)view).getQueryList(queryListView);
                }
            }

            @Override
            public void getErroy(String str) {

            }
        });
    }

    @Override
    public void getTwoList() {
        model.getTwoList(new IContrll.TwoListViewCallback() {
            @Override
            public void getSuccess(TwoListView twoListView) {
                IBaseView view = getView();
                if (view!=null&&view instanceof IContrll.IView){
                    ((IContrll.IView)view).getTwoList(twoListView);
                }
            }

            @Override
            public void getErroy(String str) {

            }
        });
    }
}
