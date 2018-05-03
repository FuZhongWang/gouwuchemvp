package com.example.yuekaomoni.presenter;

import com.example.yuekaomoni.model.bean.ShoppingBean;
import com.example.yuekaomoni.model.callback.HttpUtilsCallBack;
import com.example.yuekaomoni.model.http.HttpUtils;
import com.google.gson.Gson;

public class MainPresenter extends BasePresenter {

    private final HttpUtils httpUtils;

    public MainPresenter() {
        httpUtils = HttpUtils.getInstance();
    }
    public void getData(String url){
        httpUtils.doGet(url, new HttpUtilsCallBack() {
            @Override
            public void onSuccess(String s) {
                getView().onSuccess(s);

            }

            @Override
            public void onError(String s) {

            }
        });

    }
}
