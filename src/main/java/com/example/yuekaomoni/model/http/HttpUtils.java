package com.example.yuekaomoni.model.http;

import com.example.yuekaomoni.model.callback.HttpUtilsCallBack;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtils implements Callback {
    private static  HttpUtilsCallBack httpUtilsCallBack;
    private  static  HttpUtils httpUtils;
    private final OkHttpClient okHttpClient;
    private final Request.Builder builder;

    private HttpUtils() {
        okHttpClient = new OkHttpClient.Builder().build();
        builder = new Request.Builder();

    }
    public static HttpUtils getInstance(){
      if (httpUtils==null){

          httpUtils=new HttpUtils();
      }
      return httpUtils;

    }
    public void doGet(String url, HttpUtilsCallBack httpUtilsCallBack){
        this.httpUtilsCallBack=httpUtilsCallBack;
        Request request = builder.get().url(url).build();
        okHttpClient.newCall(request).enqueue(this);

    }
    public void doPost(String url, HttpUtilsCallBack httpUtilsCallBack, HashMap<String,String>hashMap){
        this.httpUtilsCallBack=httpUtilsCallBack;
        FormBody.Builder fbuilder = new FormBody.Builder();
        Iterator<String> iterator = hashMap.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            String value = hashMap.get(key);
            fbuilder.add(key,value);

        }
        FormBody build = fbuilder.build();
        Request request = this.builder.post(build).url(url).build();
        okHttpClient.newCall(request).enqueue(this);
    }


    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        String string = response.body().string();
        httpUtilsCallBack.onSuccess(string);
    }
}
