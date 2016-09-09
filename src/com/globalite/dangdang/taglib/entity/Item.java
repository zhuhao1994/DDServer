package com.globalite.dangdang.taglib.entity;

/**
 * Created by zhu on 2016/9/5.
 */
public class Item {
    private String id = null;
    private String url = null;
    private String content = null;

    public Item() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
