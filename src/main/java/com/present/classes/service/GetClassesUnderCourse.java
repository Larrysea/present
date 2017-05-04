package com.present.classes.service;

import com.alibaba.fastjson.JSONObject;
import com.present.classes.bean.Classes;
import com.present.common.dto.ResponseDto;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.course.dao.CourseClassDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Larry-sea on 2017/3/20.
 * <p>
 * 通过老师id和课程id获取某个课程下面的所有班级
 */
@Service("getClassesUnderCourse")
public class GetClassesUnderCourse extends BaseService<List<Classes>> {

    @Autowired
    CourseClassDao courseClassDao;


    @Override
    public ResponseDto<List<Classes>> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "teacherId", "courseId");
        return getClassUnderCourse(params);
    }

    /**
     * 通过老师id还有课程id获取某门课程下面的班级
     *
     * @param params
     * @return
     */
    private ResponseDto<List<Classes>> getClassUnderCourse(JSONObject params) {
        List<Classes> classesList = courseClassDao
                .queryClassesByTeacherAndCourse(params.getString("teacherId"), params.getString("courseId"));
        ResponseDto<List<Classes>> responseDto = new ResponseDto<List<Classes>>();
        responseDto.setData(classesList);
        return responseDto;
    }


}
