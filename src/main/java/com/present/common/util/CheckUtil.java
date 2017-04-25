/**
 * 南京青牛通讯技术有限公司
 * 日期：$$Date: 2016-09-06 17:30:34 +0800 (星期二, 06 九月 2016) $$
 * 作者：$$Author: zhang.hao $$
 * 版本：$$Rev: 129750 $$
 * 版权：All rights reserved.
 */
package com.present.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.MessageInfoDto;
import com.present.common.exception.ExternalServiceException;


import java.text.MessageFormat;

public class CheckUtil {

    /**
     * 校验是否为JSON格式，请求参数为空返回NULL
     *
     * @param json
     * @return
     */
    public static JSONObject parseObject(String json) {
        // 参数为空的时候 默认检查成功
        if (StringUtil.isNotBlank(json)) {
            try {
                return JSON.parseObject(json);
            } catch (Exception e) {
                MessageInfoDto messageInfo = MessageUtil.getMessageInfoByKey("wrong.json");
                throw new ExternalServiceException(messageInfo);
            }
        }

        return new JSONObject();
    }

    /**
     * 必须check
     *
     * @param params
     * @return
     */
    public static void checkEmpty(JSONObject params, String... keys) {
        StringBuilder sb = new StringBuilder();
        if (params == null) {
            MessageInfoDto messageInfo = MessageUtil.getMessageInfoByKey("params.empty");
            String message = messageInfo.getMsg();
            message = MessageFormat.format(message, keys[0]);
            messageInfo.setMsg(message);

            throw new ExternalServiceException(messageInfo);
        }
        for (String key : keys) {
            String value = params.getString(key);
            if (StringUtil.isBlank(value)) {
                sb.append(",");
                sb.append(key);
            }
        }

        throwParamEmptyException(sb);
    }

    /**
     * 必须check
     *
     * @param params
     * @return
     */
    public static void checkEmptyExceptEmptyString(JSONObject params, String... keys) {
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            String value = params.getString(key);
            if (value == null) {
                sb.append(",");
                sb.append(key);
            }
        }

        throwParamEmptyException(sb);
    }

    /**
     * 检查结果是否不为空，如果不为空则表示有参数为空则抛出异常
     *
     * @param sb
     */
    private static void throwParamEmptyException(StringBuilder sb) {
        if (sb.length() > 0) {
            MessageInfoDto messageInfo = MessageUtil.getMessageInfoByKey("params.empty");
            String message = messageInfo.getMsg();
            sb.delete(0, 1);
            message = MessageFormat.format(message, sb.toString());
            messageInfo.setMsg(message);

            throw new ExternalServiceException(messageInfo);
        }
    }
}
