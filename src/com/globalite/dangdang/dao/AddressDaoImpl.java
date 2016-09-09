package com.globalite.dangdang.dao;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.dao.AddressDao;
import com.globalite.dangdang.entity.City;
import com.globalite.dangdang.entity.Consignmentaddress;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AddressDaoImpl extends HibernateDaoSupport implements AddressDao {
    public AddressDaoImpl() {
    }

    public List<?> findAllCustomerAddress(Long id) {
        DetachedCriteria dc = DetachedCriteria.forClass(Consignmentaddress.class);
        dc.createAlias("cityByDistrict", "b", 1).createAlias("cityByCountry", "c", 1).createAlias("cityByCity", "d", 1).createAlias("cityByProvince", "e", 1).createAlias("customer", "f", 1).add(Restrictions.eq("f.id", id)).addOrder(Order.asc("id"));
        List ret = super.getHibernateTemplate().findByCriteria(dc);
        return ret;
    }

    public Consignmentaddress findAddressById(Long id) {
        DetachedCriteria dc = DetachedCriteria.forClass(Consignmentaddress.class);
        dc.createAlias("cityByDistrict", "b", 1).createAlias("cityByCountry", "c", 1).createAlias("cityByCity", "d", 1).createAlias("cityByProvince", "e", 1).createAlias("customer", "f", 1).add(Restrictions.eq("id", id));
        List ret = super.getHibernateTemplate().findByCriteria(dc);
        return ret.size() == 0?null:(Consignmentaddress)ret.get(0);
    }

    public void save(Consignmentaddress address) {
        address.setDefault_(Byte.valueOf((byte)1));
        address.setDeleteflag(Byte.valueOf((byte)0));

        try {
            address.setCityByCity((City)super.getHibernateTemplate().load(City.class, address.getCityByCity().getId()));
            address.setCityByCountry((City)super.getHibernateTemplate().load(City.class, address.getCityByCountry().getId()));
            address.setCityByDistrict((City)super.getHibernateTemplate().load(City.class, address.getCityByDistrict().getId()));
            address.setCityByProvince((City)super.getHibernateTemplate().load(City.class, address.getCityByProvince().getId()));
            super.getHibernateTemplate().save(address);
        } catch (DataAccessException var3) {
            var3.printStackTrace();
        }

    }
}
