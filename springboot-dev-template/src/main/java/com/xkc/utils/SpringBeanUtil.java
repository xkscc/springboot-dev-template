package com.xkc.utils;

import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author: chenwei
 * @CreateTime: 2022-05-05  15:52
 * @Description: 获取spring上下文对象
 * @Version: 1.0
 */
public class SpringBeanUtil {

    private static ConfigurableApplicationContext applicationContext;

    public static void setContext(ConfigurableApplicationContext context) {
        if (applicationContext == null) {
            applicationContext = context;
        }
    }

    public static <T> T getBean(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }
}
