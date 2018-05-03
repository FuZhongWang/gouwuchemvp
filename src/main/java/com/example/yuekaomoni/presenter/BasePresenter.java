package com.example.yuekaomoni.presenter;

import com.example.yuekaomoni.view.interfaces.IBaseView;

public class BasePresenter<T extends IBaseView> {
    private T iBaseView ;
    public void attachView(T iBaseView){
        this.iBaseView=iBaseView;

    }
    public T getView(){
        return iBaseView;
    }
}
