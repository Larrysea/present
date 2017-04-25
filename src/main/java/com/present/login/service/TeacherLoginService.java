package com.present.login.service;

import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.MessageInfoDto;
import com.present.common.dto.ResponseDto;
import com.present.common.exception.ExternalServiceException;
import com.present.common.service.BaseService;
import com.present.common.service.TokenApiService;
import com.present.common.util.CheckUtil;
import com.present.common.util.MessageUtil;
import com.present.login.bean.Teacher;
import com.present.login.dto.TeacherLoginSuccessDto;
import com.present.login.dao.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Larry-sea on 2017/3/16.
 * <p>
 * 老师登录服务
 */
@Service("teacherLogin")
public class TeacherLoginService extends BaseService<TeacherLoginSuccessDto> {


    @Autowired
    TeacherDao teacherDao;

    @Autowired
    TokenApiService tokenApiService;


    @Override
    public ResponseDto<TeacherLoginSuccessDto> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "phone", "password");

        int result = teacherDao.queryByPhone(params.getString("phone"));
        Teacher teacher = null;
        ResponseDto<TeacherLoginSuccessDto> responseDto;
            //账户存在
        if (result > 0) {
            teacher = teacherDao.login(params.getString("phone"), params.getString("password"));
            //账户存在但是密码错误
            if (teacher == null) {
                throw new ExternalServiceException(MessageUtil.getMessageInfoByKey("login.invalid.password"));
            }
            //登录成功
            else {
                tokenApiService.setToken(teacher.getId());
                responseDto = new ResponseDto<TeacherLoginSuccessDto>();
                responseDto.setData(initTeacherLoginDto(teacher, tokenApiService.getToken(teacher.getId())));
            }

        }
        //账户不存在
        else {
            MessageInfoDto messageInfoDto = MessageUtil.getMessageInfoByKey("invalid.account");
            throw new ExternalServiceException(messageInfoDto);
        }

        return responseDto;
    }

    /**
     * 初始化登录成功的返回的数据dto
     *
     * @return
     */
    public TeacherLoginSuccessDto initTeacherLoginDto(final Teacher teacher, final String token) {
        if (teacher != null && token != null) {
            TeacherLoginSuccessDto teacherLoginSuccessDto = new TeacherLoginSuccessDto();
            teacherLoginSuccessDto.setName(teacher.getName());
            teacherLoginSuccessDto.setToken(token);
            teacherLoginSuccessDto.setMail(teacher.getMail());
            teacherLoginSuccessDto.setPhone(teacher.getPhone());
            teacherLoginSuccessDto.setSchoolId(teacher.getSchoolId());
            teacherLoginSuccessDto.setUserId(teacher.getId());
            return teacherLoginSuccessDto;
        } else {
            throw new IllegalArgumentException("param cant empty");
        }
    }
}
