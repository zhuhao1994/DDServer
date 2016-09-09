package com.globalite.dangdang.dao;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.dao.ImagesDao;
import com.globalite.dangdang.entity.Images;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ImagesDaoImpl extends HibernateDaoSupport implements ImagesDao {
    public ImagesDaoImpl() {
    }

    public void saveImages(Images images) {
        super.getHibernateTemplate().save(images);
    }
}
