package com.present.student.service;

import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.ResponseDto;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.student.bean.Student;
import com.present.student.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Larry-sea on 2017/3/22.
 *
 * 提交学生的详细信息
 *
 */

@Service("submitStudentInfo")
public class SubmitStudentInfoService extends BaseService{

    @Autowired
    StudentDao studentDao;

    @Override
    public ResponseDto process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params,"password","phone","sexual","portraitUrl","imel","mail");
        submitStudentInfo(params);
        return new ResponseDto();
    }

    /**
     * 提交用户信息
     *
     * @param params   params参数
     */
    private void submitStudentInfo(JSONObject params) {
        Student student=new Student();
        student.setPassword(params.getString("password"));
        student.setPhone(params.getString("phone"));
        student.setSexual(params.getString("sexual"));
        student.setPortraitUrl(params.getString("portraitUrl"));
        student.setImel(params.getString("imel"));
        student.setMail(params.getString("mail"));
        studentDao.insert(student);
    }
}
