package com.liu.lxf.myapplication.presenter;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.liu.lxf.myapplication.bean.ContentListBean;
import com.liu.lxf.myapplication.utils.Count;
import com.liu.lxf.myapplication.view.IDemoView;
import com.liu.lxf.myapplication.view.MainActivity;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;


/**
 * Created by LiuXiangFei on 2016/12/21.
 */
public class AppDemoPresenter {
   private Activity context;

    private IDemoView iview;
    public AppDemoPresenter(MainActivity activity){
        this.context = activity;
        this.iview = activity;
    }

    /**
     * 解析所有数据
     */
   public ContentListBean jsonData(String s){
       ContentListBean bean = JSON.parseObject(s, ContentListBean.class);
       return bean;
   }

    //简单的Get请求，不带参数
    public void getAllData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Count.URL)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }
    //请求后的回调方法
    private Callback callback = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {
            setResult(e.getMessage(), false);
        }
        @Override
        public void onResponse(Response response) throws IOException {
            setResult(response.body().string(), true);
        }
    };
    //显示请求返回的结果
    private void setResult(final String msg, final boolean success) {
        ((MainActivity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (success) {
                    Toast.makeText((MainActivity) context, "请求成功", Toast.LENGTH_SHORT).show();
                    iview.showData(jsonData(msg));
                } else {
                    Toast.makeText((MainActivity) context, "请求失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
