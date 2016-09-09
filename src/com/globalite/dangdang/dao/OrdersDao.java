package com.globalite.dangdang.dao;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.entity.Orders;
import com.globalite.dangdang.struts.web.form.OrdersForm;
import java.util.List;

public interface OrdersDao {
    void saveOrder(Orders var1);

    Orders findOrderByOrderId(String var1);

    List<?> findOrdersByCondition(OrdersForm var1);

    List<?> findOrdersByCondition(Long var1);

    void updateOrdersType(Long var1, Byte var2);
}
