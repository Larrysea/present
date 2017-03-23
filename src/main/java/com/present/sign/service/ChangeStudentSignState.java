package com.present.sign.service;

import com.alibaba.fastjson.JSONObject;
import com.present.common.dto.ResponseDto;
import com.present.common.service.BaseService;
import com.present.common.util.CheckUtil;
import com.present.sign.dao.StudentSignDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Larry-sea on 2017/3/21.
 * <p>
 * 老师修改某个学生的签到状态
 */
public class ChangeStudentSignState extends BaseService {

    @Autowired
    StudentSignDao studentSignDao;

    @Override
    public ResponseDto process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        CheckUtil.checkEmpty(params, params.getString("courseSignId"), params.getString("studentId"), params.getString("signState"));
        studentSignDao.changeStudentSignState(params.getString("courseId"), params.getString("studentId"), params.getString("signState"));

        return new ResponseDto();
    }
}
