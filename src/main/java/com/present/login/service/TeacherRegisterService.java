package com.present.login.service;

import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.ResponseDto;
import com.present.common.exception.ExternalServiceException;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.common.util.MessageUtil;
import com.present.login.bean.Teacher;
import com.present.login.dao.TeacherDao;
import com.present.login.dto.TeacherLoginSuccessDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Larry-sea on 2017/3/17.
 * <p>
 * 老师注册接口接口
 */
@Service("submitTeacherInfo")
public class TeacherRegisterService extends BaseService {


    @Autowired
    TeacherDao teacherDao;


    @Override
    public ResponseDto process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "teacher");
        Teacher teacher = params.getObject("teacher", Teacher.class);
        int result = teacherDao.queryByPhone(teacher.getPhone());
        if (result > 0) {
            throw new ExternalServiceException(MessageUtil.getMessageInfoByKey("login.account.already.exist"));
        } else {
            teacherDao.insert(teacher);
        }
        return new ResponseDto();
    }


    /**
     * 将老师实体转换为loginSuccessDto
     *
     * @return teacherLoginSuccessDto
     */
    public TeacherLoginSuccessDto convertTeacherToTeacherLoginSuccessDto(final Teacher teacher) {
        if (teacher == null) {
            throw new IllegalArgumentException("parameter  [teacher] cant empty");
        }
        final TeacherLoginSuccessDto teacherLoginSuccessDto = new TeacherLoginSuccessDto();
        teacherLoginSuccessDto.setSchoolId(teacher.getSchoolId());
        teacherLoginSuccessDto.setUserId(teacher.getId());
        teacherLoginSuccessDto.setPhone(teacher.getPhone());
        teacherLoginSuccessDto.setMail(teacher.getMail());
        teacherLoginSuccessDto.setName(teacher.getName());
        return teacherLoginSuccessDto;
    }


}
