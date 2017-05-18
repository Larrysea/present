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
import com.present.course.bean.TeacherCourse;
import com.present.course.dao.CourseDao;
import com.present.course.dao.TeacherCourseDao;
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

    @Autowired
    TeacherCourseDao teacherCourseDao;

    @Override
    public ResponseDto<Course> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "courseName", "teacherId");

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
        course.setTeacherId(params.getString("teacherId"));
        courseDao.insert(course);
        ResponseDto<Course> courseDto = new ResponseDto<Course>();
        courseDto.setData(course);
        TeacherCourse teacherCourse = new TeacherCourse();
        teacherCourse.setCourseId(course.getId());
        teacherCourse.setDataState("1");
        teacherCourse.setTeacherId(params.getString("teacherId"));
        teacherCourseDao.insert(teacherCourse);
        return courseDto;
    }
}
