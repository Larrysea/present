package com.present.login.service;

import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.ResponseDto;
import com.present.common.exception.ExternalServiceException;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.common.util.MessageUtil;
import com.present.login.dto.StudentLoginSuccessDto;
import com.present.student.bean.Student;
import com.present.student.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Larry-sea on 2017/3/18.
 * <p>
 * 学生用户登录
 */
@Service("studentLogin")
public class StudentLoginService extends BaseService<StudentLoginSuccessDto> {


    @Autowired
    StudentDao studentDao;

    @Override
    public ResponseDto<StudentLoginSuccessDto> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "schoolId", "studentNumber", "password");
        return studentLogin(params.getString("schoolId"), params.getString("studentNumber"), params.getString("password"));
    }


    /**
     * 学生用户登录
     *
     * @param schoolId      学校id
     * @param studentNumber 学生学号
     * @param password      用户登录密码
     * @return
     */
    public ResponseDto<StudentLoginSuccessDto> studentLogin(String schoolId, String studentNumber, String password) {
        String result = studentDao.isValidUser(studentNumber, password, schoolId);
        if (result == null) {
            throw new ExternalServiceException(MessageUtil.getMessageInfoByKey("student.isValidAccount"));
        }
        ResponseDto<StudentLoginSuccessDto> responseDto = new ResponseDto<StudentLoginSuccessDto>();
        StudentLoginSuccessDto studentLoginSuccessDto = converStudentToStudentLoginSuccessDto(studentDao.studentLogin(schoolId, studentNumber, password));
        responseDto.setData(studentLoginSuccessDto);
        return responseDto;
    }


    /**
     * 将学生实体转化为学生登录的dto
     * 其中省略了密码项
     *
     * @param student
     * @return
     */
    private StudentLoginSuccessDto converStudentToStudentLoginSuccessDto(final Student student) {
        final StudentLoginSuccessDto studentLoginSuccessDto;
        if (student != null) {
            studentLoginSuccessDto = new StudentLoginSuccessDto();
            studentLoginSuccessDto.setId(student.getId());
            studentLoginSuccessDto.setImel(student.getImel());
            studentLoginSuccessDto.setMail(student.getMail());
            studentLoginSuccessDto.setName(student.getName());
            studentLoginSuccessDto.setPhone(student.getPhone());
            studentLoginSuccessDto.setPortraitUrl(student.getPortraitUrl());
            studentLoginSuccessDto.setSexual(student.getSexual());
            studentLoginSuccessDto.setStudentNumber(student.getStudentNumber());
            studentLoginSuccessDto.setSchoolId(student.getSchoolId());

        } else {
            throw new IllegalArgumentException("student cant empty");
        }

        return studentLoginSuccessDto;
    }
}
