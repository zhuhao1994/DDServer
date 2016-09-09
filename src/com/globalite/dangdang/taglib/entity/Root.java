package com.globalite.dangdang.taglib.entity;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.taglib.entity.Item;
import java.util.List;

public class Root {
    private List<Item> items = null;
    private String id = null;
    private String name = null;

    public Root() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}