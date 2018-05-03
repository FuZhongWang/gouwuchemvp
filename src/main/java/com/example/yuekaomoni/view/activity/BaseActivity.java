package com.example.yuekaomoni.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.yuekaomoni.presenter.BasePresenter;
import com.example.yuekaomoni.view.interfaces.IBaseView;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseView{

    public T basePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        basePresenter = getBasePresenter();
        initView();
        initData();

    }
    public abstract int getContentView();
    public abstract void initView();
    public abstract void initData();
    public abstract T getBasePresenter();
}
