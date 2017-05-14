package com.present.sign.service;

import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.ResponseDto;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.sign.bean.CourseSign;
import com.present.sign.dao.CourseSignDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Larry-sea on 2017/5/13.
 * <p>
 * 获取某门课程的所有签到发起记录
 */
@Service("getCourseAllSignInfo")
public class GetCourseAllSignInfo extends BaseService<List<CourseSign>> {


    @Autowired
    CourseSignDao courseSignDao;

    @Override
    public ResponseDto<List<CourseSign>> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "courseId");
        List<CourseSign> courseSignList = courseSignDao.getCourseAllSignInfo(params.getString("courseId"));
        ResponseDto<List<CourseSign>> resp = new ResponseDto<List<CourseSign>>();
        resp.setData(courseSignList);
        return resp;
    }
}
