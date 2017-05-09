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

/**
 * Created by Larry-sea on 2017/3/20.
 * <p>
 * 选择课程开始签到
 */
@Service("selectCourseToSign")
public class SelectCourseToSign extends BaseService<String> {


    @Autowired
    CourseSignDao courseSignDao;

    @Override
    public ResponseDto<String> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "courseId", "signStartType", "teacherId", "validOfTime");
        return selectCourseToSign(params);
    }

    /**
     * 选择课程发起签到
     *
     * @param params    请求的参数
     * @return
     */
    private ResponseDto<String> selectCourseToSign(JSONObject params) {
        CourseSign courseSign = new CourseSign();
        courseSign.setCourseId(params.getString("courseId"));
        courseSign.setSignStartType(params.getString("signStartType"));
        courseSign.setTeacherId(params.getString("teacherId"));
        courseSign.setValidOfTime(params.getInteger("validOfTime"));
        courseSign.setDataState("1");
        courseSignDao.insert(courseSign);
        ResponseDto<String> responseDto = new ResponseDto<String>();
        responseDto.setData(courseSign.getId());
        return responseDto;
    }
}
