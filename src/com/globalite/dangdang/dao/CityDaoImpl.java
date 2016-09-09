package com.globalite.dangdang.dao;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.dao.CityDao;
import com.globalite.dangdang.entity.City;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CityDaoImpl extends HibernateDaoSupport implements CityDao {
    public CityDaoImpl() {
    }

    public List<?> findCities(String provinceId) {
        DetachedCriteria dc = DetachedCriteria.forClass(City.class);
        dc.add(Restrictions.eq("parentid", provinceId)).add(Restrictions.eq("type", Byte.valueOf((byte)2)));
        List ret = super.getHibernateTemplate().findByCriteria(dc);
        if(ret.size() > 0) {
            return ret;
        } else {
            dc = DetachedCriteria.forClass(City.class);
            dc.add(Restrictions.eq("id", provinceId.split("-")[0]));
            return super.getHibernateTemplate().findByCriteria(dc);
        }
    }

    public City findById(String id) {
        return (City)super.getHibernateTemplate().get(City.class, id);
    }

    public List<?> findCountries() {
        DetachedCriteria dc = DetachedCriteria.forClass(City.class);
        dc.add(Restrictions.eq("type", Byte.valueOf((byte)0)));
        return super.getHibernateTemplate().findByCriteria(dc);
    }

    public List<?> findProvinces(String countryId) {
        DetachedCriteria dc = DetachedCriteria.forClass(City.class);
        dc.add(Restrictions.eq("parentid", countryId)).add(Restrictions.eq("type", Byte.valueOf((byte)1)));
        return super.getHibernateTemplate().findByCriteria(dc);
    }

    public List<?> findAllProvinces() {
        DetachedCriteria dc = DetachedCriteria.forClass(City.class);
        dc.add(Restrictions.eq("type", Byte.valueOf((byte)1)));
        return super.getHibernateTemplate().findByCriteria(dc);
    }

    public List<?> findDistricts(String cityId) {
        DetachedCriteria dc = DetachedCriteria.forClass(City.class);
        dc.add(Restrictions.eq("parentid", cityId)).add(Restrictions.eq("type", Byte.valueOf((byte)3)));
        return super.getHibernateTemplate().findByCriteria(dc);
    }

    public List<?> findDistrictsByProvince(String provinceId) {
        DetachedCriteria dc = DetachedCriteria.forClass(City.class);
        dc.add(Restrictions.eq("parentid", provinceId));
        return super.getHibernateTemplate().findByCriteria(dc);
    }
}

