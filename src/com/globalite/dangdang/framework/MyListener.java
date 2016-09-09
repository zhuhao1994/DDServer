package com.globalite.dangdang.framework;

/**
 * Created by zhu on 2016/9/5.
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MyListener implements ServletContextListener {
    public MyListener() {
    }

    public void contextDestroyed(ServletContextEvent event) {
    }

    public void contextInitialized(ServletContextEvent event) {
        Configuration config = (new Configuration()).configure();
        SessionFactory factory = config.buildSessionFactory();
        event.getServletContext().setAttribute("SecondLevelCache", factory);
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("[" + formater.format(new Date()) + "] Hibernate的配置文件被完全读取，" + "并且将二级缓存创建成功保存倒了SerlvetContext容器中，" + "以后使用Hibnerate的时候二级缓存可以直接从SerlvetContext中获取");
    }
}

