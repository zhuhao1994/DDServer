package com.globalite.dangdang.service;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.bo.ShoppingCart;
import com.globalite.dangdang.entity.Book;
import com.globalite.dangdang.service.CartService;
import javax.servlet.http.HttpSession;

public class SessionCartService implements CartService {
    private HttpSession session = null;

    public SessionCartService(HttpSession session) {
        this.session = session;
    }

    public ShoppingCart getCart() {
        ShoppingCart sc = (ShoppingCart)this.session.getAttribute("com.globalite.dangdang.constants.shoppingcart");
        if(sc != null) {
            return sc;
        } else {
            sc = new ShoppingCart();
            this.session.setAttribute("com.globalite.dangdang.constants.shoppingcart", sc);
            return sc;
        }
    }

    public void addBook(Book book) {
        this.addBook(book, "1");
    }

    public void removeBook(Book book) {
        ShoppingCart sc = this.getCart();
        sc.removeBook(book.getId());
    }

    public void addBook(Book book, String quantity) {
        ShoppingCart cart = this.getCart();
        cart.addBook(book, quantity);
    }

    public void editBook(Book book, String quantity) {
        ShoppingCart cart = this.getCart();
        cart.updateQuantity(book.getId(), quantity);
    }

    public void removeCart() {
        this.session.removeAttribute("com.globalite.dangdang.constants.shoppingcart");
    }
}
