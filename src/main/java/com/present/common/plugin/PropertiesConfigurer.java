/**
 * 南京青牛通讯技术有限公司
 * 日期：$$Date: 2015-09-09 15:08:01 +0800 (周三, 09 9月 2015) $$
 * 作者：$$Author: zhangmy $$
 * 版本：$$Rev: 1158 $$
 * 版权：All rights reserved.
 */
package com.present.common.plugin;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 自定义ProjectDBinfoConfigurer返回properties内容
 * @ClassName: ProjectDBinfoConfigurer
 * @Description: 自定义ProjectDBinfoConfigurer返回properties内容
 * @author ZhangYQ iitshare@itblood.com
 * @date: 2012-11-20 下午11:48:32
 */
public class PropertiesConfigurer extends PropertyPlaceholderConfigurer {
	
    private static Map<String, String> ctxPropertiesMap;
 
    @Override
    protected void processProperties(
            ConfigurableListableBeanFactory beanFactoryToProcess,
            Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        ctxPropertiesMap = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value);
        }
    }
    public static String getContextProperty(String name) {
        return ctxPropertiesMap.get(name);
    }
}