package com.present.common.service;

import com.present.common.dao.CacheDao;
import com.present.common.dao.PropertiesUtil;
import com.present.common.dto.MessageInfoDto;
import com.present.common.exception.ExternalException;
import com.present.common.exception.ExternalServiceException;
import com.present.common.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Larry-sea on 2017/3/17.
 * <p>
 * 用户登录鉴权token
 */
@Service
public class TokenApiService {

    private final Logger logger = LoggerFactory.getLogger(TokenApiService.class);

    private final String expire_time = PropertiesUtil.getProperty("token_expire_time");


    @Autowired
    CacheDao cacheDao;


    /**
     * @param userId 用户id
     */
    public void setToken(String userId) {
        long nowTime = new Date().getTime();
        //token 的生成规则是用户id 加上当前时间
        cacheDao.set(userId, userId + "_" + String.valueOf(nowTime));
    }


    /**
     * 根据用户的用户id获取token
     *
     * @param userId
     * @return  如果该用户未登录则没有token
     */
    public String getToken(String userId) {

        return cacheDao.get(userId);
    }


    public void checkToken(String token) {

        if (cacheDao.get(getUserId(token)) != null) {
            //用户token有效
            if (cacheDao.get(getUserId(token)).equals(token)) {
                return;
            }
        } else {
            //token无效
            MessageInfoDto messageInfoDto = MessageUtil.getMessageInfoByKey("token.invalid");
            throw new ExternalServiceException(messageInfoDto);
        }
    }


    /**
     * @param token
     * @return
     */
    public String getUserId(String token) {
        int endIndex = 0;
        if (token != null) {
            endIndex = token.indexOf('_');
            return token.substring(0, endIndex);

        }
        return null;
    }


}
