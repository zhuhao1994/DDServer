package com.globalite.dangdang.dao;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.entity.Customer;
import java.util.List;

public interface CustomerDao {
    Customer findCustomer(String var1, String var2);

    void saveCustomer(Customer var1);

    List<?> getAllRegnames();

    void updateCustomer(Customer var1);

    void updatePassword(Long var1, String var2);

    void updateEMail(Long var1, String var2);
}