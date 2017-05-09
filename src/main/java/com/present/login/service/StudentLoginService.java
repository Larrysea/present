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
        CheckUtil.checkEmpty(params, "password", "phone");
        return studentLogin(params.getString("phone"), params.getString("password"));
    }


    /**
     * 学生用户登录
     *
     * @param phone    注册的手机号
     * @param password 用户登录密码
     * @return
     */
    public ResponseDto<StudentLoginSuccessDto> studentLogin(String phone, String password) {
        int result = studentDao.queryByPhone(phone);
        if (result <= 0) {
            throw new ExternalServiceException(MessageUtil.getMessageInfoByKey("login.invalid.account"));
        }
        ResponseDto<StudentLoginSuccessDto> responseDto = new ResponseDto<StudentLoginSuccessDto>();
        Student student = studentDao.studentLoginByPhone(phone, password);
        if (student == null) {
            throw new ExternalServiceException(MessageUtil.getMessageInfoByKey("login.invalid.password"));
        }
        StudentLoginSuccessDto studentLoginSuccessDto = convertStudentToStudentLoginSuccessDto(student);
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
    private StudentLoginSuccessDto convertStudentToStudentLoginSuccessDto(final Student student) {
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
            studentLoginSuccessDto.setClassId(student.getClassId());
            studentLoginSuccessDto.setClassPosition(student.getClassPosition());

        } else {
            throw new IllegalArgumentException("student cant empty");
        }

        return studentLoginSuccessDto;
    }
}
