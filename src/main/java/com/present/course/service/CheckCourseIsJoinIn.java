package com.present.course.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.ResponseDto;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.course.bean.Course;
import com.present.course.dao.CourseClassDao;
import com.present.sign.bean.CourseSign;
import com.present.sign.dao.CourseSignDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Larry-sea on 2017/4/28.
 * <p>
 * //todo  这个类可以优化，因为需求是通过多个courseSignId 中找到一个符合id即可，所以通过改写sql通过in查询实现
 * <p>
 * 返回这个学生的正确的课程签到id
 */
@Service("isJoinTheCourse")
public class CheckCourseIsJoinIn extends BaseService<String> {

    /**
     * 课程签到dao
     */
    @Autowired
    CourseSignDao courseSignDao;


    /**
     * 课程班级dao层
     */
    @Autowired
    CourseClassDao courseClassDao;

    @Override
    public ResponseDto<String> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "courseSignIdList", "classId");
        ResponseDto<String> responseDto = new ResponseDto<String>();
        JSONArray courseSignIdList = params.getJSONArray("courseSignIdList");
        List<Course> courseIdList = courseClassDao.queryCourseByClassId(params.getString("classId"));
        String courseId = getTheRightCourseId(convertCourseListToCourseIdList(courseIdList), getCourseId(courseSignIdList));
        String latestCourseSignId = courseSignDao.queryTheLatestCourseSignId(courseId);
        responseDto.setData(latestCourseSignId);
        return responseDto;
    }


    /**
     * 获取正确课程 id
     *
     * @param courseIdList           根具班级查询到的所有课程id
     * @param courseIdByCourseSignId 根具课程签到id获取的所有课程id
     * @return 返回学生的上课的id
     */
    public String getTheRightCourseId(List<String> courseIdList, List<String> courseIdByCourseSignId) {
        boolean result = courseIdList.retainAll(courseIdByCourseSignId);
        //获取到自己课程的签到id
        if (result) {
            return courseIdList.get(0);
        }
        //如果查询不到表明没有发现老师的签到信号
        else {
            return null;
        }
    }


    /**
     * @param courseList 课程清单
     * @return 返回课程id的list
     */
    public List<String> convertCourseListToCourseIdList(final List<Course> courseList) {
        List<String> courseIdList = new ArrayList<String>();
        for (Course course : courseList) {
            courseIdList.add(course.getId());
        }
        return courseIdList;
    }


    /**
     * 根据课程的签到id获取所有的课程id
     *
     * @param courseSignIdArray 课程签到idArray
     * @return 返回所有的课程id
     */
    public List<String> getCourseId(final JSONArray courseSignIdArray) {
        List<String> courseIdList = new ArrayList<String>();
        CourseSign courseSign;
        for (Object object : courseSignIdArray) {
            courseSign = courseSignDao.queryByKey(object.toString());
            courseIdList.add(courseSign.getCourseId());
        }
        return courseIdList;
    }



}
