package com.present.sign.bean;

import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.ResponseDto;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Larry-sea on 2017/3/20.
 *
 * 已经选择了课程现在开始选择 班级进行签到
 *
 */
public class SelectCourrseToSign extends BaseService{

    @Autowired



    @Override
    public ResponseDto process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params,params.getString("courseSignId"));

        return super.process(params, request, response);
    }



}
