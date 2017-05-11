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
 * <p>
 * 获取某一次签到过程中学生缺勤的清单
 */
@Service("getAbsenceStudentList")
public class GetAbsenceStudentList extends BaseService<List<StudentCourseSignDto>> {

    @Autowired
    StudentSignDao studentSignDao;


    @Override
    public ResponseDto<List<StudentCourseSignDto>> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "courseSignId");
        ResponseDto<List<StudentCourseSignDto>> responseDto = new ResponseDto<List<StudentCourseSignDto>>();
        responseDto.setData(studentSignDao.getAbsenceStudentList(params.getString("courseSignId")));
        return responseDto;
    }
}
