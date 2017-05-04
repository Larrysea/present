package com.present.course.service;

import com.alibaba.fastjson.JSONObject;
import com.present.common.config.Constants;
import com.present.common.dto.ResponseDto;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.course.bean.Course;
import com.present.course.dao.CourseClassDao;
import com.present.course.dao.CourseDao;
import com.present.sign.bean.CourseSign;
import com.present.sign.dao.CourseSignDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Larry-sea on 2017/4/28.
 * <p>
 * <p>
 * 检查这个课程是否是这个学生的参加课程
 */
@Service("isJoinTheCourse")
public class CheckCourseIsJoinIn extends BaseService<String> {

    /**
     * 课程签到dao
     */
    @Autowired
    CourseSignDao courseSignDao;

    /**
     * 课程dao层
     */
    @Autowired
    CourseDao courseDao;


    /**
     * 课程班级dao层
     */
    @Autowired
    CourseClassDao courseClassDao;

    @Override
    public ResponseDto<String> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "courseSignId", "classId");
        CourseSign courseSign = courseSignDao.queryByKey(params.getString("courseSignId"));
        ResponseDto<String> responseDto = new ResponseDto<String>();
        /*
        * 课程签到
        * */
        if (courseSign != null) {
            String courseId = courseSign.getCourseId();
            //判断班级id是否一致如果一致返回

            //数据库中查询到的课程签到记录，与实际传输过过来的进行检查
            List<Course> courseList = courseClassDao.queryCourseByClassId(params.getString("classId"));
            if (courseList.get(0).getId().equals(courseId)) {
                responseDto.setData(Constants.ALREADY_JOIN_IN);
            } else {
                responseDto.setData(Constants.NOT_JOIN_THE_COURSE);
            }

        }
        return responseDto;
    }


    /**
     * 查询是否包含这个课程
     *
     * @param courseList 课程列表
     * @param courseId   课程id
     * @return
     */
    public boolean isContainTheCourse(final List<Course> courseList, String courseId) {

        for (Course course : courseList) {
            if (course.getId().equals(courseId)) {
                return true;
            }
        }
        return false;
    }
}
