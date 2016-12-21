package com.liu.lxf.myapplication.bean;

/**
 * Created by LiuXiangFei on 2016/12/20.
 * 每一个条目的bean类
 */
public class ItemBean {

    /**
     * 条目标题
     */
    private String title;
    /**
     * 描述信息
     */
    private String description;
    /**
     * 图片地址
     */
    private String imageHref;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }
}
