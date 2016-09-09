package com.globalite.dangdang.dao;

/**
 * Created by zhu on 2016/9/5.
 */

import com.globalite.dangdang.entity.Orderitems;
import java.util.List;

public interface OrderitemsDao {
    void saveItems(List<Orderitems> var1);
}