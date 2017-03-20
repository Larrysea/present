package com.present.classes.service;

import com.alibaba.fastjson.JSONObject;
import com.present.classes.bean.Classes;
import com.present.classes.dao.CourseClassDao;
import com.present.common.dto.ResponseDto;
import com.present.common.exception.ExternalServiceException;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.common.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Larry-sea on 2017/3/20.
 * <p>
 * 通过老师id和课程id获取某个课程下面的所有班级
 */
public class GetClassesUnderCourse extends BaseService<List<Classes>> {

    @Autowired
    CourseClassDao courseClassDao;


    @Override
    public ResponseDto<List<Classes>> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, params.getString("teacherId"), params.getString("courseId"));
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
                .queryClassesByteacherAndCourse(params.getString("teacherId"), params.getString("courseId"));
        ResponseDto<List<Classes>> responseDto = new ResponseDto<List<Classes>>();
        if (null != classesList) {
            responseDto.setData(classesList);

        } else {
            throw new ExternalServiceException(MessageUtil.getMessageInfoByKey("classes.noClasses"));
        }
        return responseDto;
    }


}
