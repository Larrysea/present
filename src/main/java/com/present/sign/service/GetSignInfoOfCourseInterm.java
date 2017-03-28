package com.present.sign.service;

import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.ResponseDto;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.sign.bean.StudentSign;
import com.present.sign.dao.CourseSignInfoInTermDao;
import com.present.sign.dto.StudentSignInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Larry-sea on 2017/3/21.
 * <p>
 * 获取某门课程的考勤信息
 */

@Service("getSignInfoOfCourseInTerm")
public class GetSignInfoOfCourseInTerm extends BaseService<List<StudentSignInfoDto>> {


    /*
    *
    * 获取课程签到信息的dto
    * */
    @Autowired
    CourseSignInfoInTermDao getCourseSignInfoInTerm;

    @Override
    public ResponseDto<List<StudentSignInfoDto>> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "teacherId", "courseId", "classId");
        List<StudentSignInfoDto> studentSignInfoDtos = getCourseSignInfoInTerm.getCourseSignInfoInTerm(params.getString("teacherId"), params.getString("courseId"), params.getString("classId"));
        ResponseDto<List<StudentSignInfoDto>> responseDto = new ResponseDto<List<StudentSignInfoDto>>();
        responseDto.setData(studentSignInfoDtos);
        return responseDto;
    }
}
