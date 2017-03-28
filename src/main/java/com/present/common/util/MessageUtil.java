package com.present.common.util;

import com.present.common.dto.MessageInfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;


public class MessageUtil {

    private static MessageInfoDto successDto = getMessageInfoByKey("success");

    private final static Logger logger = LoggerFactory.getLogger(POIUtil.class);

    /**
     * 成功的返回码和返回码描述。
     *
     * @return 返回码和返回码描述
     */
    public static MessageInfoDto getMessageInfoSuccess() {
        return successDto;
    }

    /**
     * 根据key检索message文件得到返回码和返回码描述。
     *
     * @param key 检索key
     * @return 返回码和返回码描述
     */
    public static MessageInfoDto getMessageInfoByKey(String key) {

        int state = Integer.parseInt(getMessageRcByKey(key));
        String message = getMessageRdByKey(key);
        return new MessageInfoDto(state, message);

    }

    /**
     * 根据key检索message文件得到返回码。
     *
     * @param key 检索key
     * @return 返回码
     */
    public static String getMessageRcByKey(String key) {
        return MessagePropertiesConfigurer.getMessagePropertyByKey(key + ".code");
    }

    /**
     * 根据key检索message文件得到返回码描述。
     *
     * @param key 检索key
     * @return 返回码描述
     */
    public static String getMessageRdByKey(String key) {
        return MessagePropertiesConfigurer.getMessagePropertyByKey(key + ".message");
    }

    private static class MessagePropertiesConfigurer {

        /**
         * messageProperies配置取得的变量
         */
        private static Properties messageProperties = (Properties) BeanFactoryUtil.getBean("messageProperties");

        protected static String getMessagePropertyByKey(String key) {
            return messageProperties.getProperty(key);
        }
    }
}