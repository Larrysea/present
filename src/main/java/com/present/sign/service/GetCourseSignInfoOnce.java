package com.present.sign.service;

import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.ResponseDto;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.sign.dao.StudentSignDao;
import com.present.sign.dto.StudentCourseSignDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Larry-sea on 2017/5/11.
 *
 * 获取某一次签到的所有学生考勤记录
 *
 */

@Service("getCourseSignInfoOnce")
public class GetCourseSignInfoOnce extends BaseService<List<StudentCourseSignDto>> {
    @Autowired
    StudentSignDao studentSignDao;


    @Override
    public ResponseDto<List<StudentCourseSignDto>> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "courseSignId");
        List<StudentCourseSignDto> studentSignList = studentSignDao.queryStudentSignDtoByCourseSignId(params.getString("courseSignId"));
        ResponseDto<List<StudentCourseSignDto>> studentSignResponseDto = new ResponseDto<List<StudentCourseSignDto>>();
        studentSignResponseDto.setData(studentSignList);
        return studentSignResponseDto;

    }


}
