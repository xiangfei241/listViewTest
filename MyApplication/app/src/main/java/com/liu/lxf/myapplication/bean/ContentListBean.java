package com.liu.lxf.myapplication.bean;

import java.util.List;

/**
 * Created by LiuXiangFei on 2016/12/20.
 * 获取的结果
 */
public class ContentListBean {
    /*
     *一级标题
     */
    private String title;
    /*
     *内容列表
     */
    private List<ItemBean> rows;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ItemBean> getRows() {
        return rows;
    }

    public void setRows(List<ItemBean> rows) {
        this.rows = rows;
    }
}
