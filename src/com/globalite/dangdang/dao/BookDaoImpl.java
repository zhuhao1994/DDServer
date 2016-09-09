package com.globalite.dangdang.dao;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.dao.BookDao;
import com.globalite.dangdang.entity.Book;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BookDaoImpl extends HibernateDaoSupport implements BookDao {
    public BookDaoImpl() {
    }

    public List<?> findBooks(String condition) {
        super.getHibernateTemplate().setCacheQueries(false);
        super.getHibernateTemplate().setQueryCacheRegion((String)null);
        DetachedCriteria dc = DetachedCriteria.forClass(Book.class);
        dc.createAlias("image", "b", 1).createAlias("bookcomments", "d", 1);
        dc.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if(condition != null && !"".equals(condition)) {
            dc.add(Restrictions.like("name", "%" + condition + "%"));
        }

        dc.addOrder(Order.asc("id"));
        return super.getHibernateTemplate().findByCriteria(dc);
    }

    public Book findFullBook(Long id, boolean useCache) {
        if(useCache) {
            super.getHibernateTemplate().setCacheQueries(true);
            super.getHibernateTemplate().setQueryCacheRegion("com.globalite.dangdang.entity.Book");
        } else {
            super.getHibernateTemplate().setCacheQueries(false);
            super.getHibernateTemplate().setQueryCacheRegion((String)null);
        }

        DetachedCriteria dc = DetachedCriteria.forClass(Book.class);
        dc.createAlias("image", "b", 1).createAlias("booktype", "c", 1).createAlias("bookcomments", "d", 1).createAlias("d.customer", "e", 1).add(Restrictions.eq("id", id));
        dc.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List ret = super.getHibernateTemplate().findByCriteria(dc);
        return ret.size() == 0?null:(Book)ret.get(0);
    }
}

