package com.present.login.service;

import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.ResponseDto;
import com.present.common.exception.ExternalServiceException;
import com.present.common.service.BaseService;
import com.present.common.service.TokenApiService;
import com.present.common.util.CheckUtil;
import com.present.common.util.MessageUtil;
import com.present.login.bean.Teacher;
import com.present.login.dao.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Larry-sea on 2017/3/17.
 * <p>
 * 老师注册接口接口
 */
@Service("teacherRegister")
public class TeacherRegisterService extends BaseService {


    @Autowired
    TeacherDao teacherDao;

    @Autowired
    TokenApiService tokenApiService;

    @Override
    public ResponseDto process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, params.getString("token"), params.getString("name"), params.getString("password"), params.getString("school_id"), params.getString("mail"), params.getString("phone"));
        tokenApiService.checkToken(params.getString("token"));
        int result = teacherDao.isValidAccount(params.getString("phone"));
        //账号已经存在
        if (result > 0) {
            throw new ExternalServiceException(MessageUtil.getMessageInfoByKey("login.account.already.exist"));
        } else {
            teacherDao.insert(initTeacher(params));
        }
        return new ResponseDto();
    }


    /**
     * 初始化一个老师信息
     *
     * @param jsonObject
     * @return
     */
    public Teacher initTeacher(final JSONObject jsonObject) {
        if (jsonObject == null) {
            throw new IllegalArgumentException("param cant empty");
        }
        Teacher teacher = new Teacher();
        teacher.setName(jsonObject.getString("name"));
        teacher.setSchoolId(jsonObject.getString("schooleId"));
        teacher.setPhone(jsonObject.getString("phone"));
        teacher.setPassword(jsonObject.getString("password"));
        teacher.setMail(jsonObject.getString("mail"));
        return teacher;
    }


}
