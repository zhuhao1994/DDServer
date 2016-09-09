package com.globalite.dangdang.mvc.form;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.bo.CartItem;
import java.util.List;

public class ShoppingCartForm {
    private String id = null;
    private String op = null;
    private String number = null;
    private List<CartItem> items = null;

    public ShoppingCartForm() {
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOp() {
        return this.op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public List<CartItem> getItems() {
        return this.items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}

