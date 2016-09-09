package com.globalite.dangdang.dao;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.dao.CustomerDao;
import com.globalite.dangdang.entity.City;
import com.globalite.dangdang.entity.Customer;
import java.util.List;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {
    public CustomerDaoImpl() {
    }

    public Customer findCustomer(String regname, String pwd) {
        String hql = "From Customer A ";
        hql = hql + "left join fetch A.customerlevel ";
        hql = hql + "left join fetch A.head ";
        hql = hql + "left join fetch A.city ";
        hql = hql + "where A.regname = ? and A.password = ?";
        List ret = super.getHibernateTemplate().find(hql, new Object[]{regname, pwd});
        return ret.size() == 0?null:(Customer)ret.get(0);
    }

    public void saveCustomer(Customer cus) {
        super.getHibernateTemplate().save(cus);
    }

    public List<?> getAllRegnames() {
        return super.getHibernateTemplate().find("Select A.regname From Customer A");
    }

    public void updateCustomer(Customer cus) {
        try {
            Customer e = (Customer)super.getSession().get(Customer.class, cus.getId());
            e.setBirthday(cus.getBirthday());
            e.setIdentity(cus.getIdentity());
            e.setName(cus.getName());
            e.setSex(cus.getSex());
            if(cus.getHead() != null) {
                e.setHead(cus.getHead());
            }

            if(cus.getCity() != null && !cus.getCity().getId().equals("000")) {
                e.setCity((City)super.getSession().get(City.class, cus.getCity().getId()));
            } else {
                e.setCity((City)null);
            }

            super.getSession().update(e);
        } catch (HibernateException var3) {
            var3.printStackTrace();
        }

    }

    public void updateEMail(Long id, String email) {
        try {
            Customer e = (Customer)super.getHibernateTemplate().get(Customer.class, id);
            if(e == null) {
                return;
            }

            e.setEmail(email);
            super.getHibernateTemplate().update(e);
        } catch (HibernateException var4) {
            var4.printStackTrace();
        }

    }

    public void updatePassword(Long id, String password) {
        try {
            Customer e = (Customer)super.getHibernateTemplate().get(Customer.class, id);
            if(e == null) {
                return;
            }

            e.setPassword(password);
            super.getHibernateTemplate().update(e);
        } catch (HibernateException var4) {
            var4.printStackTrace();
        }

    }
}

