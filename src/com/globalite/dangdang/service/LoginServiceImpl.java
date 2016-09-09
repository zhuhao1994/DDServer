package com.globalite.dangdang.service;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.common.SpringManager;
import com.globalite.dangdang.common.Utils;
import com.globalite.dangdang.dao.CustomerDao;
import com.globalite.dangdang.entity.Customer;
import com.globalite.dangdang.service.LoginService;

public class LoginServiceImpl implements LoginService {
    public LoginServiceImpl() {
    }

    public Customer checkLogin(String uid, String pwd) {
        if(uid != null && pwd != null) {
            CustomerDao dao = (CustomerDao)SpringManager.getManager().getBean("customerDao");
            Customer cus = dao.findCustomer(uid, Utils.toMD5(pwd));
            return cus == null?null:cus;
        } else {
            return null;
        }
    }

    public boolean checkNumber(String number, String code) {
        boolean ret = false;
        return number == null?ret:(!number.equalsIgnoreCase(code)?ret:true);
    }
}
