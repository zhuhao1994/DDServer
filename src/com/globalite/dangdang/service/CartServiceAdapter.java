package com.globalite.dangdang.service;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.bo.ShoppingCart;
import com.globalite.dangdang.entity.Book;
import com.globalite.dangdang.service.CacheCartService;
import com.globalite.dangdang.service.CartService;
import com.globalite.dangdang.service.SessionCartService;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;

public class CartServiceAdapter implements CartService {
    public static final String CART_TYPE_SESSION = "0";
    public static final String CART_TYPE_CACHE = "1";
    public static final String CONFIGURATION_PATH = "/cart.properties";
    public static final String CART_TYPE_KEY = "TYPE";
    private static String CART_TYPE = "0";
    private CartService service = null;

    static {
        Properties p = new Properties();

        try {
            p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("/cart.properties"));
            CART_TYPE = p.getProperty("TYPE");
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public CartServiceAdapter(HttpServletRequest request, String regName) {
        if(CART_TYPE == null) {
            throw new IllegalArgumentException("当前系统中设置了无效的购物车类型");
        } else {
            if(CART_TYPE.equals("1")) {
                this.service = new CacheCartService(regName);
            }

            if(CART_TYPE.equals("0")) {
                this.service = new SessionCartService(request.getSession());
            }

        }
    }

    public void removeCart() {
        this.service.removeCart();
    }

    public ShoppingCart getCart() {
        return this.service.getCart();
    }

    public void addBook(Book book) {
        this.service.addBook(book);
    }

    public void removeBook(Book book) {
        this.service.removeBook(book);
    }

    public void addBook(Book book, String quantity) {
        this.service.addBook(book, quantity);
    }

    public void editBook(Book book, String quantity) {
        this.service.editBook(book, quantity);
    }
}
