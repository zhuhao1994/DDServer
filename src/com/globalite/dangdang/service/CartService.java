package com.globalite.dangdang.service;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.bo.ShoppingCart;
import com.globalite.dangdang.entity.Book;

public interface CartService {
    void removeCart();

    ShoppingCart getCart();

    void addBook(Book var1);

    void removeBook(Book var1);

    void addBook(Book var1, String var2);

    void editBook(Book var1, String var2);
}