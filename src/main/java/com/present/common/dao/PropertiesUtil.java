package com.present.common.dao;


import com.present.common.plugin.PropertiesConfigurer;

public class PropertiesUtil {

    /**
     * 根据key检索config文件得到对应值。
     * 
     * @param key 检索key
     * @return key对应值
     */
    public static String getProperty(String key)
    {
        return PropertiesConfigurer.getContextProperty(key);
    }
    
    /**
     * 根据key检索config文件得到对应值。
     * 
     * @param key 检索key
     * @return key对应值
     */
    public static int getPropertyInt(String key)
    {
        return Integer.parseInt(PropertiesConfigurer.getContextProperty(key));
    }
}