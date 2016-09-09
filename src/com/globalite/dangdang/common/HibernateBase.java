package com.globalite.dangdang.common;

/**
 * Created by zhu on 2016/9/5.
 */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateBase {
    private static Session session = null;
    private static SessionFactory factory = null;
    private static Configuration conf = null;

    static {
        conf = (new Configuration()).configure();
        factory = conf.buildSessionFactory();
        session = factory.openSession();
    }

    public HibernateBase() {
    }

    public static SessionFactory rebuildFactory() {
        factory = conf.buildSessionFactory();
        return factory;
    }

    public static Session openSession() {
        return factory.openSession();
    }

    public static Session getSession() {
        if(!session.isOpen()) {
            session = openSession();
        }

        return session;
    }

    public static SessionFactory getFactory() {
        return factory;
    }
}

