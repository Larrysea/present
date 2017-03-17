package com.present.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public final class BeanFactoryUtil implements ApplicationContextAware 
{
    private static ApplicationContext context; 
    
    public void setApplicationContext(ApplicationContext context) throws BeansException 
    {
        BeanFactoryUtil.context = context;
    }

    /**
     * 根据一个bean的id获取配置文件中相应的bean
     * 
     * @param  beanName
     * @return Object
     * @throws BeansException BeansException
     */
    public static Object getBean(String beanName) throws BeansException
    {
        return context.getBean(beanName);
    }
}