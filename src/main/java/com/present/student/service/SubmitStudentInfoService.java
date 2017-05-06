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
 * <p>
 * 提交学生的详细信息
 */

@Service("submitStudentInfo")
public class SubmitStudentInfoService extends BaseService {

    @Autowired
    StudentDao studentDao;

    @Override
    public ResponseDto process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, "student");

        submitStudentInfo(params);
        return new ResponseDto();
    }

    /**
     * 提交用户信息
     *
     * @param params params参数
     */
    private void submitStudentInfo(JSONObject params) {
        studentDao.insert(JSONObject.parseObject(params.get("student").toString(), Student.class));
        /*studentDao.submitStudentInfo(params.getString("studentId"),
                params.getString("password"),
                params.getString("phone"),
                params.getString("sexual"),
                params.getString("imel"),
                params.getString("mail"));*/
    }
}
