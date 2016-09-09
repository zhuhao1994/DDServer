package com.globalite.dangdang.service;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.bo.ShoppingCart;
import com.globalite.dangdang.entity.Customer;
import com.globalite.dangdang.struts.web.form.CartConfirmForm;

public interface CartConfirmService {
    void doConfirmOrder(ShoppingCart var1, CartConfirmForm var2, Customer var3);
}
