package com.globalite.dangdang.service;

/**
 * Created by zhu on 2016/9/5.
 */

import com.globalite.dangdang.entity.Customer;

public interface LoginService {
    Customer checkLogin(String var1, String var2);

    boolean checkNumber(String var1, String var2);
}
