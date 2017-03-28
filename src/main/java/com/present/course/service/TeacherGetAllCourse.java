package com.present.course.service;

import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.ResponseDto;
import com.present.common.exception.ExternalServiceException;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.common.util.MessageUtil;
import com.present.course.bean.Course;
import com.present.course.dao.TeacherCourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Larry-sea on 2017/3/20.
 * <p>
 * 老师获取所有课程
 */
@Service("teacherGetAllCourse")
public class TeacherGetAllCourse extends BaseService<List<Course>> {

    @Autowired
    TeacherCourseDao teacherCourseDao;

    @Override
    public ResponseDto<List<Course>> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "teacherId");
        List<Course> arrayList = teacherCourseDao.queryCourseByTeacherId(params.getString("teacherId"));
        ResponseDto<List<Course>> responseDto = new ResponseDto<List<Course>>();
        responseDto.setData(arrayList);
        return responseDto;
    }
}
