package com.globalite.dangdang.entity.decorator;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.entity.Orders;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class OrdersDecorator implements Serializable {
    private static final long serialVersionUID = -7681965387081618311L;
    private Orders own = null;

    public OrdersDecorator(Orders own) {
        this.own = own;
    }

    public Orders getOwn() {
        return this.own;
    }

    public void setOwn(Orders own) {
        this.own = own;
    }

    public String getPrice() {
        DecimalFormat f = new DecimalFormat("0.00");
        return f.format(this.getOwn().getPrice());
    }

    public String getPayString() {
        switch(this.getOwn().getPaytype().byteValue()) {
            case 0:
                return "货到付款";
            case 1:
                return "网银支付";
            case 2:
                return "银行汇款";
            default:
                return "";
        }
    }

    public String getCreateDate() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        return this.getOwn().getCreatedate() == null?null:formater.format(this.getOwn().getCreatedate());
    }

    public String getTypeString() {
        switch(this.getOwn().getOrdertype().byteValue()) {
            case 0:
                return "审核";
            case 1:
                return "配货中";
            case 2:
                return "客户取消";
            case 3:
                return "已发货";
            case 4:
                return "交易成功";
            default:
                return "";
        }
    }
}
