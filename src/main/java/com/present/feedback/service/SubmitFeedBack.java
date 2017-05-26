package com.present.feedback.service;

import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.ResponseDto;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.feedback.bean.Feedback;
import com.present.feedback.dao.FeedbackDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Larry-sea on 2017/5/25.
 * <p>
 * 提交反馈接口
 */
@Service("submitFeedback")
public class SubmitFeedBack extends BaseService<String> {

    @Autowired
    FeedbackDao feedbackDao;

    @Override
    public ResponseDto<String> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "userId", "contactWay", "content", "osInfo", "deviceInfo");
        Feedback feedback = new Feedback();
        feedback.setContactWay(params.getString("contactWay"));
        feedback.setDeviceInfo(params.getString("deviceInfo"));
        feedback.setOsInfo(params.getString("osInfo"));
        feedback.setUserid(params.getString("userId"));
        feedback.setFeedBackContent(params.getString("content"));
        feedbackDao.insert(feedback);
        return new ResponseDto<String>();
    }
}
