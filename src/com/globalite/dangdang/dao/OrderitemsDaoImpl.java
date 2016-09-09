package com.globalite.dangdang.dao;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.dao.OrderitemsDao;
import com.globalite.dangdang.entity.Orderitems;
import java.util.Iterator;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class OrderitemsDaoImpl extends HibernateDaoSupport implements OrderitemsDao {
    public OrderitemsDaoImpl() {
    }

    public void saveItems(List<Orderitems> items) {
        Iterator var3 = items.iterator();

        while(var3.hasNext()) {
            Orderitems item = (Orderitems)var3.next();
            super.getHibernateTemplate().save(item);
        }

    }
}

