package com.liu.lxf.myapplication.view;

import com.liu.lxf.myapplication.bean.ContentListBean;

/**
 * Created by LiuXiangFei on 2016/12/20.
 */
public interface IDemoView {


    /**
     * 进入页面显示数据
     */
    void showData(ContentListBean bean);

    /**
     * 下拉到最低，显示更多信息
     */
    void showMoreData();

    /**
     * 刷新信息
     */
    void refresh();
}
