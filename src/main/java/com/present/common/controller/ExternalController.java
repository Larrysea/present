package com.present.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.MessageInfoDto;
import com.present.common.dto.ResponseDto;
import com.present.common.exception.ExternalServiceException;
import com.present.common.service.IBaseService;
import com.present.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ExternalController{
	
	private static Logger logger = LoggerFactory.getLogger(ExternalController.class);
	
	/**
	 * 后台管理接口入口。
	 * 
	 * @param service 调用接口服务名
	 * @param params 调用接口参数名
	 * @param request request对象
	 * @return 调用结果
	 * @throws IOException 
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/{service}")
	@ResponseBody
	public String executeExternalService( @PathVariable String service, @RequestBody  String params,
			HttpServletRequest request, HttpServletResponse response) throws IOException
	{		
		response.addHeader("Access-Control-Allow-Origin", "*");  //跨域访问用
		
		String uuid = StringUtil.getUUIDString();
		logger.info("request({}):service={}, params={}", new Object[]{uuid, service, params});

		ResponseDto rspDto = new ResponseDto();
		String rspStr = "";
		MessageInfoDto messageInfo = null;
		try
		{			
			messageInfo = MessageUtil.getMessageInfoSuccess();
			JSONObject paramObj = CheckUtil.parseObject(params);
			IBaseService baseService = (IBaseService) BeanFactoryUtil.getBean(service);
			rspDto = baseService.process(paramObj, request, response);
			if (rspDto.getCode() == 0) {
				rspDto.setMessageInfo(messageInfo);
			}
		}
		catch(RecoverableDataAccessException e){
			messageInfo = MessageUtil.getMessageInfoByKey("db.error");
			rspDto.setMessageInfo(messageInfo);
			logger.error("db.error:", e);
		}
		catch(DataAccessResourceFailureException e){
			messageInfo = MessageUtil.getMessageInfoByKey("db.error");
			rspDto.setMessageInfo(messageInfo);
			logger.error("db.error:", e);
		}	
	
		catch (BeansException e)
		{
			messageInfo = MessageUtil.getMessageInfoByKey("service.not.exist");
			rspDto.setMessageInfo(messageInfo);
			logger.error("BeansException:", e);
		}
		catch (ExternalServiceException e)
		{
			rspDto.setMessageInfo(e.getMessageInfo());
		}
		
		catch (Throwable e)
		{
			messageInfo = MessageUtil.getMessageInfoByKey("system.error");
			rspDto.setMessageInfo(messageInfo);
			logger.error("exception:", e);
		}
		finally
		{	
			rspStr = rspDto.toString();
			logger.info("response({}):{}", uuid, rspStr);
		}
		return rspStr;
	}
}
