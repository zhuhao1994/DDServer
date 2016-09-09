package com.globalite.dangdang.common;

/**
 * Created by zhu on 2016/9/5.
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringManager {
    private static SpringManager instance = null;
    private ApplicationContext context = null;

    private SpringManager(String configFile) {
        this.context = new ClassPathXmlApplicationContext(configFile);
    }

    public static SpringManager createManager(String configFile) {
        if(instance == null) {
            instance = new SpringManager(configFile);
        }

        return instance;
    }

    public static SpringManager getManager() {
        return instance;
    }

    public Object getBean(String name) {
        return this.context.getBean(name);
    }
}
