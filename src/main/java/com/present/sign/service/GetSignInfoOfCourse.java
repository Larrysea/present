package com.present.sign.service;

import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.ResponseDto;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.sign.bean.StudentSign;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Larry-sea on 2017/3/21.
 *
 * 获取某门课程的考勤信息
 *
 */
public class GetSignInfoOfCourse  extends BaseService<List<StudentSign>>{


    @Autowired

    @Override
    public ResponseDto<List<StudentSign>> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params,"courseName","teacherId");


        return super.process(params, request, response);
    }
}
