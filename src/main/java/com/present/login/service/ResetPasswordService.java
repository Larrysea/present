package com.present.login.service;

import com.alibaba.fastjson.JSONObject;
import com.present.common.config.Constants;
import com.present.common.dto.ResponseDto;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.login.bean.Teacher;
import com.present.login.dao.TeacherDao;
import com.present.student.bean.Student;
import com.present.student.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Larry-sea on 2017/5/25.
 * <p>
 * 重新设置密码服务
 */
@Service("resetPassword")
public class ResetPasswordService extends BaseService<String> {

    //学生dao层
    @Autowired
    StudentDao studentDao;
    //老师dao层
    @Autowired
    TeacherDao teacherDao;

    @Override
    public ResponseDto<String> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "userType", "password", "userId");
        if (params.getInteger("userType").equals(Constants.TEACHER_TYPE)) {
            Teacher teacher = new Teacher();
            teacher.setId(params.getString("userId"));
            teacher.setPassword(params.getString("password"));
            teacherDao.updateByKey(teacher);
        } else if (params.getInteger("userType").equals(Constants.STUDENT_TYPE)) {
            Student student = new Student();
            student.setId(params.getString("userId"));
            student.setPassword(params.getString("password"));
            studentDao.updateByKey(student);
        }
        return new ResponseDto<String>();
    }
}
