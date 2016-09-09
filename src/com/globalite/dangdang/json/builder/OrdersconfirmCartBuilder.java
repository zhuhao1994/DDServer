package com.globalite.dangdang.json.builder;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.bo.CartItem;
import com.globalite.dangdang.bo.ShoppingCart;
import com.globalite.dangdang.common.SpringManager;
import com.globalite.dangdang.dao.BookDao;
import com.globalite.dangdang.entity.Book;
import com.globalite.dangdang.entity.decorator.BookDecorator;
import com.globalite.dangdang.json.builder.Builder;
import com.oracle.dd.tool.json.request.entity.CartItemRequestParams;
import com.oracle.dd.tool.json.request.entity.OrderConfirmRequestParams;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrdersconfirmCartBuilder implements Builder {
    public static final String DEFAULT_MOBILE_DELIVER_PRICE = "5.0";
    public static final String DEFAULT_MOBILE_INVOICE_TYPE = "0";
    public static final String DEFAULT_MOBILE_PAY_TYPE = "0";
    public static final String DEFAULT_INVOICE_CONTENT = null;

    public OrdersconfirmCartBuilder() {
    }

    public Object build(Object entity, int optional) {
        BookDao dao = (BookDao)SpringManager.getManager().getBean("bookDao");
        OrderConfirmRequestParams requestParams = (OrderConfirmRequestParams)entity;
        List itemRequestParams = requestParams.getBooks();
        ShoppingCart cart = new ShoppingCart();
        ArrayList items = new ArrayList();
        Iterator var9 = itemRequestParams.iterator();

        while(var9.hasNext()) {
            CartItemRequestParams itemRequest = (CartItemRequestParams)var9.next();
            CartItem item = new CartItem();
            Book book = dao.findFullBook(Long.valueOf(itemRequest.getBookid()), true);
            item.setBook(new BookDecorator(book));
            item.setQuantity(Integer.valueOf(itemRequest.getQuantity()));
            items.add(item);
        }

        cart.setItems(items);
        return cart;
    }
}
