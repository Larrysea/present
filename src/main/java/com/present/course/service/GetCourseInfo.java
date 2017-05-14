package com.present.course.service;

import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.ResponseDto;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.course.bean.Course;
import com.present.course.dao.CourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 根据课程id获取某个课程的详细信息
 *
 * Created by Larry-sea on 2017/5/13.
 */
@Service("getCourseInfo")
public class GetCourseInfo extends BaseService<Course> {
    @Autowired
    CourseDao courseDao;

    @Override
    public ResponseDto<Course> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "courseId");
        Course course = courseDao.queryByKey(params.getString("courseId"));
        ResponseDto<Course> responseDto = new ResponseDto<Course>();
        responseDto.setData(course);
        return responseDto;
    }
}
