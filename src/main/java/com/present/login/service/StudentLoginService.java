package com.present.login.service;

import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.ResponseDto;
import com.present.common.exception.ExternalServiceException;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.common.util.MessageUtil;
import com.present.student.baen.Student;
import com.present.student.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

/**
 * Created by Larry-sea on 2017/3/18.
 *
 * 学生用户登录
 */
public class StudentLoginService extends BaseService<Student> {


    @Autowired
    StudentDao studentDao;

    @Override
    public ResponseDto<Student> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, params.getString("studentNumber"), params.getString("password"));


        return super.process(params, request, response);
    }


    /**
     * 学生用户登录
     *
     * @param schoolId      学校id
     * @param studentNumber 学生学号
     * @param password      用户登录密码
     * @return
     */
    public ResponseDto<Student> studentLogin(String schoolId, String studentNumber, String password) {
        String result = studentDao.isVaildUser(studentNumber, schoolId);
        if (result == null) {
            throw new ExternalServiceException(MessageUtil.getMessageInfoByKey("student.isValidAccount"));
        }
        ResponseDto<Student> responseDto = new ResponseDto<Student>();
        Student student = studentDao.studentLogin(schoolId, studentNumber, password);
        responseDto.setData(student);
        return responseDto;
    }

}
