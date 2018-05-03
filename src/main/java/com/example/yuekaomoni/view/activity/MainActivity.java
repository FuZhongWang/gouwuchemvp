package com.example.yuekaomoni.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.yuekaomoni.R;
import com.example.yuekaomoni.model.bean.JsonDataBean;
import com.example.yuekaomoni.model.bean.ShoppingBean;
import com.example.yuekaomoni.presenter.MainPresenter;
import com.example.yuekaomoni.util.constant.Constants;
import com.example.yuekaomoni.view.adapter.ShoppingAdapter;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter> implements View.OnClickListener,ShoppingAdapter.CallBack {

    private ExpandableListView mExpl;
    /**
     * 全选/反选
     */
    private CheckBox mCkAll;
    private TextView mTotalPrice;
    /**
     * 去结算
     */
    private Button mConsume;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                List<ShoppingBean.DataBean> data= (List<ShoppingBean.DataBean>) msg.obj;


            }
        }
    };
    private ShoppingAdapter shoppingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mExpl = (ExpandableListView) findViewById(R.id.expl);
        mCkAll = (CheckBox) findViewById(R.id.ck_All);
        mTotalPrice = (TextView) findViewById(R.id.total_price);
        mConsume = (Button) findViewById(R.id.consume);
        mConsume.setOnClickListener(this);
    }

    @Override
    public void initData() {
        /*basePresenter.getData(Constants.SHOPPING_URL);*/
        try {
            InputStream open = getAssets().open("data.json");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len= 0;
            byte[] bytes = new byte[1024];
            while ((len=open.read(bytes))!=-1){

                bos.write(bytes,0,len);
            }
            String s = bos.toString();
            Gson gson = new Gson();
            JsonDataBean jsonDataBean = gson.fromJson(s, JsonDataBean.class);
            List<JsonDataBean.ContentBean> content = jsonDataBean.getContent();

            shoppingAdapter = new ShoppingAdapter(this, content);
            shoppingAdapter.setTextprice(mTotalPrice);
            shoppingAdapter.setCallBack(this);
            mExpl.setAdapter(shoppingAdapter);
            int groupCount = shoppingAdapter.getGroupCount();
            for (int i = 0; i <groupCount; i++) {
                mExpl.expandGroup(i);
            }
            shoppingAdapter.notifyDataSetChanged();
            mCkAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {








                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public MainPresenter getBasePresenter() {
        return new MainPresenter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.consume:
                break;
        }
    }

    @Override
    public void onSuccess(String s) {
       /* Gson gson = new Gson();
        ShoppingBean shoppingBean = gson.fromJson(s, ShoppingBean.class);
        List<ShoppingBean.DataBean> data = shoppingBean.getData();
        Message message = handler.obtainMessage();
        message.what=1;
        message.obj=data;
        handler.sendMessage(message);*/
    }

    @Override
    public void onError(String s) {

    }

    @Override
    public void selectAll(boolean b) {
        mCkAll.setChecked(b);
    }
}
