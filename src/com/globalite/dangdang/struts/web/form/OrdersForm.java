package com.globalite.dangdang.struts.web.form;

/**
 * Created by zhu on 2016/9/5.
 */

import com.globalite.dangdang.entity.decorator.OrdersDecorator;
import java.util.List;
import org.apache.struts.action.ActionForm;

public class OrdersForm extends ActionForm {
    private static final long serialVersionUID = 4825144233949636913L;
    private String ordid = null;
    private String priceFrom = null;
    private String priceTo = null;
    private String dateFrom = null;
    private String dateTo = null;
    private String op = null;
    private String msg = null;
    private Long cusid = null;
    private String orderstype = null;
    private String id = null;
    private List<OrdersDecorator> orders = null;

    public OrdersForm() {
    }

    public Long getCusid() {
        return this.cusid;
    }

    public void setCusid(Long cusid) {
        this.cusid = cusid;
    }

    public String getOrderstype() {
        return this.orderstype;
    }

    public void setOrderstype(String orderstype) {
        this.orderstype = orderstype;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrdid() {
        return this.ordid;
    }

    public void setOrdid(String ordid) {
        this.ordid = ordid;
    }

    public String getPriceFrom() {
        return this.priceFrom;
    }

    public void setPriceFrom(String priceFrom) {
        this.priceFrom = priceFrom;
    }

    public String getPriceTo() {
        return this.priceTo;
    }

    public void setPriceTo(String priceTo) {
        this.priceTo = priceTo;
    }

    public String getDateFrom() {
        return this.dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return this.dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getOp() {
        return this.op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<OrdersDecorator> getOrders() {
        return this.orders;
    }

    public void setOrders(List<OrdersDecorator> orders) {
        this.orders = orders;
    }
}
