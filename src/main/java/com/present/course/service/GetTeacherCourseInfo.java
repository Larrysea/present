package com.present.course.service;

import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.ResponseDto;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.course.dao.TeacherCourseDao;
import com.present.course.dto.CourseAndTeacherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Larry-sea on 2017/5/13.
 * <p>
 * 获取老师和课程信息
 */
@Service("getTeacherAndCourseInfo")
public class GetTeacherCourseInfo extends BaseService<CourseAndTeacherDto> {

    @Autowired
    TeacherCourseDao teacherCourseDao;

    @Override
    public ResponseDto<CourseAndTeacherDto> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "courseId");
        CourseAndTeacherDto courseAndTeacherDto = teacherCourseDao.getTeacherAndCourseInfo(params.getString("courseId"));
        ResponseDto<CourseAndTeacherDto> responseDto = new ResponseDto<CourseAndTeacherDto>();
        responseDto.setData(courseAndTeacherDto);
        return responseDto;
    }
}
