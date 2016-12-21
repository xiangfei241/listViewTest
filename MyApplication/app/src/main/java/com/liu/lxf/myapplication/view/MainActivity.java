package com.liu.lxf.myapplication.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;

import com.liu.lxf.myapplication.R;
import com.liu.lxf.myapplication.bean.ContentListBean;
import com.liu.lxf.myapplication.bean.ItemBean;
import com.liu.lxf.myapplication.presenter.AppDemoPresenter;
import java.util.List;

public class MainActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener,
        RefreshLayout.OnLoadListener,IDemoView {

    private RefreshLayout swipeLayout;
    private ListView listView;
    private MyAdapter adapter;
    private View header;
    private AppDemoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
        presenter = new AppDemoPresenter(this);
        getActionBar().setTitle("Loading...");
        presenter.getAllData();
    }
    private void initView() {
        header = getLayoutInflater().inflate(R.layout.header, null);
        swipeLayout = (RefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    private void setListener() {
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setOnLoadListener(this);
    }

    @Override
    public void onRefresh() {
        swipeLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 更新数据
                // 更新完后调用该方法结束刷新
                swipeLayout.setRefreshing(false);
                refresh();
            }
        }, 1000);

    }

    @Override
    public void onLoad() {
        swipeLayout.postDelayed(new Runnable() {

            @Override
            public void run() {
                // 更新数据
                // 更新完后调用该方法结束刷新
                swipeLayout.setLoading(false);
            }
        }, 1000);
    }

    @Override
    public void showData(ContentListBean bean) {
        getActionBar().setTitle(bean.getTitle());
        List<ItemBean> listBean = bean.getRows();
        listView = (ListView) findViewById(R.id.list);
        listView.addHeaderView(header);
        adapter = new MyAdapter(this, listBean);
        listView.setAdapter(adapter);
    }

    @Override
    public void showMoreData() {

        //showMoreData
    }

    @Override
    public void refresh() {
        presenter.getAllData();
    }
}
