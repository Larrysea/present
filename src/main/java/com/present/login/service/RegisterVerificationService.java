package com.present.login.service;

import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.MessageInfoDto;
import com.present.common.dto.ResponseDto;
import com.present.common.exception.ExternalServiceException;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.common.util.MessageUtil;
import com.present.login.dao.TeacherDao;
import com.present.student.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Larry-sea on 2017/4/19.
 * <p>
 * <p>
 * 验证账户是否存在，主要是检验手机号是否已经注册了
 */
@Service("registerVerification")
public class RegisterVerificationService extends BaseService<String> {

    @Autowired
    StudentDao studentDao;

    @Autowired
    TeacherDao teacherDao;

    @Override
    public ResponseDto<String> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "phone");
        boolean isAlreadyExist = isAlreadyRegister(params.getString("phone"));
        ResponseDto<String> responseDto = new ResponseDto<String>();
        if (isAlreadyExist) {
            MessageInfoDto messageInfoDto = MessageUtil.getMessageInfoByKey("register.account.alreadyexist");
            throw new ExternalServiceException(messageInfoDto);
        } else {
            responseDto.setData("succeed");
        }
        return responseDto;
    }


    /**
     * 验证手机账号
     *
     * @param phone
     * @return 如果手机已经注册则返回true否则返回false
     */
    public boolean isAlreadyRegister(String phone) {
        if (phone != null) {
            if (studentDao.queryByPhone(phone) > 0 || teacherDao.queryByPhone(phone) > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new IllegalArgumentException("params cant empty");
        }

    }



}
