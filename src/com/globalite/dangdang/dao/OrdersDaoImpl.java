package com.globalite.dangdang.dao;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.dao.OrdersDao;
import com.globalite.dangdang.entity.Orders;
import com.globalite.dangdang.struts.web.form.OrdersForm;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class OrdersDaoImpl extends HibernateDaoSupport implements OrdersDao {
    public OrdersDaoImpl() {
    }

    public Orders findOrderByOrderId(String orderId) {
        DetachedCriteria dc = DetachedCriteria.forClass(Orders.class);
        dc.add(Restrictions.like("ordid", orderId + "%")).addOrder(Order.desc("ordid"));
        List ret = super.getHibernateTemplate().findByCriteria(dc);
        return ret.size() == 0?null:(Orders)ret.get(0);
    }

    public void saveOrder(Orders orders) {
        super.getHibernateTemplate().save(orders);
    }

    public List<?> findOrdersByCondition(Long cusid) {
        OrdersForm vo = new OrdersForm();
        vo.setCusid(cusid);
        return this.findOrdersByCondition(vo);
    }

    public List<?> findOrdersByCondition(OrdersForm vo) {
        DetachedCriteria c = DetachedCriteria.forClass(Orders.class);
        c.createCriteria("this.customer", "b", 1).createCriteria("this.orderitemses", "c", 1).createCriteria("c.book", "d", 1).createCriteria("d.image", "e", 1).add(Restrictions.eq("this.deleteflag", Byte.valueOf((byte)0))).add(Restrictions.eq("b.id", vo.getCusid()));
        if(vo != null) {
            if(vo.getOrdid() != null && !"".equals(vo.getOrdid().trim())) {
                c.add(Restrictions.eq("ordid", vo.getOrdid().toString()));
            }

            if(vo.getPriceFrom() != null && !"".equals(vo.getPriceFrom().trim())) {
                c.add(Restrictions.ge("price", BigDecimal.valueOf(Double.valueOf(vo.getPriceFrom().trim()).doubleValue())));
            }

            if(vo.getPriceTo() != null && !"".equals(vo.getPriceTo().trim())) {
                c.add(Restrictions.le("price", BigDecimal.valueOf(Double.valueOf(vo.getPriceTo().trim()).doubleValue())));
            }

            if(vo.getDateFrom() != null && !"".equals(vo.getDateFrom().trim())) {
                c.add(Restrictions.ge("createdate", Date.valueOf(vo.getDateFrom().trim())));
            }

            if(vo.getDateTo() != null && !"".equals(vo.getDateTo().trim())) {
                c.add(Restrictions.le("createdate", Date.valueOf(vo.getDateTo().trim())));
            }
        }

        c.addOrder(Order.desc("createdate")).addOrder(Order.asc("id"));
        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return super.getHibernateTemplate().findByCriteria(c);
    }

    public void updateOrdersType(Long id, Byte ordersType) {
        Orders orders = (Orders)super.getHibernateTemplate().get(Orders.class, id);
        if(orders != null) {
            orders.setOrdertype(ordersType);
        }

    }
}

