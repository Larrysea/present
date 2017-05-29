/**
 * 北京红云融通技术有限公司
 * 日期：$$Date$$
 * 作者：$$Author$$
 * 版本：$$Rev$$
 * 版权：All rights reserved.
 */
package com.present.common.dao;

import com.present.common.dto.MessageInfoDto;
import com.present.common.exception.ExternalServiceException;
import com.present.common.util.MessageUtil;
import com.present.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.List;
import java.util.Map;


/**  
 * redis操作工具类  
 *  
 */
@Repository
public class CacheDao {
	
	private static JedisPool pool = null;
	private static Logger logger = LoggerFactory.getLogger(CacheDao.class);
	public void init() 
	{
		String url = PropertiesUtil.getProperty("redis.url");
		int port = PropertiesUtil.getPropertyInt("redis.port");
		String password = PropertiesUtil.getProperty("redis.password");
		// 连接池配置
		int maxTotal = PropertiesUtil.getPropertyInt("redis.maxTotal");
		int maxIdle = PropertiesUtil.getPropertyInt("redis.maxIdle");
		int maxWaitMillis = PropertiesUtil.getPropertyInt("redis.maxWaitMillis");
		int minIdle = PropertiesUtil.getPropertyInt("redis.minIdle");
		int readTimeout = PropertiesUtil.getPropertyInt("redis.readTimeout");
		String testWhileIdle = PropertiesUtil.getProperty("redis.testWhileIdle");
		int timeBetweenEvictionRunsMillis = PropertiesUtil.getPropertyInt("redis.timeBetweenEvictionRunsMillis");
		int minEvictableIdleTimeMillis = PropertiesUtil.getPropertyInt("redis.minEvictableIdleTimeMillis");
		
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(maxTotal);
		config.setMaxIdle(maxIdle);
		config.setMaxWaitMillis(maxWaitMillis);
		config.setMinIdle(minIdle);
		//Idle时进行连接扫描
		config.setTestWhileIdle(Boolean.valueOf(testWhileIdle));
		//表示idle object evitor两次扫描之间要sleep的毫秒数
		config.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		config.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		
		if (StringUtil.isNotBlank(password))
		{
			pool = new JedisPool(config, url, port, readTimeout, password);
		}
		else 
		{
			pool = new JedisPool(config, url, port, readTimeout);
		}
	}
      
    /**  
     * 设置String数据类型  
     *   
     * @param key  
     * @param value  
     * @return  
     */  
    public String set(String key, String value, int time) { 
    	if (value == null)
    	{
    		value = "";
    	}
    	Jedis jedis = null;
    	try 
    	{
            jedis = pool.getResource();
            logger.info("set key = " + key + " and value = " + value + " and time = " + time);

            return jedis.setex(key, time, value);
    	}
    	catch (Exception e)
    	{
    		MessageInfoDto messageInfo = MessageUtil.getMessageInfoByKey("redis.connect.timeout");
    		throw new ExternalServiceException(messageInfo);
    	}
    	finally 
    	{
	        if (jedis != null)
	        {
	          jedis.close();
	        }
    	}
    }  
    
    /**  
     * 设置String数据类型  
     *   
     *
	 * @param keyvalueMap
	 * @return
	 */
    public List<Object> setpipeline(Map<String,String> keyvalueMap) { 
    	if (keyvalueMap == null)
    	{
    		return null;
    	}
    	Jedis jedis = null;
    	try 
    	{
            jedis = pool.getResource();
            Pipeline pipeline = jedis.pipelined();
            for (Map.Entry<String, String> entry : keyvalueMap.entrySet()) {  
            	logger.info("setpipeline key = " + entry.getKey() + " and value = " + entry.getValue());  
                pipeline.set(entry.getKey(), entry.getValue());
            }  
            return pipeline.syncAndReturnAll();
    	}
    	catch (Exception e)
    	{
    		MessageInfoDto messageInfo = MessageUtil.getMessageInfoByKey("redis.connect.timeout");
    		throw new ExternalServiceException(messageInfo);
    	}
    	finally 
    	{
	        if (jedis != null)
	        {
	          jedis.close();
	        }
    	}
    }  
    
    /**  
     * 设置String数据类型  
     *   
     * @param key  
     * @param value  
     * @return  
     */  
    public String set(String key, String value) {  
    	if (value == null)
    	{
    		value = "";
    	}
    	Jedis jedis = null;
    	try 
    	{
            jedis = pool.getResource();
            logger.info("set key = " + key + " and value = " + value);  
            return jedis.set(key, value);
    	}
    	catch (Exception e)
    	{
    		MessageInfoDto messageInfo = MessageUtil.getMessageInfoByKey("redis.connect.timeout");
    		throw new ExternalServiceException(messageInfo);
    	}
    	finally 
    	{
	        if (jedis != null)
	        {
	          jedis.close();
	        }
    	}
    } 
  
    public String get(String key) { 
    	Jedis jedis = null;
    	try 
    	{
            jedis = pool.getResource();
            return jedis.get(key);
    	}
    	catch (Exception e)
    	{
    		MessageInfoDto messageInfo = MessageUtil.getMessageInfoByKey("redis.connect.timeout");
    		throw new ExternalServiceException(messageInfo);
    	}
    	finally 
    	{
	        if (jedis != null)
	        {
	          jedis.close();
	        }
    	}
    } 
  
    public boolean isExist(String key) {  
    	Jedis jedis = null;
    	try 
    	{
            jedis = pool.getResource();
            return jedis.exists(key);
    	}
    	catch (Exception e)
    	{
    		MessageInfoDto messageInfo = MessageUtil.getMessageInfoByKey("redis.connect.timeout");
    		throw new ExternalServiceException(messageInfo);
    	}
    	finally 
    	{
	        if (jedis != null)
	        {
	          jedis.close();
	        }
    	}
    }
  
    public void delete(String key) {  
    	Jedis jedis = null;
    	try 
    	{
            jedis = pool.getResource();
            logger.info("delete key = " + key);
            jedis.del(key);
    	}
    	catch (Exception e)
    	{
    		MessageInfoDto messageInfo = MessageUtil.getMessageInfoByKey("redis.connect.timeout");
    		throw new ExternalServiceException(messageInfo);
    	}
    	finally 
    	{
	        if (jedis != null)
	        {
	          jedis.close();
	        }
    	}    	
    }
    
    public long ttl(String key) {  
    	Jedis jedis = null;
    	long time = 0 ;
    	try 
    	{
            jedis = pool.getResource();
            logger.info("ttl:key = " + key + " and result = " + time); 
            time = jedis.ttl(key);
	        return time;
    	}
    	catch (Exception e)
    	{
    		MessageInfoDto messageInfo = MessageUtil.getMessageInfoByKey("redis.connect.timeout");
    		throw new ExternalServiceException(messageInfo);
    	}
    	finally 
    	{
	        if (jedis != null)
	        {
	          jedis.close();
	        }
    	}    	
    }
}  