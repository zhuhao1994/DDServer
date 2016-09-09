package com.globalite.dangdang.bo;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.bo.CartItem;
import com.globalite.dangdang.entity.Book;
import com.globalite.dangdang.entity.decorator.BookDecorator;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShoppingCart implements Serializable {
    private static final long serialVersionUID = 960645346261703394L;
    private List<CartItem> items = null;

    public Integer getQuantity() {
        return Integer.valueOf(this.items.size());
    }

    public ShoppingCart() {
        this.items = new ArrayList();
    }

    public BookDecorator findBookById(Long bookId) {
        return this.findItemById(bookId).getBook();
    }

    public CartItem findItemById(Long bookId) {
        Iterator var3 = this.items.iterator();

        while(var3.hasNext()) {
            CartItem item = (CartItem)var3.next();
            if(item.getBook().getOwn().getId().longValue() == bookId.longValue()) {
                return item;
            }
        }

        return null;
    }

    public void addBook(Book book) {
        this.addBook(book, (String)null);
    }

    public void addBook(Book book, String quantity) {
        CartItem item = this.findItemById(book.getId());
        if(item == null) {
            item = new CartItem(book);
            this.items.add(item);
            if(quantity != null) {
                item.setQuantity(Integer.valueOf(quantity));
            }
        } else if(quantity == null) {
            item.incrementQuantity();
        } else {
            item.setQuantity(Integer.valueOf(item.getQuantity().intValue() + Integer.valueOf(quantity).intValue()));
        }

    }

    public void removeBook(Long bookId) {
        CartItem item = this.findItemById(bookId);
        if(item != null) {
            this.items.remove(item);
        }
    }

    public void updateQuantity(Long bookId, String quantity) {
        CartItem item = this.findItemById(bookId);
        if(item != null) {
            item.setQuantity(Integer.valueOf(quantity));
        }
    }

    public List<CartItem> getItems() {
        return this.items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        double tp = 0.0D;

        CartItem bd;
        for(Iterator var4 = this.items.iterator(); var4.hasNext(); tp += bd.getSubtotal().doubleValue()) {
            bd = (CartItem)var4.next();
        }

        BigDecimal bd1 = new BigDecimal(tp);
        return bd1.setScale(2, 1);
    }
}
