package com.globalite.dangdang.json.builder;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.bo.CartItem;
import com.globalite.dangdang.bo.ShoppingCart;
import com.globalite.dangdang.json.builder.Builder;
import com.oracle.dd.tool.json.response.entity.CartItemResponseParams;
import com.oracle.dd.tool.json.response.entity.CartResponseParams;
import java.util.ArrayList;
import java.util.Iterator;

public class CartParamsBuilder implements Builder {
    public static final int CART_INFO = 0;
    public static final int CART_UPDATE = 1;

    public CartParamsBuilder() {
    }

    public Object build(Object entity, int optional) {
        CartResponseParams param = new CartResponseParams();
        if(1 == optional) {
            param.setIsOk(((Boolean)entity).booleanValue());
            param.setOp("edit");
            return param;
        } else {
            ShoppingCart cart = (ShoppingCart)entity;
            ArrayList itemParams = new ArrayList();
            Iterator var7 = cart.getItems().iterator();

            while(var7.hasNext()) {
                CartItem item = (CartItem)var7.next();
                CartItemResponseParams itemParam = new CartItemResponseParams();
                itemParam.setBookId(String.valueOf(item.getBook().getOwn().getId()));
                itemParam.setBookName(item.getBook().getOwn().getName());
                itemParam.setBookPrice(item.getBook().getPrice());
                itemParam.setImageName(item.getBook().getOwn().getImage().getName());
                itemParam.setQuantity(String.valueOf(item.getQuantity()));
                itemParams.add(itemParam);
            }

            param.setItems(itemParams);
            return param;
        }
    }
}

