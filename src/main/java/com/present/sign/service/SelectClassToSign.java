package com.present.sign.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.ResponseDto;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.sign.bean.SignStartWithClass;
import com.present.sign.dao.SignStartWithClassDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Larry-sea on 2017/3/20.
 * <p>
 * 已经确定是那个课程了，现在开始选择班级进行签到
 */
public class SelectClassToSign extends BaseService {


    @Autowired
    SignStartWithClassDao signStartWithClassDao;

    @Override
    public ResponseDto process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, params.getString("course_sign_id"), params.getString("classArray"));

        return selectClassesToSign(params);
    }


    /**
     * 在确定某个课程的情况下选择班级进行签到
     *
     * @param params   输入参数r
     * @return  返回responseDto
     */
    public ResponseDto selectClassesToSign(JSONObject params) {
        JSONArray classArray = null;
        SignStartWithClass signStartWithClass;
        if (null != params.getJSONArray("classArray")) {
            classArray = params.getJSONArray("classArray");
            for (int position = 0; position < classArray.size(); position++) {
                signStartWithClass = (SignStartWithClass) classArray.get(position);
                signStartWithClassDao.insert(signStartWithClass);
            }
        }

        return new ResponseDto();
    }
}
