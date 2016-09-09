package com.globalite.dangdang.entity.decorator;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.entity.Book;
import java.io.Serializable;
import java.text.DecimalFormat;

public class BookDecorator implements Serializable {
    private static final long serialVersionUID = 6752167366135471021L;
    private Book own = null;

    public BookDecorator(Book book) {
        this.own = book;
    }

    public Book getOwn() {
        return this.own;
    }

    public void setOwn(Book own) {
        this.own = own;
    }

    public String getFullImage() {
        return this.own.getImage().getPath() + "/" + this.own.getImage().getName();
    }

    public String getPrice() {
        DecimalFormat f = new DecimalFormat("0.00");
        return f.format(this.getOwn().getPrice());
    }

    public String getVipprice() {
        DecimalFormat f = new DecimalFormat("0.00");
        return f.format(this.getOwn().getVipprice());
    }

    public String getCommonprice() {
        DecimalFormat f = new DecimalFormat("0.00");
        return f.format(this.getOwn().getCommonprice());
    }

    public String getDiscount() {
        DecimalFormat f = new DecimalFormat("0.0");
        return f.format((double)(this.getOwn().getPrice().floatValue() / this.getOwn().getCommonprice().floatValue() * 10.0F));
    }

    public String getIntroduction() {
        return this.getOwn().getIntroduction() != null && !"".equals(this.getOwn().getIntroduction())?(this.getOwn().getIntroduction().length() <= 150?this.getOwn().getIntroduction():this.getOwn().getIntroduction().substring(0, 150) + "......"):"";
    }

    public String getStock() {
        return this.getOwn().getStockstatus() == null?"无货":(this.getOwn().getStockstatus().byteValue() == 0?"有货":(this.getOwn().getStockstatus().byteValue() == 1?"可配货":"无货"));
    }
}

