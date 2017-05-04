package com.present.course.service;

/**
 * Created by Larry-sea on 2017/3/18.
 */

import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.ResponseDto;
import com.present.common.exception.ExternalServiceException;
import com.present.common.service.BaseService;
import com.present.common.service.TokenApiService;
import com.present.common.util.CheckUtil;
import com.present.common.util.MessageUtil;
import com.present.course.bean.Course;
import com.present.course.dao.CourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Larry-sea on 2017/3/18.
 * <p>
 * 老师添加课程信息
 */
@Service("addCourse")
public class AddCourseService extends BaseService<Course> {

    @Autowired
    CourseDao courseDao;

    @Autowired
    TokenApiService tokenApiService;

    @Override
    public ResponseDto<Course> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "courseName");

        return addCourse(params);
    }


    //todo 老师添加课程接口中需要添加老师的id，而不只是课程名
    /**
     * 添加课程信息
     *
     * @param params
     * @return
     */
    public ResponseDto<Course> addCourse(final JSONObject params) {
        String courseId = courseDao.queryIdByCourseName(params.getString("course"));
        if (courseId != null) {
            throw new ExternalServiceException(MessageUtil.getMessageInfoByKey("course.addcourse"));
        }
        Course course = new Course();
        course.setCourseName(params.getString("courseName"));
        courseDao.insert(course);
        ResponseDto<Course> courseDto = new ResponseDto<Course>();
        courseDto.setData(course);
        return courseDto;
    }
}
