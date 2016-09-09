package com.globalite.dangdang.bo;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.entity.Book;
import com.globalite.dangdang.entity.decorator.BookDecorator;
import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable {
    private static final long serialVersionUID = 67010091974445335L;
    private BookDecorator book = null;
    private Integer quantity = Integer.valueOf(1);

    public CartItem() {
    }

    public CartItem(Book book) {
        this.book = new BookDecorator(book);
    }

    public void incrementQuantity() {
        this.quantity = Integer.valueOf(this.quantity.intValue() + 1);
    }

    public BigDecimal getSubtotal() {
        if(this.book == null) {
            return BigDecimal.valueOf(0.0D);
        } else {
            double st = this.book.getOwn().getPrice().doubleValue() * (double)this.quantity.intValue();
            BigDecimal bd = new BigDecimal(st);
            return bd.setScale(2, 1);
        }
    }

    public BookDecorator getBook() {
        return this.book;
    }

    public void setBook(BookDecorator book) {
        this.book = book;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
