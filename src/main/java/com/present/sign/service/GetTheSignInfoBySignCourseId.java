package com.present.sign.service;

import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.ResponseDto;
import com.present.common.exception.ExternalServiceException;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.common.util.MessageUtil;
import com.present.course.bean.Course;
import com.present.sign.bean.StudentSign;
import com.present.sign.dao.StudentSignDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Larry-sea on 2017/3/20.
 * <p>
 * <p>
 * 通过课程签到id来获取学生的考勤信息
 */
public class GetTheSignInfoBySignCourseId extends BaseService<List<StudentSign>> {


    @Autowired
    StudentSignDao studentSignDao;


    @Override
    public ResponseDto<List<StudentSign>> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, params.getString("courseSignId"));
        List<StudentSign> studentSignList = studentSignDao.getStudentSignInfoList(params.getString("courseSignId"));
        ResponseDto<List<StudentSign>> studentSignResponseDto = new ResponseDto<List<StudentSign>>();
        if (null != studentSignList) {
            studentSignResponseDto.setData(studentSignList);
        } else {
            throw new ExternalServiceException(MessageUtil.getMessageInfoByKey("sign.noSignData"));
        }

        return studentSignResponseDto;
    }


}
